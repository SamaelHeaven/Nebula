package org.samaelheaven.nebula.utils;

import org.jetbrains.annotations.NotNull;

public final class Path {
    public static @NotNull String format(@NotNull String path) {
        path = path.replace("\\", "/");
        while (path.startsWith("/")) {
            path = path.substring(1);
        }
        while (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        return "/" + path;
    }
}