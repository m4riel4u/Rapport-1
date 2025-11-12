 
# Plateforme web d'aide aux choix de cours - Phase 1

## Description du projet

Développer une plateforme web intéractive qui permet aux étudiants de l'Université de Montréal de consulter et comparer les informations nécessaires pour faire des choix de cours éclaircis.

Ce site web présentera:
- Un outil de comparaison de cours
- Une interface claire et accessible
- Une recherche de cours facile par titre ou mot-clé
- Une personnalisation selon le profil de l'étudiant


Ce projet vise à centraliser plusieurs sources d'information:
- API Planifium: Catalogue officiel des programmes, cours et horaires de l'Université de Montréal
- Résultats académiques agrégés: Résultats globaux de l'ensemble des cours durant une session précise
- Avis étudiants provenant de Discord: Avis sur des cours recueillis directement auprès des étudiants via un bot Discord

## Organisation du répertoire
- docs/ : Documentation du projet 
  - besoins/ : Glossaire, analyse des besoins, cas d’utilisation, exigences et risques  
  - conception/ : Structure du système et diagrammes de conception
  - css/ : Feuilles de style 
  - application.md : Documentation décrivant le processus de développement et l'organisation du projet
  - bilan.md : Résumé des constats et apprentissages  
  - evaluation.md : Évaluation du projet et pistes d’amélioration  
  - index.md : Page d’accueil
- mkdocs.yml : Fichier de configuration du site
- requirements.txt : Liste des dépendances Python (MkDocs, extensions)
- Pipfile : Définition de l’environnement virtuel avec `pipenv`
- README.md : Brève description et organisation du projet
- .gitignore : Exclusion des fichiers non suivis par Git

## Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.

## Ressources utiles

- Documentation officielle MkDocs
- Thème Material for MkDocs

# Template de projet REST API avec Javalin - IFT2255

Ce dépôt sert de template de base pour les projets REST API avec Javalin dans le cadre du cours IFT2255 – Génie logiciel.
Il fournit une structure organisée suivant une architecture MVC (Model–View–Controller) simplifiée, prête à être utilisée pour vos travaux.

## Structure du projet

```sh
rest-api/
│
├── src/
│   ├── main/
│   │   ├── java/com/diro/ift2255/
│   │   │   ├── config/
│   │   │   │   └── Routes.java           # Définition des routes HTTP
│   │   │   │
│   │   │   ├── controller/
│   │   │   │   ├── CourseController.java # Contrôleur pour les endpoints de cours
│   │   │   │   └── UserController.java   # Contrôleur pour les endpoints utilisateurs
│   │   │   │
│   │   │   ├── model/
│   │   │   │   ├── Course.java           # Modèle représentant un cours
│   │   │   │   └── User.java             # Modèle représentant un utilisateur
│   │   │   │
│   │   │   ├── service/
│   │   │   │   ├── CourseService.java    # Logique métier liée aux cours
│   │   │   │   └── UserService.java      # Logique métier liée aux utilisateurs
│   │   │   │
│   │   │   ├── util/
│   │   │   │   ├── HttpClientAPI.java    # Client HTTP pour appels externes
│   │   │   │   ├── HttpResponse.java     # Représentation d'une réponse HTTP
│   │   │   │   ├── HttpStatus.java       # Codes de statut HTTP
│   │   │   │   ├── ResponseUtil.java     # Outils pour formater les réponses
│   │   │   │   └── ValidationUtil.java   # Méthodes utilitaires de validation
│   │   │   │
│   │   │   └── Main.java                 # Point d’entrée du serveur Javalin
│   │   │
│   │   └── resources/                    # Ressources utilisées dans le code
│   │
│   └── test/                             # Tests unitaires (JUnit)
│
└── pom.xml
```

## Architecture

Ce template suit principalement le modèle MVC :

- **Model (`model/`)** : Représentation des entités du domaine (ex. User, Course)
- **Controller (`controller/`)** : Gestion des requêtes HTTP et appels au service
- **Service (`service/`)** : Logique métier centrale
- **Util (`util/`)** : Fonctions utilitaires réutilisables (validation, réponses, etc.)
- **Config (`config/`)** : Configuration du serveur et définition des routes
- **`Main.java`** : Point d’entrée du serveur (initialise Javalin et enregistre les routes)

## Bonnes pratiques

- Respectez la séparation des responsabilités entre **Controller**, **Service** et **Model**.
- Utilisez les classes du dossier `util/` pour les validations et la gestion des réponses HTTP.
- Centralisez les routes dans `config/Routes.java` pour simplifier l’ajout de nouveaux endpoints.
- Ajoutez des **tests unitaires** pour chaque méthode de service.
- Conservez un style de code uniforme (respect du standard Java).
