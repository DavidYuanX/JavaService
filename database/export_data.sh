#!/bin/bash
cd "$(dirname "$0")"

# 数据库配置
DB_HOST="localhost"
DB_PORT="3306"
DB_NAME="crud_db"
DB_USER="root"
DB_PASS="123qweasdzxc"

OUTPUT_FILE="data/dump.sql"

# 查找 mysqldump
find_mysqldump() {
    if command -v mysqldump &> /dev/null; then
        echo "mysqldump"
    elif [ -x "/usr/local/mysql/bin/mysqldump" ]; then
        echo "/usr/local/mysql/bin/mysqldump"
    elif [ -x "/opt/homebrew/bin/mysqldump" ]; then
        echo "/opt/homebrew/bin/mysqldump"
    fi
}

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

# 创建输出目录
mkdir -p "$(dirname "$OUTPUT_FILE")"

echo "=== 导出数据库数据 ==="
echo "数据库: $DB_NAME"
echo "输出文件: $OUTPUT_FILE"

# 检查是否使用 Docker
MYSQL_CONTAINER=$(docker ps --format "{{.Names}}" 2>/dev/null | grep -i mysql | head -1)
MYSQLDUMP=$(find_mysqldump)

if [ -n "$MYSQL_CONTAINER" ]; then
    echo "检测到 MySQL 容器: $MYSQL_CONTAINER"
    docker exec "$MYSQL_CONTAINER" mysqldump -u "$DB_USER" -p"$DB_PASS" \
        --no-create-info \
        --skip-triggers \
        --skip-add-drop-table \
        --complete-insert \
        "$DB_NAME" > "$OUTPUT_FILE" 2>/dev/null
elif [ -n "$MYSQLDUMP" ]; then
    echo "使用: $MYSQLDUMP"
    "$MYSQLDUMP" -h "$DB_HOST" -P "$DB_PORT" -u "$DB_USER" -p"$DB_PASS" \
        --no-create-info \
        --skip-triggers \
        --skip-add-drop-table \
        --complete-insert \
        "$DB_NAME" > "$OUTPUT_FILE" 2>/dev/null
else
    echo "错误: 未找到 mysqldump 或 MySQL Docker 容器"
    echo "请安装 mysql-client 或启动 MySQL Docker 容器"
    exit 1
fi

if [ $? -eq 0 ] && [ -s "$OUTPUT_FILE" ]; then
    echo "导出成功!"
    echo "记录数: $(grep -c "^INSERT" "$OUTPUT_FILE" 2>/dev/null || echo "未知")"
else
    echo "导出失败，请检查数据库连接"
    exit 1
fi