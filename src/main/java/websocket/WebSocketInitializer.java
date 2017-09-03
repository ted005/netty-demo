package websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;


public class WebSocketInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());//以块的方式写
        pipeline.addLast(new HttpObjectAggregator(8192));//聚合为FullHttpRequest、FullHttpResponse, 放在HttpServerCodec后面
        pipeline.addLast(new WebSocketServerProtocolHandler("/abc"));//  ws://server:port/abc
        pipeline.addLast(new TextWebSocketFrameHandler());
    }
}
