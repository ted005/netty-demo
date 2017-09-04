package protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

//实际处理的数据类型是HotelListSearchRequest
public class PBHandler extends SimpleChannelInboundHandler<HotelListSearchProtos.HotelListSearchRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HotelListSearchProtos.HotelListSearchRequest msg) throws Exception {
        System.out.println(msg.getFilters(0).getValue());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
