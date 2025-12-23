package com.diro.ift2255.service;

import com.diro.ift2255.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;

/**
 * La classe {@code UserService} fournit des services pour gérer les utilisateurs dans le système.
 * Elle permet de récupérer, créer, mettre à jour et supprimer des utilisateurs. 
 * Les données des utilisateurs sont stockées dans un fichier JSON local.
 * 
 * Cette classe gère également l'attribution des identifiants uniques pour chaque utilisateur.
 */
public class UserService {

    /**
     * Le chemin du fichier contenant les données des utilisateurs sous format JSON.
     */
    private static final String FILE_PATH = "users.json";

    /**
     * L'instance de {@link ObjectMapper} utilisée pour sérialiser et désérialiser les utilisateurs en JSON.
     */
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * La liste des utilisateurs chargés depuis le fichier JSON.
     */
    private final List<User> users;

    /**
     * Le prochain identifiant disponible pour un utilisateur à créer.
     */
    private int nextId;


    /**
     * Constructeur de la classe {@code UserService}.
     * Charge les utilisateurs depuis le fichier et initialise le prochain identifiant disponible.
     */
    public UserService() {
        users = loadUsersFromFile();
        nextId = users.stream()
                .mapToInt(User::getId)
                .max()
                .orElse(0) + 1;
    }

    /**
     * Récupère tous les utilisateurs enregistrés dans le système.
     * @return Une liste contenant tous les utilisateurs.
     */
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * Récupère un utilisateur par son identifiant.
     * @param id L'identifiant de l'utilisateur à récupérer.
     * @return Un objet {@code Optional} contenant l'utilisateur, s'il est trouvé, sinon vide.
     */
    public Optional<User> getUserById(int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    /**
     * Crée un nouvel utilisateur et l'ajoute à la liste des utilisateurs.
     * L'identifiant de l'utilisateur est automatiquement généré et attribué.
     * @param user L'utilisateur à créer.
     */
    public void createUser(User user) {
        user.setId(nextId++);
        users.add(user);
        saveUsersToFile();
    }

    /**
     * Met à jour un utilisateur existant en remplaçant ses données par celles fournies.
     * @param id L'identifiant de l'utilisateur à mettre à jour.
     * @param updated Les nouvelles données de l'utilisateur.
     */
    public void updateUser(int id, User updated) {
        deleteUser(id);
        updated.setId(id);
        users.add(updated);
        saveUsersToFile();
    }

    /**
     * Supprime un utilisateur par son identifiant.
     * @param id L'identifiant de l'utilisateur à supprimer.
     * @return {@code true} si l'utilisateur a été supprimé, sinon {@code false}.
     */
    public boolean deleteUser(int id) {
        boolean removed = users.removeIf(u -> u.getId() == id);
        if (removed) {
            saveUsersToFile();
        }
        return removed;
    }

    /**
     * Charge les utilisateurs depuis le fichier JSON.
     * Si le fichier n'existe pas ou si une erreur se produit, une liste vide est retournée.
     * @return Une liste contenant tous les utilisateurs chargés depuis le fichier JSON.
     */
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

    /**
     * Sauvegarde les utilisateurs dans le fichier JSON.
     * Cette méthode sérialise la liste des utilisateurs et l'écrit dans le fichier {@code FILE_PATH}.
     */
    private void saveUsersToFile() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(FILE_PATH), users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
