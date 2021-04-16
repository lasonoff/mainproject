package ru.yauroff.test.mainproject.view;

/**
 * Created by ayaurov on 15.04.2021.
 */
public interface View {
    public void show();

    public void doAction();

    public void setPrevView(View prevView);

}
