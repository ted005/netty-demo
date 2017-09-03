# netty-lecture

Gradle本地仓库地: /.gradle/caches/modules-2/files-2.1

post命令：curl -X post "http://localhost:8899/"

查看端口：lsof -i:8899

client离线时，channelRemove() 消息会保存到离线消息数据库中，下次连接建立时再发送出去

集群节点：最终一致性、非实时一致性（拉取通过日志的方式同步）

http: 1无状态（cookie-session解决）  2基于请求、响应模式的协议（客户端发起请求，建立连接; http 1.1 keep-alive）

ws://server:port/context_path

101:switching protocols http协议升级为websocket