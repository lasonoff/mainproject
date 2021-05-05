package ru.yauroff.test.mainproject.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.yauroff.test.mainproject.common.PropertiesManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class);
    private static ConnectionFactory instance;
    private Connection connection;


    private ConnectionFactory() {
        createConnection();
    }

    public static synchronized ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Ошибка при закрытии соединения", e);
            }
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    private void createConnection() {
        try {
            Class.forName(PropertiesManager.getInstance().get("datasource.driver"));
        } catch (ClassNotFoundException e) {
            logger.error("Не найден класс " + PropertiesManager.getInstance().get("datasource.driver"), e);
            System.exit(0);
        }
        String url = PropertiesManager.getInstance().get("datasource.url");
        String user = PropertiesManager.getInstance().get("datasource.username");
        String password = PropertiesManager.getInstance().get("datasource.password");
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            logger.error("Ошибка при создании соединения! ", e);
            System.exit(0);
        }
    }
}
