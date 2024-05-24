package org.samaelheaven.nebula.database;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Objects;

public abstract class Broker {
    private Database database;

    public Broker(@Nullable Database database) {
        this.database = Objects.requireNonNullElse(database, new Database());
    }

    public @NotNull Database getDatabase() {
        return database;
    }

    public void setDatabase(@Nullable Database database) {
        this.database = Objects.requireNonNullElse(database, new Database());
    }

    public @NotNull ResultSet select(@NotNull String query, Object... args) throws BrokerException {
        try {
            return database.select(query, args);
        } catch (SQLException e) {
            throw new BrokerException(e);
        }
    }

    public void execute(@NotNull String query, Object... args) throws BrokerException {
        try {
            database.execute(query, args);
        } catch (SQLException e) {
            throw new BrokerException(e);
        }
    }

    public <T> @Nullable T selectInstance(@NotNull ResultSetHandler<T> resultSetHandler, @NotNull String query, Object... args) throws BrokerException {
        try {
            return database.selectInstance(resultSetHandler, query, args);
        } catch (SQLException e) {
            throw new BrokerException(e);
        }
    }

    public @NotNull <T> Collection<T> selectCollection(@NotNull ResultSetHandler<T> resultSetHandler, @NotNull String query, Object... args) throws BrokerException {
        try {
            return database.selectCollection(resultSetHandler, query, args);
        } catch (SQLException e) {
            throw new BrokerException(e);
        }
    }

    public void executeTransaction(@NotNull Transaction transaction) throws BrokerException {
        try {
            database.executeTransaction(transaction);
        } catch (SQLException e) {
            throw new BrokerException(e);
        }
    }
}