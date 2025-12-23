package com.diro.ift2255;

import io.javalin.Javalin;

import com.diro.ift2255.config.Routes;

/**
 * La classe {@code Main} est le point d'entrée de l'application.
 * Elle initialise et configure l'application web en utilisant Javalin, un framework léger pour les applications web en Java.
 * 
 * Cette classe contient la méthode {@link #main(String[])} qui configure l'application et lance le serveur web.
 * Elle définit la configuration du serveur, enregistre les routes pour les différents endpoints et démarre le serveur.
 * 
 * Le serveur utilise par défaut le type de contenu JSON pour toutes les réponses HTTP et permet de servir des fichiers statiques (HTML, CSS, etc.).
 */
public class Main {

    /**
     * Le point d'entrée de l'application.
     * Cette méthode configure le serveur Javalin, enregistre les routes de l'application et démarre le serveur web.
     * 
     * 1. Crée une instance de {@link Javalin} avec une configuration personnalisée.
     * 2. Définit le type de contenu par défaut des réponses HTTP sur {@code application/json}.
     * 3. Ajoute un répertoire pour les fichiers statiques (HTML, CSS) sous {@code "public"}.
     * 4. Enregistre les routes de l'application via la classe {@link Routes}.
     * 5. Démarre le serveur web sur le port {@code 7070}.
     * 
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        // Crée une instance de Javalin avec une configuration personnalisée
        // Ici, on définit le type de contenu par défaut des réponses HTTP en JSON
        Javalin app = Javalin.create(config -> {
            config.http.defaultContentType = "application/json";
            config.staticFiles.add("public"); //pour les pages HTML
        });

        // Enregistre toutes les routes de l'application
        Routes.register(app);

        // Démarre le serveur sur le port 7070 (choix arbitraire)
        app.start(7070);
        System.out.println("Serveur démarré sur http://localhost:7070");
    }
}