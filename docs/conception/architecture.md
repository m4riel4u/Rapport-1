---
title: Conception - Architecture
---

# Architecture du système

## Vue d’ensemble

- L'architecture REST sera retenue.
- C'est un style d'architecture utilisé principalement pour créer des API web. Cette architecture repose sur un ensemble de principes qui permet aux systèmes informatique de communiquer facilement via le protocole HTTP. De plus il est sans état, le serveur ne conserve pas d'état entre les requêtes. Les données utilisées sont représentées sous forme de ressource identifiées par des URI, et ces ressources peuvent être présentées sous différentes formes spécifiable via les en-têtes HTTP.

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