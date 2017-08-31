package chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE); //与服务器连接的channel集合

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channels.forEach(ch -> {
            if (channel != ch) { //非当前channel
                ch.writeAndFlush(channel.remoteAddress() + "说:" + msg + "\r\n");
            } else {
                ch.writeAndFlush("自己说:" + msg + "\r\n");
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channels.remove(ctx.channel());//netty自动调用
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel currentChannel = ctx.channel();
        channels.forEach(c -> {
            if (c != currentChannel) {
                c.writeAndFlush(ctx.channel().remoteAddress() + "上线了\r\n");//告诉其他client当前channel所连接的客户端上线了
            }
        });
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel currentChannel = ctx.channel();
        channels.forEach(c -> {
            if (c != currentChannel) {
                c.writeAndFlush(ctx.channel().remoteAddress() + "下线了\r\n");//告诉其他client
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
