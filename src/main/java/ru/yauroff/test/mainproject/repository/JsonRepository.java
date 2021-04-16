package ru.yauroff.test.mainproject.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

abstract class JsonRepository<T> {
    private String filePath;
    private Class<T> type;

    public JsonRepository(String filePath) {
        this.filePath = filePath;
    }

    protected void setType(Class<T> type) {
        this.type = type;
    }

    public long count() {
        return readAll().size();
    }

    protected List<T> readAll() {
        List<T> res = new ArrayList<>();
        Gson gson = new Gson();
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(filePath);
        } catch ( FileNotFoundException e ) {
            return res;
        }
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        try {
            Type typeT = TypeToken.getParameterized(List.class, type).getType();
            res = gson.fromJson(streamReader, typeT);
            streamReader.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return res;
    }

    protected void write(T object) {
        List<T> allObjects = readAll();
        allObjects.add(object);
        writeAll(allObjects);
    }

    protected void writeAll(List<T> objects) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(filePath);
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
            return;
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets
                .UTF_8));
        try {
            gson.toJson(objects, objects.getClass(), bufferedWriter);
            bufferedWriter.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
