package ru.yauroff.test.mainproject.view;

import ru.yauroff.test.mainproject.controller.UserController;
import ru.yauroff.test.mainproject.model.User;
import ru.yauroff.test.mainproject.repository.UserRepository;
import ru.yauroff.test.mainproject.repository.impl.JsonUserRepository;

import java.util.List;
import java.util.Scanner;


public class UserView extends AbstractActionView<User> implements View {
    private UserController controller;
    private UserRepository repository;
    // TOOD: Настройки перенести в properties
    private String pathToJson = System.getProperty("user.dir") + System.getProperty("file" + ".separator") + "user" +
            ".json";


    public UserView() {
        super("user");
        repository = new JsonUserRepository(pathToJson);
        controller = new UserController(repository);
    }

    @Override
    protected void printObject(User object) {
        System.out.println(object);
    }

    @Override
    protected void create() {
        Scanner in = new Scanner(System.in);
        System.out.println("\n");
        System.out.print("Input FirstName:");
        String firstName = in.nextLine();
        System.out.print("Input LastName:");
        String lastName = in.nextLine();
        System.out.print("Input RegionId:");
        String regionId = in.nextLine();
        System.out.print("Input Role (0- ADMIN, 1- MODERATOR, 2- USER:");
        //Role role = new Role(in.nextLine());
        //controller.create(content);
        System.out.println("\n");
        System.out.println("Created: Ok!");
    }

    @Override
    protected void update() {

    }

    @Override
    protected void delete() {

    }

    @Override
    protected void deleteAllAction() {
        controller.deleteAll();
    }

    @Override
    protected List<User> findAll() {
        return repository.findAll();
    }

    @Override
    protected Long getCount() {
        return repository.count();
    }
}
