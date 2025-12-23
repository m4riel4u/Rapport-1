# Plateforme web d'aide aux choix de cours 

## Description du projet ORIENTO

Le projet **"Oriento"** consiste à développer une **plateforme web interactive** qui permet aux étudiants de l'université de Montréal de consulter et comparer les informations nécessaires pour faire des choix de cours éclaircis.

Ce site web présentera:
- Un outil de comparaison de cours
- Une interface claire et accessible
- Une recherche de cours facile par titre ou mot-clé
- Une personnalisation selon le profil de l'étudiant

Ce projet vise à centraliser plusieurs sources d'information:
- API Planifium: Catalogue officiel des programmes, cours et horaires de l'Université de Montréal
- Résultats académiques agrégés: Résultats globaux de l'ensemble des cours durant une session précise
- Avis étudiants provenant de Discord: Avis sur des cours recueillis directement auprès des étudiants via un bot Discord

## Fonctionnalités de l'application par rôle

Utilisateur:
- Créer un compte: Créer son compte et personnaliser son profil selon ses intérêt et son parcours scolaire.
- Rechercher des cours: Chercher des cours par titre, code, programme, etc.
- Comparer des cours: Comparer les cours en fonction de différents critères sélectionnés.
- Voir les recommandations de cours: Voir le cours recommandés selon la personalisation du profil.
- Consulter des avis: Consulter les avis des autres étudiant sur un cours désiré.
- Ajouter des avis: Laisser un avis sur un cours qu'il a suivi.
- Vérifier l'éligibilité: Vérifier son éligibilité à un cours, si les prérequis sont tous attents.
- Finaliser son horaire: Mettre ensemble les cours sélectionnés pour un semestre.

API Planifium:
- L'application utilise l'API Planifium pour récupérer les informations nécessaires aux choix de cours.

Discord bot:
- Mettre à jour les avis des utilisateurs.

## Organisation du répertoire
- docs/ : Documentation du projet 
  - besoins/ : Glossaire, analyse des besoins, cas d’utilisation, exigences et risques  
  - conception/ : Structure du système et diagrammes de conception
  - css/ : Feuilles de style 
  - application.md : Documentation décrivant le processus de développement et l'organisation du projet
  - bilan.md : Résumé des constats et apprentissages  
  - evaluation.md : Évaluation du projet et pistes d’amélioration  
  - index.md : Page d’accueil
- rest-api/: Implémentation du code et tests unitaires
  - pom.xml : Code nécessaire à l'exécution de maven
  - src/: Code source
    - main/ : Ensemble de l'implémentation
      - java/com/diro/ift2255/ : Implémentation du code nécessaire 
        - config/ : Configuration des routes Javalin nécessaires
          - Routes.java
        - controller/ : Gestion des requêtes des utilisateurs et réponses en retour
          - CourseController.java
          - UserController.java
        - model/ : Entitées principales utilisées dans l'application
          - Avis.java
          - Course.java
          - EligibilityResult.java
          - Schedule.java
          - User.java
        - service/ : Logique métier de l'application
          - CourseService.java
          - UserService.java
        - util/ : Utilités pour des fonctionnalités spécifiques
          - HttpClientApi.java
          - HttpClientApiResponse.java
          - HttpStatus.java
          - ResponseUtil.java
          - ValidationUtil.java
	- Main.java : Point d'entré de l'application
      - ressources/: Page web formant le front-end de l'application
	-  public/:
	  - compare.html
	  - ensemble.html
	  - ensemble.js
	  - index.html
	  - profil.html
	  - search.html
	- avis.log
	- historique_cours_prog_117510.csv
    - test/
      -  java/com/diro/ift2255/:
        - service/ : Tests unitaires pour les classes services
	  - CourseServiceTest.java
	  - UserServiceTest.java
        - util/ : Tests unitaires pour les classes utils
	  - ResponseUtilTest.java
	  - ValidationUtilTest.java
- mkdocs.yml : Fichier de configuration du site
- requirements.txt : Liste des dépendances Python (MkDocs, extensions)
- Pipfile : Définition de l’environnement virtuel avec `pipenv`
- README.md : Brève description et organisation du projet
- .gitignore : Exclusion des fichiers non suivis par Git
- users.json : Fichier pour sauvegarder les profils utilisateurs

## Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.

## Installation de l'application

Prérequis : 
- Java 17+
- Python 3.10+ (pour le Discord bot)
- Maven 3+

## Exécution de l'application

- Lancer le backend avec `maven`
  > mvn clean package
  > java -jar target/Oriento.jar

- Lancer le bot
  > python Oriento.py

  Le backend Java va gérer l'API et la logique côté serveur, tandis que le bot Discord collectera et affichera les avis des étudiants en temps réel.

## Tester l'application

- Les tests sont effectués dans le répertoire src/test/
  > mvn compile
  > run main
  > mvn test

## Serveur Discord

[Oriento](discord.gg/VMtfavu5ZR)

## Documentation JavaDoc


