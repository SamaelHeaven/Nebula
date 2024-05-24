package org.samaelheaven.nebula.database;

import org.jetbrains.annotations.NotNull;

public final class PrimitiveResultSetHandler {
    public static @NotNull ResultSetHandler<Double> getDouble(@NotNull String columnLabel) {
        return resultSet -> resultSet.getDouble(columnLabel);
    }

    public static @NotNull ResultSetHandler<Float> getFloat(@NotNull String columnLabel) {
        return resultSet -> resultSet.getFloat(columnLabel);
    }

    public static @NotNull ResultSetHandler<Long> getLong(@NotNull String columnLabel) {
        return resultSet -> resultSet.getLong(columnLabel);
    }

    public static @NotNull ResultSetHandler<Integer> getInt(@NotNull String columnLabel) {
        return resultSet -> resultSet.getInt(columnLabel);
    }

    public static @NotNull ResultSetHandler<Short> getShort(@NotNull String columnLabel) {
        return resultSet -> resultSet.getShort(columnLabel);
    }

    public static @NotNull ResultSetHandler<Byte> getByte(@NotNull String columnLabel) {
        return resultSet -> resultSet.getByte(columnLabel);
    }

    public static @NotNull ResultSetHandler<Boolean> getBoolean(@NotNull String columnLabel) {
        return resultSet -> resultSet.getBoolean(columnLabel);
    }

    public static @NotNull ResultSetHandler<String> getString(@NotNull String columnLabel) {
        return resultSet -> resultSet.getString(columnLabel);
    }
}