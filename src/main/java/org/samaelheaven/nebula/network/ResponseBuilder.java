package org.samaelheaven.nebula.network;

import org.jetbrains.annotations.NotNull;

public interface ResponseBuilder {
    @NotNull Response buildResponse(@NotNull Request request);
}