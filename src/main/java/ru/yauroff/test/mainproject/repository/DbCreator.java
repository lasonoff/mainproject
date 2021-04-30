package ru.yauroff.test.mainproject.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbCreator {
    private static final Logger logger = LogManager.getLogger(DbCreator.class);

    private static String createRegionSql = "CREATE TABLE IF NOT EXISTS `region` (" +
            "    `id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT," +
            "    `name` CHAR(60) NOT NULL," +
            "    PRIMARY KEY (`id`)" +
            ");";

    private static String createUserSql = "CREATE TABLE IF NOT EXISTS `user` (" +
            "    `id` SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL," +
            "    `firstName` CHAR(60) NOT NULL," +
            "    `lastName` CHAR(60) NOT NULL," +
            "    `role` CHAR(10) NOT NULL," +
            "    `regionId` SMALLINT UNSIGNED NOT NULL," +
            "    PRIMARY KEY (`id`)," +
            "    CONSTRAINT `fk_user_region` FOREIGN KEY (`regionId`)" +
            "        REFERENCES `region` (`id`)" +
            "        ON DELETE RESTRICT ON UPDATE CASCADE" +
            ");";

    private static String createPostSql = "CREATE TABLE IF NOT EXISTS post (" +
            "    `id` SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL," +
            "    `content` CHAR(255) NOT NULL," +
            "    `userId` SMALLINT UNSIGNED NOT NULL," +
            "    `created` DATE NOT NULL," +
            "    `updated` DATE NOT NULL," +
            "    PRIMARY KEY (`id`)," +
            "    CONSTRAINT `fk_post_user` FOREIGN KEY (`userId`)" +
            "        REFERENCES `user` (`id`)" +
            "        ON DELETE RESTRICT ON UPDATE CASCADE" +
            ");";

    public static void createTables() {
        try (Connection connection = ConnectionFactory.getConnection()) {
            connection.setAutoCommit(false);

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createRegionSql);
                statement.executeUpdate(createUserSql);
                statement.executeUpdate(createPostSql);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Ошибка при создании структуры базы данных!", e);
                System.exit(0);
            }

        } catch (SQLException e) {
            logger.error("Ошибка при получении соеденения с БД", e);
            System.exit(0);
        }
    }
}
