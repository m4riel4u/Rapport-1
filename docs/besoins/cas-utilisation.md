---
title: Analyse des besoins - Cas d'utilisation
---

# Cas d'utilisation

## Vue d’ensemble

TODO: Introduction aux cas d’utilisation du système.
![alt text](<Capture d’écran 2025-10-06 150358.png>)
## Liste des cas d’utilisation

| ID | Nom | Acteurs principaux | Description |
|----|-----|---------------------|-------------|
| CU01 | Connexion | Utilisateur | L'utilisateur se connecte à la plateforme. |
| CU02 | Recherche de cours | Utilisateur | L'étudiant peut effectuer une recherche de cours sur l'application. |
| CU03 | Personnalisation du profil | Utilisateur | L'étudiant peut configurer ses préférences de cours pour obtenir des suggestions personnalisées. |
| CU04 | Comparaison de cours | Utilisateur | L'étudiant peut comparer des cours pour estimer la charge total de travail d'une combinaison. |
| CU05 | Création d'un compte | Utilisateur | L'utilisateur peut se créer un compte s'il n'en a pas déjà un. |


## Détail

### CU01 - Connexion

**Acteurs** : 

- Utilisateur (principal)

**Préconditions** : 

- L'étudiant possède un compte activé et valide.  
- Le système d'authentification est adapté au compte de l'étudiant.

**PostConditions** :

- Le système a pu vérifier l'authentification de l'étudiant et le redirige vers le menu principal.

**Déclencheur** : 

- L'étudiant a accès à la page de connexion pour saisir son identifiant et son mot de passe.

**Dépendances** : 

- Système d'authentification (Authentificator)

**But** :

- L'étudiant a accès à son compte avec ses données enregistrées et protégées.

### CU02 - Rechercher un cours

**Acteurs** :

- Utilisateur 

**Préconditions** :

- L'étudiant et connecté à son compte.  
- La base de donnée sur les structures des programmes est synchronisée avec Planifium.

**PostConditions** :

- Une liste de cours s'affiche selon les critères de recherche de l'étudiant.

**Déclencheur** :

- L'étudiant écrit des mots-clés ou un titre de cours dans la barre de recherche sur l'application.

**Dépendances** :

- API Planifium

**But** :

- L'étudiant peut découvrir une variété de cours qui correspondent à sa recherche.

### CU03 - Personnalisation du profil

**Acteurs** :

- Utilisateur

**Préconditions** :

- L'étudiant est connecté à son compte avec toutes ses informations valides.

**PostConditions** :

- Les préférences sur les choix de cours et des données personnelles sont spécifiés sur le profil de l'étudiant. 
- Les préférences présents dans le compte sont utilisés pour filtrer les suggestions des programmes de cours donnés.

**Déclencheur** :

- L'étudiant a accès à la section de 'Personnalisation du profil' dans son profil.

**Dépendances** :

- Base de données personnelles des étudiants sauvegardée  
- Moteur de suggestions qui suit les critères du profil de l'étudiant

**But** :

- Améliorer la précision des suggestions de cours en tenant compte de son profil personnalisé de l'étudiant

### CU04 - Comparaison de cours

**Acteurs** :

- Utilisateur

**Préconditions** :

- L'étudiant sélectionne au moins deux cours à comparer la charge de travail.

**PostConditions** :

- Les données des cours choisis s'affichent avec les données comparées.

**Déclencheur** :

- L'étudiant se dirige dans l'onglet de 'Comparaison de cours' dans l'application et sélectionne les cours au choix.

**Dépendances** :

- Données des cours sont synchronisées avec le Planifium  
- Les préférences de la charge de travail dans le profil de l'étudiant

**But** :

- Comparaison des cours pour aider l'étudiant à évaluer la charge de travail sur une combinaison de cours.


### CU05 - Création d'un compte

**Acteurs** :

- Utilisateur 

**Préconditions** :

- Vérifier que l'utilisateur n'a pas déjà un compte associé à son identifiant.

**PostConditions** :

- Le système a pu créer le compte de l'utilisateur et le redirige vers le menu principal.

**Déclencheur** :

- L'étudiant a accès à la page de création d'un compte pour créer son profil.

**Dépendances** :

- Aucune

**But** :

- L'utilisateur peut se créer un compte s'il n'en a pas déjà un. 