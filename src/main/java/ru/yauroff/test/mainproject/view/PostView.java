package ru.yauroff.test.mainproject.view;

import ru.yauroff.test.mainproject.controller.PostController;
import ru.yauroff.test.mainproject.model.Post;
import ru.yauroff.test.mainproject.model.User;
import ru.yauroff.test.mainproject.repository.PostRepository;
import ru.yauroff.test.mainproject.repository.UserRepository;
import ru.yauroff.test.mainproject.repository.impl.ObjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PostView extends AbstractActionView<Post> implements View {
    private PostController controller = new PostController();
    private PostRepository repository = ObjectRepository.getInstance().getPostRepository();
    private UserRepository userRepository = ObjectRepository.getInstance().getUserRepository();


    public PostView() {
        super("post");
    }

    @Override
    protected void printObject(Post object) {
        System.out.println(object);
    }

    @Override
    protected void create() {
        System.out.print("Input Content:");
        Scanner in = new Scanner(System.in);
        String content = in.nextLine();
        User user = getUser();
        if (user != null) {
            controller.create(content, user);
            System.out.println("Created: Ok!");
        }
    }

    @Override
    protected void update() {
        System.out.print("Input ID:");
        Scanner in1 = new Scanner(System.in);
        String id = in1.nextLine();
        Long idLong = Long.valueOf(id);
        Post obj = repository.findById(idLong).orElse(null);
        if (obj == null) {
            System.out.println("Error: not found post with id = " + id);
            return;
        }
        printObject(obj);
        System.out.print("Input Content:");
        Scanner in2 = new Scanner(System.in);
        String content = in2.nextLine();
        controller.update(obj, content);
        System.out.println("Update: Ok!");
    }

    @Override
    protected void delete() {
        System.out.print("Input ID object for delete:");
        Scanner in = new Scanner(System.in);
        String id = in.nextLine();
        Long idLong = Long.valueOf(id);
        controller.delete(idLong);
        System.out.println("Delete: Ok!");
    }

    private User getUser() {
        Scanner in = new Scanner(System.in);
        System.out.print("Input UserId:");
        Long userId;
        try {
            userId = Long.valueOf(in.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error input UserId!");
            return null;
        }
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            System.out.println("Not found User with id = " + userId);
            return null;
        }
        return user.get();
    }

    @Override
    protected void deleteAllAction() {
        controller.deleteAll();
    }

    @Override
    protected List<Post> findAll() {
        return repository.findAll();
    }

    @Override
    protected Long getCount() {
        return repository.count();
    }
}
