package ru.yauroff.test.mainproject.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class MainView extends AbstractView implements View {
    private Map<String, View> views = new HashMap<String, View>();


    public MainView() {
        views.put("3", new RegionView());
        views.put("2", new PostView());
        views.put("1", new UserView());
    }

    @Override
    public void show() {
        System.out.println("==== Main\n");
        System.out.println("0 - Exit");
        System.out.println("1 - User");
        System.out.println("2 - Post");
        System.out.println("3 - Region");
        System.out.print("Input action: ");
        doAction();
    }

    @Override
    public void doAction() {
        Scanner in = new Scanner(System.in);
        String action = in.nextLine();
        if ("0".equals(action)) {
            return;
        }
        if (views.get(action) != null) {
            views.get(action).setPrevView(this);
            views.get(action).show();
        } else {
            show();
        }
    }
}
