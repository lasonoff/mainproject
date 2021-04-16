package ru.yauroff.test.mainproject.view;

/**
 * Created by ayaurov on 15.04.2021.
 */
public class AbstractView {
    protected View prevView;

    public void setPrevView(View prevView) {
        this.prevView = prevView;
    }
}
