package by.silebin.final_project.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class);

    private final String DATABASE_URL;
    private static final String DB_URL = "db.url";
    private static final String DB_DRIVER = "db.driver";
    private static final String RESOURCE = "db.properties";
    private final Properties properties;

    public ConnectionFactory() {
        String driverName = null;
        try (InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream(RESOURCE)) {
            properties = new Properties();
            properties.load(inputStream);
            driverName = (String) properties.get(DB_DRIVER);
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            logger.fatal(e);
            throw new RuntimeException("fatal: can't registrate driver: " + driverName, e);
        } catch (IOException e) {
            logger.fatal(e);
            throw new RuntimeException("can't load properties: ", e);
        }
        DATABASE_URL = (String) properties.get(DB_URL);
    }

    public Connection newConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, properties);
    }
}
