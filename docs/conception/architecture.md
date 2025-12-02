---
title: Conception - Architecture
---

# Architecture du système

## Vue d’ensemble

- L'architecture REST sera retenue.
- C'est un style d'architecture utilisé principalement pour créer des API web. Cette architecture repose sur un ensemble de principes qui permet aux systèmes informatique de communiquer facilement via le protocole HTTP. De plus il est sans état, le serveur ne conserve pas d'état entre les requêtes. Les données utilisées sont représentées sous forme de ressource identifiées par des URI, et ces ressources peuvent être présentées sous différentes formes spécifiable via les en-têtes HTTP.
- Dans le cadre de ce projet, l'architecture REST sera utilisée pour interagir avec la plateforme Planifium afin de récupérer les informations des cours nécessaires. Chaque cours sera représenté comme une ressource accessible par un URI spécifique. Les requêtes HTTP permettront de lire ou mettre à jour les données des cours de façon efficace.
- L'utilisation de REST permet de simplifer la communication entre notre application et Planifium et permet de gérer facilement les différentes ressources fournies (cours, enseignants, horaires, etc.), traitant chaque requête indépendamment.

## Composants principaux

- Liste des modules ou services :
  - Module d’authentification
  - Gestion des utilisateurs
  - Interface (frontend)
  - API (backend)
  - Base de données
  - Notifications

## Communication entre composants

- Mécanismes d’échange : appels HTTP, WebSocket, messages
- Format des données : JSON, XML

## Diagramme d’architecture (Modèle C4)
Niveau 1 : 
![alt text](<Capture d’écran 2025-10-03 131537.png>)
Niveau 2 : 
![alt text](<Capture d’écran 2025-10-03 131505.png>)