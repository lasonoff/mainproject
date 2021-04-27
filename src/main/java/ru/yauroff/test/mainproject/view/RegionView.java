package ru.yauroff.test.mainproject.view;

import ru.yauroff.test.mainproject.controller.RegionController;
import ru.yauroff.test.mainproject.model.Region;
import ru.yauroff.test.mainproject.repository.RegionRepository;
import ru.yauroff.test.mainproject.repository.impl.ObjectRepository;

import java.util.List;
import java.util.Scanner;

public class RegionView extends AbstractActionView<Region> implements View {
    private RegionController controller = new RegionController();
    private RegionRepository repository = ObjectRepository.getInstance().getRegionRepository();

    public RegionView() {
        super("region");
    }

    @Override
    protected void printObject(Region object) {
        System.out.println(object);
    }

    @Override
    protected void create() {
        System.out.print("Input Name:");
        Scanner in = new Scanner(System.in);
        String content = in.nextLine();
        controller.create(content);
        System.out.println("Created: Ok!");
    }

    @Override
    protected void update() {
        System.out.print("Input ID:");
        Scanner in1 = new Scanner(System.in);
        String id = in1.nextLine();
        Long idLong = Long.valueOf(id);
        Region obj = repository.findById(idLong).orElse(null);
        if (obj == null) {
            System.out.println("Error: not found region with id = " + id);
            return;
        }
        printObject(obj);
        System.out.print("Input Name:");
        Scanner in2 = new Scanner(System.in);
        String name = in2.nextLine();
        controller.update(obj, name);
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

    @Override
    protected void deleteAllAction() {
        controller.deleteAll();
    }

    @Override
    protected List<Region> findAll() {
        return repository.findAll();
    }

    @Override
    protected Long getCount() {
        return repository.count();
    }
}
