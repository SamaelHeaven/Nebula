package org.samaelheaven.nebula.network;

import com.sun.net.httpserver.Headers;
import org.jetbrains.annotations.NotNull;

public final class Response {
    private final Headers headers;
    private int code;
    private byte[] body;

    public Response() {
        this.headers = new Headers();
        this.code = 200;
        this.body = new byte[0];
    }

    public @NotNull Headers getHeaders() {
        return headers;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}