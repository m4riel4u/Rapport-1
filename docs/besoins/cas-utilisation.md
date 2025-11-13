---
title: Analyse des besoins - Cas d'utilisation
---

# Cas d'utilisation

## Vue d’ensemble

![alt text](<Capture d’écran 2025-10-03 111800.png>)
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

- L'étudiant ouvre la page de connexion pour saisir son identifiant et son mot de passe.

**Dépendances** : 

- Système d'authentification (Authentificator)

**But** :

- L'étudiant se connecte à son compte avec ses données enregistrées et protégées.

#### Scénario:
- Scénario principal
    1. L'utilisateur ouvre l'application.
    2. L'application affiche la page de connexion.
    3. L'utilisateur entre son adresse couriiel et son mot de passe.
    4. L'utilisateur clique sur « Se connecter ».
    5. La plateforme vérifie les identifiants.
    6. La plateforme affiche la page d’accueil de l’utilisateur.

- Scénarios alternatifs
    1. Le mot de passe incorrect
        1. L’utilisateur entre son courriel et un mauvais mot de passe.
        2. L’utilisateur clique sur « Se connecter ».
        3. La plateforme indique que les informations sont invalides.
        4. L’utilisateur peut réessayer ou demander une récupération du mot de passe.
    2. Les champs sont vides
        1. L’utilisateur clique sur « Se connecter » sans remplir les champs.
        2. La plateforme affiche un message d’erreur demandant de compléter les informations.


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

#### Scénario:
- Scénario principal
    1. L’utilisateur accède à la barre de recherche sur la page principale.
    2. L’utilisateur saisit un mot-clé.
    3. L’utilisateur clique sur « Rechercher ».
    4. L’application fait appel à la base de données des cours.
    5. La plateforme affiche une liste de cours correspondant aux critères.
    6. L’utilisateur sélectionne un cours pour afficher les détails.

- Scénarios alternatifs
    1. Aucun résultat trouvé
        1. L’utilisateur entre un mot-clé qui ne correspond à aucun cours.
        2. La plateforme affiche : « Aucun cours trouvé ».
        3. L’utilisateur peut modifier sa recherche.
    2. Les informations de recherche sont incomplètes
        1. L’utilisateur clique sur « Rechercher » sans mot-clé.
        2. La plateforme demande d’entrer un critère de recherche.

### CU03 - Personnalisation du profil

**Acteurs** :

- Utilisateur

**Préconditions** :

- L'étudiant est connecté à son compte avec toutes ses informations valides.

**PostConditions** :

- Les préférences sur les choix de cours et des données personnelles sont spécifiés sur le profil de l'étudiant. 
- Les préférences présents dans le compte sont utilisés pour filtrer les suggestions des programmes de cours donnés.

**Déclencheur** :

- L'étudiant ouvre la section de 'Personnalisation du profil' dans son profil.

**Dépendances** :

- Base de données personnelles des étudiants sauvegardée.
- Moteur de suggestions qui suit les critères du profil de l'étudiant.

**But** :

- Améliorer la précision des suggestions de cours en tenant compte de son profil personnalisé de l'étudiant

#### Scénario:
- Scénario principal
    1. L’utilisateur ouvre la section « Profil ».
    2. La plateforme affiche les informations actuelles du profil.
    3. L’utilisateur modifie ses préférences.
    4. L’utilisateur clique sur « Enregistrer ».
    5. La plateforme met à jour les informations dans la base de données.
    6. Un message confirme que la personnalisation est sauvegardée.

- Scénario alternatif
    1. L’utilisateur tente d’enregistrer un champ obligatoire vide.
    2. La plateforme affiche un message d’erreur.
    3. L’utilisateur corrige les informations et réessaie.

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

- Données des cours sont synchronisées avec le Planifium.
- Les préférences de la charge de travail dans le profil de l'étudiant.

**But** :

- Comparaison des cours pour aider l'étudiant à évaluer la charge de travail sur une combinaison de cours.

#### Scénario:
- Scénario principal
    1. L’utilisateur effectue une recherche ou navigue parmi les cours.
    2. L’utilisateur sélectionne deux cours à comparer.
    3. L’utilisateur clique sur « Comparer ».
    4. La plateforme analyse les charges de travail, horaires, et critères similaires.
    5. Un tableau comparatif est affiché.
    6. L’utilisateur consulte les différences pour choisir une combinaison personnalisée.

- Scénario alternatif
    1. L’utilisateur clique sur « Comparer » avec seulement un cours sélectionné.
    2. La plateforme affiche un message demandant de sélectionner au moins deux cours.

### CU05 - Création d'un compte

**Acteurs** :

- Utilisateur 

**Préconditions** :

- Vérifier que l'utilisateur n'a pas déjà un compte associé à son identifiant.

**PostConditions** :

- Le système a pu créer le compte de l'utilisateur et le redirige vers le menu principal.

**Déclencheur** :

- L'étudiant ouvre la page de création d'un compte pour créer son profil.

**Dépendances** :

- Aucune

**But** :

- L'utilisateur peut se créer un compte s'il n'en a pas déjà un. 

#### Scénario:
- Scénario principal
    1. L’utilisateur ouvre l’application et clique sur « Créer un compte ».
    2. La plateforme affiche le formulaire d’inscription.
    3. L’utilisateur remplit les champs requis.
    4. L’utilisateur clique sur « S’inscrire ».
    5. La plateforme vérifie que les informations sont valides.
    6. Le compte est créé et un message de confirmation est affiché.
    7. L’utilisateur est redirigé vers la page d’accueil ou la page de connexion.

- Scénarios alternatifs
    1. Le courriel est déjà utilisé
        1. L’utilisateur remplit le formulaire avec un courriel existant.
        2. La plateforme affiche : « Ce courriel est déjà associé à un compte ».
        3. L’utilisateur doit entrer une autre adresse ou se connecter.
    2. Des champs obligatoires sont manquants
        1. L’utilisateur tente d’envoyer le formulaire incomplet.
        2. La plateforme affiche un message d’erreur.
        3. L’utilisateur complète les champs manquants.
    