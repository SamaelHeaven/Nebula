package org.samaelheaven.nebula.database;

import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandler<T> {
    @NotNull T handle(@NotNull ResultSet resultSet) throws SQLException;
}