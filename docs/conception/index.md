---
title: Conception - Présentation générale
---

# Conception

Cette section présente les grandes lignes de l’architecture et des choix de conception retenus pour le projet.

## Approche utilisée

- L'architecture client-server selon le modèle MVC (Modèle-Vue-Contrôleur) sera utilisé.
- Couches:
    - Présentation (UI) - Vue: Responsable de l'affichage des informations aux utilisateurs et de la collecte de leurs interactions.
    - Logique métier - Contrôleur: Traite les requêtes des utilisateurs, coordonne les actions et communique avec la base de donnée pour récupérer ou modifier les informations des cours.
    - Base de données - Modèle: Responsable du stockage et de l'organisation des données de façon sécurisée et structurée. 
- Cet approche permet de séparer clairement les responsabilités: la gestion des données, la logique applicative et l'interface utilisateur sont découplées, ce qui facilitera la maintenance, les évolutions futures et les tests unitaires des différentes composantes. Ayant chaque couche (Modèle-Vue-Contrôleur) indépendant avec son rôle clair et unique, le modèle MVC permet de réduire le couplage et d'améliorer la cohérence.

## Contraintes prises en compte

- Contraintes techniques (hébergement, langage, base de données):
    - Le système doit être hébergé sur un server ou un cloud
    - Limiter le stokage des et la taille des bases de données
    - Utiliser le langage Java
    - Les systèmes doient pourvoir interagir entre eux via API REST
- Contraintes imposées par les exigences (ex. : sécurité, performances):
    - Athentification obligatoire
    - Avoir la capacité à gérer un certain nombre d'utilisateur simultanés
    - Avoir le système toujours disponible
    - L'architecture doit permettre d'ajouter facilement de nouvelle fonctionnalités dans le besoin
    - Avoir des composantes modulaires pour faciliter les mises à jour
