package ru.yauroff.test.mainproject.view;

import ru.yauroff.test.mainproject.controller.UserController;
import ru.yauroff.test.mainproject.model.Region;
import ru.yauroff.test.mainproject.model.Role;
import ru.yauroff.test.mainproject.model.User;
import ru.yauroff.test.mainproject.repository.RegionRepository;
import ru.yauroff.test.mainproject.repository.UserRepository;
import ru.yauroff.test.mainproject.repository.impl.ObjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserView extends AbstractActionView<User> implements View {
    private UserController controller = new UserController();
    private UserRepository repository = ObjectRepository.getInstance().getUserRepository();
    private RegionRepository regionRepository = ObjectRepository.getInstance().getRegionRepository();


    public UserView() {
        super("user");
    }

    @Override
    protected void printObject(User object) {
        System.out.println(object);
    }

    @Override
    protected void create() {
        String firstName = getFirstName();
        String lastName = getLastName();
        Region region = getRegion();
        if (region == null) {
            return;
        }
        Role role = getRole();
        if (role != null) {
            controller.create(firstName, lastName, region, role);
            System.out.println("Created: Ok!");
        }
    }

    @Override
    protected void update() {
        System.out.print("Input ID:");
        Scanner in1 = new Scanner(System.in);
        String id = in1.nextLine();
        Long idLong = Long.valueOf(id);
        User obj = repository.findById(idLong).orElse(null);
        if (obj == null) {
            System.out.println("Error: not found user with id = " + id);
            return;
        }
        printObject(obj);
        String firstName = getFirstName();
        String lastName = getLastName();
        Region region = getRegion();
        if (region == null) {
            return;
        }
        Role role = getRole();
        if (role != null) {
            controller.update(obj, firstName, lastName, region, role);
            System.out.println("Update: Ok!");
        }
    }

    private String getFirstName() {
        Scanner in = new Scanner(System.in);
        System.out.print("Input FirstName:");
        return in.nextLine();
    }

    private String getLastName() {
        Scanner in = new Scanner(System.in);
        System.out.print("Input LastName:");
        return in.nextLine();
    }

    private Region getRegion() {
        Scanner in = new Scanner(System.in);
        System.out.print("Input RegionId:");
        Long regionId;
        try {
            regionId = Long.valueOf(in.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error input RegionId!");
            return null;
        }
        Optional<Region> region = regionRepository.findById(regionId);
        if (!region.isPresent()) {
            System.out.println("Not found Region with id = " + regionId);
            return null;
        }
        return region.get();
    }

    private Role getRole() {
        Scanner in = new Scanner(System.in);
        System.out.print("Input Role (ADMIN, MODERATOR, USER):");
        Role role;
        try {
            return Role.valueOf(in.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Error input Role!");
            return null;
        }
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
