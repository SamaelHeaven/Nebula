package org.samaelheaven.nebula.core;

import org.jetbrains.annotations.NotNull;
import org.samaelheaven.nebula.network.Request;
import org.samaelheaven.nebula.network.Response;
import org.samaelheaven.nebula.network.Server;

import java.util.Map;
import java.util.WeakHashMap;

public final class Kernel {
    private static final Map<Thread, Response> currentResponses = new WeakHashMap<>();
    private static BaseApplication application;

    public static @NotNull Response getCurrentResponse() {
        return currentResponses.get(Thread.currentThread());
    }

    public static @NotNull BaseApplication getApplication() {
        return application;
    }

    public static void launch(@NotNull BaseApplication application) {
        if (Kernel.application != null) {
            throw new NebulaException();
        }
        Kernel.application = application;
        new Server(Kernel::buildResponse).start();
    }

    public static @NotNull Response buildResponse(@NotNull Request request) {
        var response = new Response();
        currentResponses.put(Thread.currentThread(), response);
        application.handleRequest(request, response);
        return response;
    }
}