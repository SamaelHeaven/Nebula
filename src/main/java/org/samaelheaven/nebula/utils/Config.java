package org.samaelheaven.nebula.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.samaelheaven.nebula.core.FatalException;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public final class Config {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(Resource.inputStream("/environment/config.properties"));
        } catch (IOException e) {
            throw new FatalException(e);
        }
    }

    public static double getDouble(@NotNull String key) throws NumberFormatException {
        return Double.parseDouble(getString(key));
    }

    public static float getFloat(@NotNull String key) throws NumberFormatException {
        return Float.parseFloat(getString(key));
    }

    public static long getLong(@NotNull String key) throws NumberFormatException {
        return Long.parseLong(getString(key));
    }

    public static int getInt(@NotNull String key) throws NumberFormatException {
        return Integer.parseInt(getString(key));
    }

    public static short getShort(@NotNull String key) throws NumberFormatException {
        return Short.parseShort(getString(key));
    }

    public static byte getByte(@NotNull String key) throws NumberFormatException {
        return Byte.parseByte(getString(key));
    }

    public static boolean getBoolean(@NotNull String key) {
        return Boolean.parseBoolean(getString(key));
    }

    public static @NotNull String getString(@NotNull String key) {
        return Objects.requireNonNull(getProperty(key));
    }

    public static @Nullable String getProperty(@NotNull String key) {
        return properties.getProperty(key);
    }
}