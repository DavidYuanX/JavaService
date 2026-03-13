#!/bin/bash
cd "$(dirname "$0")"

# 数据库配置
DB_HOST="localhost"
DB_PORT="3306"
DB_NAME="crud_db"
DB_USER="root"
DB_PASS="123qweasdzxc"

INPUT_FILE="data/dump.sql"

# 查找 mysql
find_mysql() {
    if command -v mysql &> /dev/null; then
        echo "mysql"
    elif [ -x "/usr/local/mysql/bin/mysql" ]; then
        echo "/usr/local/mysql/bin/mysql"
    elif [ -x "/opt/homebrew/bin/mysql" ]; then
        echo "/opt/homebrew/bin/mysql"
    fi
}

echo "=== 导入数据库数据 ==="
echo "数据库: $DB_NAME"
echo "输入文件: $INPUT_FILE"

if [ ! -f "$INPUT_FILE" ]; then
    echo "文件不存在: $INPUT_FILE"
    exit 1
fi

# 检查是否使用 Docker
MYSQL_CONTAINER=$(docker ps --format "{{.Names}}" 2>/dev/null | grep -i mysql | head -1)
MYSQL=$(find_mysql)

if [ -n "$MYSQL_CONTAINER" ]; then
    echo "检测到 MySQL 容器: $MYSQL_CONTAINER"
    docker exec -i "$MYSQL_CONTAINER" mysql -u "$DB_USER" -p"$DB_PASS" "$DB_NAME" < "$INPUT_FILE" 2>/dev/null
elif [ -n "$MYSQL" ]; then
    echo "使用: $MYSQL"
    "$MYSQL" -h "$DB_HOST" -P "$DB_PORT" -u "$DB_USER" -p"$DB_PASS" "$DB_NAME" < "$INPUT_FILE" 2>/dev/null
else
    echo "错误: 未找到 mysql 或 MySQL Docker 容器"
    echo "请安装 mysql-client 或启动 MySQL Docker 容器"
    exit 1
fi

if [ $? -eq 0 ]; then
    echo "导入成功!"
else
    echo "导入失败，请检查数据库连接"
    exit 1
fi