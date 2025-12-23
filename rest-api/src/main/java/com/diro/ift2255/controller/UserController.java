package com.diro.ift2255.controller;

import java.util.List;
import java.util.Optional;

import com.diro.ift2255.model.User;
import com.diro.ift2255.service.UserService;
import com.diro.ift2255.util.ResponseUtil;
import com.diro.ift2255.util.ValidationUtil;

import io.javalin.http.Context;

/**
 * La classe {@code UserController} gère toutes les actions liées aux utilisateurs dans l'application.
 * Il englobe des méthodes pour récupérer, créer, mettre à jour et supprimer des utilisateurs.
 * 
 * Ce contrôleur interagit avec le service {@link UserService} qui contient la logique métier pour manipuler les utilisateurs.
 */
public class UserController {
    // Service qui contient la logique métier pour la manipulation des utilisateurs (users) et la communication avec les services externes
    private final UserService service;
    
    /**
     * Crée une instance de {@code UserController} avec le service fourni.
     * Ce constructeur permet au contrôleur de manipuler les utilisateurs via le service {@code UserService}.
     * 
     * @param service Le service qui gère la logique métier liée aux utilisateurs.
     */
    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * Récupère la liste de tous les utilisateurs.
     * Cette méthode retourne tous les utilisateurs existants dans la base de données sous forme de réponse JSON.
     * @param ctx Contexte Javalin représentant la requête et la réponse HTTP
     */
    public void getAllUsers(Context ctx) {
        List<User> users = service.getAllUsers();
        ctx.json(users);
    }

    /**
     * Récupère un utilisateur spécifique par son ID.
     * Si l'utilisateur est trouvé, il est retourné sous forme de réponse JSON. Sinon, une erreur 404 est retournée.
     * @param ctx Contexte Javalin représentant la requête et la réponse HTTP
     */
    public void getUserById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));

        Optional<User> user = service.getUserById(id);
        if (user.isPresent()) {
            ctx.json(user.get());
        } else {
            ctx.status(404).json(ResponseUtil.formatError("Aucun utilisateur ne correspond à l'ID: " + id));
        }
    }

    /**
     * Crée un nouvel utilisateur avec les données passées dans le body de la requête.
     * Cette méthode vérifie le format de l'email de l'utilisateur et si l'email est valide, l'utilisateur est créé.
     * Si l'email est invalide, une réponse avec une erreur 400 est renvoyée.
     * @param ctx Contexte Javalin représentant la requête et la réponse HTTP
     */
    public void createUser(Context ctx) {
        User user = ctx.bodyAsClass(User.class);
        if (!ValidationUtil.isEmail(user.getEmail())) {
            ctx.status(400).json("Invalid email format");
            return;
        }
        service.createUser(user);
        ctx.status(201).json(user);
    }

    /**
     * Met à jour un utilisateur existant avec les données passées dans le body de la requête.
     * Cette méthode prend l'ID de l'utilisateur à mettre à jour et les nouvelles données pour cet utilisateur.
     * @param ctx Contexte Javalin représentant la requête et la réponse HTTP
     */
    public void updateUser(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        User updated = ctx.bodyAsClass(User.class);
        service.updateUser(id, updated);
        ctx.json(updated);
    }

    /**
     * Supprime un utilisateur existant à partir de son ID.
     * Si l'utilisateur est trouvé et supprimé avec succès, une réponse 204 est envoyée. 
     * Sinon, une réponse avec une erreur 404 est renvoyée.
     * @param ctx Contexte Javalin représentant la requête et la réponse HTTP
     */
    public void deleteUser(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        service.deleteUser(id);
        ctx.status(204);
    }
}
