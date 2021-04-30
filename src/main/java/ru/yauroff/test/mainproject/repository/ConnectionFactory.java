package ru.yauroff.test.mainproject.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.yauroff.test.mainproject.common.PropertiesManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class);

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(PropertiesManager.getInstance().get("datasource.driver"));
        } catch (ClassNotFoundException e) {
            logger.error("Не найден класс " + PropertiesManager.getInstance().get("datasource.driver"));
            System.exit(0);
        }
        String url = PropertiesManager.getInstance().get("datasource.url");
        String user = PropertiesManager.getInstance().get("datasource.username");
        String password = PropertiesManager.getInstance().get("datasource.password");
        return DriverManager.getConnection(url, user, password);
    }
}
