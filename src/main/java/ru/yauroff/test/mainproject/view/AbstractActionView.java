package ru.yauroff.test.mainproject.view;

import java.util.List;
import java.util.Scanner;


public abstract class AbstractActionView<T> extends AbstractView {
    private String nameObject;

    public AbstractActionView(String nameObject) {
        this.nameObject = nameObject;
    }

    public void show() {
        System.out.println("==== " + nameObject + "\n");
        System.out.println("0 - Previous");
        System.out.println("1 - Count");
        System.out.println("2 - Show all");
        System.out.println("3 - Create");
        System.out.println("4 - Update");
        System.out.println("5 - Delete");
        System.out.println("6 - Delete all!");
        System.out.print("Input action: ");
        doAction();
    }

    public void doAction() {
        Scanner in = new Scanner(System.in);
        String action = in.nextLine();
        if ("0".equals(action)) {
            this.prevView.show();
            return;
        } else if ("1".equals(action)) {
            this.showCount();
        } else if ("2".equals(action)) {
            this.showAll();
        } else if ("3".equals(action)) {
            this.create();
        } else if ("4".equals(action)) {
            this.update();
        } else if ("5".equals(action)) {
            this.delete();
        } else if ("6".equals(action)) {
            this.deleteAll();
        }
        show();
    }

    protected void showCount() {
        System.out.println(nameObject + " count = " + getCount());
    }

    protected void showAll() {
        System.out.println("All " + nameObject);
        List<T> allObjects = findAll();
        allObjects.forEach(x -> printObject(x));
    }

    abstract protected void printObject(T object);

    abstract protected void create();

    abstract protected void update();

    abstract protected void delete();

    protected void deleteAll() {
        System.out.print("Delete all " + nameObject + " objects: Y/N?");
        Scanner in = new Scanner(System.in);
        String action = in.nextLine();
        if ("Y".equals(action) || "y".equals(action)) {
            deleteAllAction();
            System.out.println("Deleted all: Ok!");
        }
    }

    abstract protected void deleteAllAction();

    abstract protected List<T> findAll();

    abstract protected Long getCount();

}
