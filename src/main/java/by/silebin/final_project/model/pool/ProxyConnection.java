package by.silebin.final_project.model.pool;

import java.sql.Connection;
import java.sql.SQLException;

public class ProxyConnection implements AutoCloseable {

    private final Connection connection;

    public ProxyConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws Exception {
        ConnectionPool.getInstance().releaseConnection(this);
    }

    public void reallyClose() throws SQLException {
        connection.close();
    }
}
