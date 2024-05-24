package org.samaelheaven.nebula.database;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.samaelheaven.nebula.utils.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public final class Database {
    private final String url;
    private final String username;
    private final String password;
    private Connection currentConnection;

    public Database(@NotNull String url, @NotNull String username, @NotNull String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Database() {
        url = Config.getString("DATABASE_URL");
        username = Config.getString("DATABASE_USER");
        password = Config.getString("DATABASE_PASSWORD");
    }

    public @NotNull Connection openConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public @NotNull ResultSet select(@NotNull String query, Object... args) throws SQLException {
        Connection connection = null;
        try {
            connection = Objects.requireNonNullElse(currentConnection, openConnection());
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.closeOnCompletion();
            for (var i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            return preparedStatement.executeQuery();
        } finally {
            if (currentConnection == null && connection != null) {
                connection.close();
            }
        }
    }

    public void execute(@NotNull String query, Object... args) throws SQLException {
        Connection connection = null;
        try {
            connection = Objects.requireNonNullElse(currentConnection, openConnection());
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.closeOnCompletion();
            for (var i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            preparedStatement.execute();
        } finally {
            if (currentConnection == null && connection != null) {
                connection.close();
            }
        }
    }

    public <T> @Nullable T selectInstance(@NotNull ResultSetHandler<T> resultSetHandler, @NotNull String query, Object... args) throws SQLException {
        try (var result = select(query, args)) {
            if (result.next()) {
                return resultSetHandler.handle(result);
            }
        }
        return null;
    }

    public @NotNull <T> Collection<T> selectCollection(@NotNull ResultSetHandler<T> resultSetHandler, @NotNull String query, Object... args) throws SQLException {
        try (var result = select(query, args)) {
            var collection = new ArrayList<T>();
            while (result.next()) {
                collection.add(resultSetHandler.handle(result));
            }
            return Collections.unmodifiableList(collection);
        }
    }

    public void executeTransaction(@NotNull Transaction transaction) throws SQLException {
        try (var connection = openConnection()) {
            currentConnection = connection;
            var autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            try {
                transaction.run();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(autoCommit);
            }
        } finally {
            currentConnection = null;
        }
    }
}