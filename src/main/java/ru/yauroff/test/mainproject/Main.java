package ru.yauroff.test.mainproject;

import ru.yauroff.test.mainproject.common.PropertiesManager;
import ru.yauroff.test.mainproject.repository.DbCreator;
import ru.yauroff.test.mainproject.view.MainView;

public class Main {

    public static void main(String[] args) {
        // Загрузка настроек
        PropertiesManager.getInstance();
        // Создание структуры базы
        DbCreator.createTables();
        // Запуск приложения2
        MainView mv = new MainView();
        mv.show();
    }
}
