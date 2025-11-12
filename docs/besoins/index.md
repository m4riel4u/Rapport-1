---
title: Analyse des besoins - Présentation générale
---

# Présentation du projet

## Méthodologie pour la cueillette des données
- Brainstorming  

## Description du domaine

Le choix de cours est une étape cruciale dans un parcours académique, car c'est cette combinaison d'apprentissages qui forme l'étudiant et le propulse vers l'obtention de son diplome. Par contre, cette tâche peut vite devenir un vrai casse-tête, car plusieurs contraintes interviennent lors de ce choix : les intérêts personnels de l'étudiant, son éligibilité au cours, la difficulté des cours, la charge de travail, les prérequis nécessaires ainsi que les ambitions de l'étudiant (orienté son parcous vers des études au cycle supérieur ou vers le marché du travail).

### Fonctionnement
Oriento est un outil numérique permettant aux utilisateurs de consulter les horaires et description de cours, comparer ceux-ci ainsi qu'offrir une affichage personnalisé selon chaque utilisateur. Cette plateforme combine des données provenant de sources officielles (Planifium, résultats académiques agrégés) et des avis personnels provenant des élèves via la plateforme Discord. L'interface est clair et concis pour pourvoir faciliter et adapter la planification du parcours académique selon la situation de chaque utilisateur.

### Acteurs
#### Acteurs principaux
Étudiants de l'Université de Montréal:     
- Étudiants de 1er cycle  
- Étudiants de cycles supérieurs  
- Étudiants internationaux  
- Étudiants en échange  
  
Équipe d'enseignement:  
- Professeurs  
- Chargé de cours  
- Auxiliaires de cours  
  
Administration facultaire:  
- TGDE  
- Responsables de programmes   
- Doyens de programmes 
#### Acteurs secondaires
- bot Discord
- API planifium 

### Dépendances
API Planifium  
Résultats académiques agrégés  
Discord  
Forum  
Réseau informatique de l'UdeM

## Hypothèses et contraintes
### Hypothèses
- Les étudiants fourniront assez d'avis pour au moins atteindre le seuil minimal  
- Les données officielles sont fiables et actualisées selon l'année et la session  

### Contraintes
#### Contrainte légale  

- Loi 25: Garder la confidentialité et protéger les données  

#### Contrainte du logiciel  

- Les API intégrées doivent suivre les protocoles REST et être accessibles via HTTPS. 

#### Contrainte temporelle

- Le projet doit être terminé avant le 23 décembre 2025.

- Les tests utilisateurs doivent être réalisés pour le 23 novembre 2025.

#### Contrainte organisationnelles 

- L'équipe de développement se limite à 4 personnes.

- L'équipe de développement doit contacter les clients au moins 5 fois avant la livraison finale.