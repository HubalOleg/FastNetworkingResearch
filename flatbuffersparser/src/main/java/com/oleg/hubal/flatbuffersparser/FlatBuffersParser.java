package com.oleg.hubal.flatbuffersparser;

import java.nio.ByteBuffer;

/**
 * Created by User on 11.04.2017.
 */

public class FlatBuffersParser {

    static {
        System.loadLibrary("FlatBuffersParser");
    }

    public ByteBuffer parseJson(String json, String schema) {
        final byte[] bytes = parseJsonNative(json, schema);
        return ByteBuffer.wrap(bytes);
    }

    private native byte[] parseJsonNative(String json, String schema);
}
