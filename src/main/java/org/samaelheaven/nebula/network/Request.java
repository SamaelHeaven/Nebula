package org.samaelheaven.nebula.network;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.jetbrains.annotations.NotNull;
import org.samaelheaven.nebula.core.NebulaException;
import org.samaelheaven.nebula.utils.Path;

import java.io.IOException;

public final class Request {
    private final String method;
    private final String path;
    private final Headers headers;
    private final byte[] body;

    public Request(@NotNull HttpExchange exchange) {
        method = exchange.getRequestMethod();
        path = Path.format(exchange.getRequestURI().toString());
        headers = exchange.getRequestHeaders();
        try {
            body = exchange.getRequestBody().readAllBytes();
        } catch (IOException e) {
            throw new NebulaException();
        }
    }

    public @NotNull String getMethod() {
        return method;
    }

    public @NotNull String getPath() {
        return path;
    }

    public @NotNull Headers getHeaders() {
        return headers;
    }

    public byte[] getBody() {
        return body;
    }
}