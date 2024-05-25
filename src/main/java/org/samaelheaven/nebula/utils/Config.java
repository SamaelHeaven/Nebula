package org.samaelheaven.nebula.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.samaelheaven.nebula.core.NebulaException;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public final class Config {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(Resource.inputStream("/environment/config.properties"));
        } catch (IOException e) {
            throw new NebulaException(e);
        }
    }

    public static double getDouble(@NotNull String key) throws NumberFormatException {
        return Double.parseDouble(getString(key));
    }

    public static double getDouble(@NotNull String key, double defaultValue) {
        try {
            return getDouble(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static float getFloat(@NotNull String key) throws NumberFormatException {
        return Float.parseFloat(getString(key));
    }

    public static float getFloat(@NotNull String key, float defaultValue) {
        try {
            return getFloat(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static long getLong(@NotNull String key) throws NumberFormatException {
        return Long.parseLong(getString(key));
    }

    public static long getLong(@NotNull String key, long defaultValue) {
        try {
            return getLong(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static int getInt(@NotNull String key) throws NumberFormatException {
        return Integer.parseInt(getString(key));
    }

    public static int getInt(@NotNull String key, int defaultValue) {
        try {
            return getInt(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static short getShort(@NotNull String key) throws NumberFormatException {
        return Short.parseShort(getString(key));
    }

    public static short getShort(@NotNull String key, short defaultValue) {
        try {
            return getShort(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static byte getByte(@NotNull String key) throws NumberFormatException {
        return Byte.parseByte(getString(key));
    }

    public static byte getByte(@NotNull String key, byte defaultValue) {
        try {
            return getByte(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static boolean getBoolean(@NotNull String key) {
        return Boolean.parseBoolean(getString(key));
    }

    public static boolean getBoolean(@NotNull String key, boolean defaultValue) {
        try {
            return getBoolean(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static @NotNull String getString(@NotNull String key) {
        return Objects.requireNonNull(getProperty(key));
    }

    public static @NotNull String getString(@NotNull String key, @NotNull String defaultValue) {
        try {
            return getString(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static @Nullable String getProperty(@NotNull String key) {
        return properties.getProperty(key);
    }

    public static boolean containsKey(@NotNull String key) {
        return properties.containsKey(key);
    }
}