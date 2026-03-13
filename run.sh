#!/bin/bash
cd "$(dirname "$0")"
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-22.jdk/Contents/Home

PORT=8080

# 检查端口占用
PID=$(lsof -ti:$PORT 2>/dev/null)
if [ -n "$PID" ]; then
    echo "端口 $PORT 已被占用 (PID: $PID)"
    read -p "是否终止占用进程？(y/n): " choice
    if [ "$choice" = "y" ] || [ "$choice" = "Y" ]; then
        kill -9 $PID
        echo "已终止进程 $PID"
    else
        echo "启动取消"
        exit 1
    fi
fi

mvn spring-boot:run