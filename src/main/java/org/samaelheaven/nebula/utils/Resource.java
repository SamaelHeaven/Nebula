package org.samaelheaven.nebula.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.net.URL;
import java.util.regex.Pattern;

public final class Resource {
    public static @Nullable URL url(@NotNull String path) {
        return Resource.class.getResource(formatPath(path));
    }

    public static @Nullable InputStream inputStream(@NotNull String path) {
        return Resource.class.getResourceAsStream(formatPath(path));
    }

    private static String formatPath(String path) {
        path = path.replace("\\", "/");
        var pattern = Pattern.compile("/+");
        var matcher = pattern.matcher(path);
        path = matcher.replaceAll("/");
        while (path.startsWith("/")) {
            path = path.substring(1);
        }
        return "/" + path;
    }
}