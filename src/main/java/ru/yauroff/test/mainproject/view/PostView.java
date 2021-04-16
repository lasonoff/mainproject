package ru.yauroff.test.mainproject.view;

import ru.yauroff.test.mainproject.repository.JsonPostRepository;
import ru.yauroff.test.mainproject.repository.PostRepository;

import java.util.Scanner;

/**
 * Created by ayaurov on 15.04.2021.
 */
public class PostView extends AbstractView implements View {
    private PostRepository repository = new JsonPostRepository("D:\\post.json");

    @Override
    public void show() {
        System.out.println("\n==== Post \n");
        System.out.println("0 - Previous");
        System.out.println("1 - Count");
        System.out.println("2 - Create");
        System.out.println("3 - Update");
        System.out.println("4 - Delete");
        System.out.println("5 - Delete all!");
        System.out.print("Введите действие: ");
        doAction();
    }

    @Override
    public void doAction() {
        Scanner in = new Scanner(System.in);
        String action = in.nextLine();
        if ("0".equals(action)) {
            this.prevView.show();
            return;
        } else if ("1".equals(action)) {
            this.showCount();
        }
        show();
    }

    public void showCount() {
        System.out.println("Post count = " + repository.count());
    }
}
