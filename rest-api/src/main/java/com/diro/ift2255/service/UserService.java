package com.diro.ift2255.service;

import com.diro.ift2255.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;

public class UserService {

    private static final String FILE_PATH = "users.json";

    private final ObjectMapper mapper = new ObjectMapper();
    private final List<User> users;
    private int nextId;

    public UserService() {
        users = loadUsersFromFile();
        nextId = users.stream()
                .mapToInt(User::getId)
                .max()
                .orElse(0) + 1;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public Optional<User> getUserById(int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    public void createUser(User user) {
        user.setId(nextId++);
        users.add(user);
        saveUsersToFile();
    }

    public void updateUser(int id, User updated) {
        deleteUser(id);
        updated.setId(id);
        users.add(updated);
        saveUsersToFile();
    }

    public boolean deleteUser(int id) {
        boolean removed = users.removeIf(u -> u.getId() == id);
        if (removed) {
            saveUsersToFile();
        }
        return removed;
    }


    private List<User> loadUsersFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return mapper.readValue(file, new TypeReference<List<User>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveUsersToFile() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(new File(FILE_PATH), users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
