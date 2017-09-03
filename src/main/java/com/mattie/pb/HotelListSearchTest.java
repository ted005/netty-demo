package com.mattie.pb;

import com.google.protobuf.InvalidProtocolBufferException;

public class HotelListSearchTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        HotelListSearchProtos.HotelListSearchRequest request = HotelListSearchProtos.HotelListSearchRequest.newBuilder()
                .setCityId(2)
                .addFilters(HotelListSearchProtos.FilterItem.newBuilder()
                        .setFilterId(1).setTitle("地标").setValue("虹桥机场"))
                .setHotelDateType(1)
                .build();

        byte[] byteArray = request.toByteArray();

        HotelListSearchProtos.HotelListSearchRequest request1 = HotelListSearchProtos.HotelListSearchRequest.parseFrom(byteArray);
        System.out.println(request1);

    }
}
