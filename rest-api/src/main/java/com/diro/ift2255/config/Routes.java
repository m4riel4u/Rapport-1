package com.diro.ift2255.config;

import com.diro.ift2255.controller.CourseController;
import com.diro.ift2255.controller.UserController;
import com.diro.ift2255.service.UserService;
import com.diro.ift2255.service.CourseService;
import com.diro.ift2255.util.HttpClientApi;

import io.javalin.Javalin;

/**
 * La classe {@code Routes} est responsable de l'enregistrement de toutes les routes dans l'application Javalin. 
 * Elle configure les points d'entrée pour les utilisateurs, les cours, ainsi que les pages web.
 */
public class Routes {

    /**
     * Enregistre toutes les routes de l'application en les associant à leurs contrôleurs respectifs.
     * Cette méthode doit être appelée au démarrage de l'application pour configurer toutes les routes
     * utilisées par l'API.
     * 
     * @param app L'instance de {@link Javalin} utilisée pour enregistrer les routes.
     */
    public static void register(Javalin app) {
        registerUserRoutes(app);
        registerCourseRoutes(app);
        registrerWebPageRoutes(app);
    }

    /**
     * Enregistre toutes les routes liées à la gestion des utilisateurs.
     * Ces routes permettent de récupérer, créer, mettre à jour, et supprimer des utilisateurs,
     * ainsi que de vérifier l'éligibilité d'un utilisateur à un cours spécifique.
     * 
     * @param app L'instance de {@link Javalin} utilisée pour enregistrer les routes.
     */
    private static void registerUserRoutes(Javalin app) {
        UserService userService = new UserService();
        UserController userController = new UserController(userService);

        app.get("/users", userController::getAllUsers);
        app.get("/users/{id}", userController::getUserById);
        app.post("/users", userController::createUser);
        app.put("/users/{id}", userController::updateUser);
        app.delete("/users/{id}", userController::deleteUser);
    }

    /**
     * Enregistre toutes les routes liées à la gestion des cours.
     * Ces routes permettent de récupérer la liste des cours, effectuer des recherches sur les cours,
     * et obtenir des informations détaillées sur un cours spécifique.
     * 
     * @param app L'instance de {@link Javalin} utilisée pour enregistrer les routes.
     */
    private static void registerCourseRoutes(Javalin app) {
        CourseService courseService = new CourseService(new HttpClientApi());
        CourseController courseController = new CourseController(courseService);

        app.get("/courses", courseController::getAllCourses);
        app.get("/courses/search", courseController::getCoursesByQuery);
        app.get("/courses/{id}", courseController::getCourseById);
        app.get("/courses/complete/{id}", courseController::getCompleteCourse);
        
    }

    /**
     * Enregistre les routes pour les pages web statiques.
     * Cette méthode permet de rediriger la route racine ("/") vers un fichier HTML.
     * 
     * @param app L'instance de {@link Javalin} utilisée pour enregistrer les routes.
     */
    private static void registrerWebPageRoutes(Javalin app) {

        app.get("/", ctx -> ctx.redirect("/index.html"));
    }
}