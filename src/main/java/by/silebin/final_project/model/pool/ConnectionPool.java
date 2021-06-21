package by.silebin.final_project.model.pool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectionPool {

    private static ConnectionPool instance;
    private static final int DEFAULT_POOL_SIZE = 8;
    private final BlockingQueue<ProxyConnection> availableConnections;
    private final BlockingQueue<ProxyConnection> usedConnections;

    private static final AtomicBoolean instanceInitialized = new AtomicBoolean(false);

    public static ConnectionPool getInstance() {
        while (instance == null) {
            if (instanceInitialized.compareAndSet(false, true)) {
                instance = new ConnectionPool();
            }
        }
        return instance;
    }

    private ConnectionPool() {
        availableConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        usedConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);

        ConnectionFactory connectionFactory = new ConnectionFactory();
        for(int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = connectionFactory.newConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                availableConnections.offer(proxyConnection);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (availableConnections.isEmpty()) {
                throw new RuntimeException("can't create connections, empty pool");
            }
        }
    }

    public ProxyConnection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = availableConnections.take();
            usedConnections.put(proxyConnection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return proxyConnection;
    }

    public void releaseConnection(Connection connection) {
        if(!(connection instanceof ProxyConnection)) {
            throw new RuntimeException("unknown connection");
        }
        ProxyConnection proxyConnection = (ProxyConnection) connection;
        usedConnections.remove(proxyConnection);
        try {
            availableConnections.put(proxyConnection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            //logger
        }
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                availableConnections.take().closeConnection();
            } catch (SQLException | InterruptedException e) {
                //logger
            }
        }

        deregisterDrivers();
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                //logger
            }
        }
    }
}
