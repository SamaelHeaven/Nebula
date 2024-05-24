package org.samaelheaven.nebula.database;

import java.sql.SQLException;

public interface Transaction {
    void run() throws SQLException;
}