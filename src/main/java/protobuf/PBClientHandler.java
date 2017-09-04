package protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class PBClientHandler extends SimpleChannelInboundHandler<HotelListSearchProtos.HotelListSearchRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HotelListSearchProtos.HotelListSearchRequest msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        HotelListSearchProtos.HotelListSearchRequest request = HotelListSearchProtos.HotelListSearchRequest
                .newBuilder()
                .setCityId(2)
                .setHotelDateType(1)
                .addFilters(HotelListSearchProtos.FilterItem
                        .newBuilder()
                        .setFilterId(1)
                        .setTitle("地标")
                        .setValue("虹桥机场")
                        .build())
                .build();

        ctx.channel().writeAndFlush(request);
    }
}
