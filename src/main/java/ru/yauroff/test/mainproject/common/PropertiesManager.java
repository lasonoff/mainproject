package ru.yauroff.test.mainproject.common;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
    private static PropertiesManager instance;
    private String filename = "mainproject.properties";
    private Properties properties;

    private PropertiesManager() {
        loadProperties();
    }

    public static synchronized PropertiesManager getInstance() {
        if (instance == null) {
            instance = new PropertiesManager();
        }
        return instance;
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {

            properties = new Properties();

            if (input == null) {
                System.out.println("Не найден файл с настройками " + filename);
                System.exit(0);
            }
            properties.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(0);
        }
    }

}
