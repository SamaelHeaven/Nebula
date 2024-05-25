package org.samaelheaven.nebula.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.net.URL;

public final class Resource {
    public static @Nullable URL url(@NotNull String path) {
        return Resource.class.getResource(Path.format(path));
    }

    public static @Nullable InputStream inputStream(@NotNull String path) {
        return Resource.class.getResourceAsStream(Path.format(path));
    }
}