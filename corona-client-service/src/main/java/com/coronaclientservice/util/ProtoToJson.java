package com.coronaclientservice.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import com.google.protobuf.AbstractMessage.Builder;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;

public final class ProtoToJson {

    public static String toJson(MessageOrBuilder messageOrBuilder) throws IOException {
        return JsonFormat.printer().print(messageOrBuilder);
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T extends Message> T fromJson(String json, Class<T> clazz) throws IOException {
        Builder builder = null;
        try {
            builder = (Builder) clazz.getMethod("newBuilder").invoke(null);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            return null;
        }
        JsonFormat.parser().ignoringUnknownFields().merge(json, builder);
        return (T) builder.build();
    }
}