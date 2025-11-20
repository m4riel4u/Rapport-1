---
title: Conception - Présentation générale
---

# Conception

Cette section présente les grandes lignes de l’architecture et des choix de conception retenus pour le projet.

## Approche utilisée

- L'architecture client-server selon le modèle MVC (Modèle-Vue-Contrôleur) sera utilisé.
- Couches:
    - Présentation (UI)
    - Logique métier
    - Base de données

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
