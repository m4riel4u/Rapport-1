 
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
