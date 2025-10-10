---
title: Analyse des besoins - Présentation générale
---

# Présentation du projet

## Méthodologie pour la cueillette des données
- Brainstorming  

## Description du domaine

### Fonctionnement
Cet outil numérique permet aux utilisateurs de consulter les horaires et description de cours, comparer ceux-ci ainsi qu'offrir une affichage personnalisé selon chaque utilisateur. Cette plateforme combine des données provenant de sources officielles (Planifium, résultats académiques agrégés) et des avis personnels provenant des élèves via la plateforme Discord. L'interface est clair et concis pour pourvoir faciliter et adapter la planification du parcours académique selon la situation de chaque étudiant.

### Acteurs
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
Contrainte légale  
- Loi 25: Garder la confidentialité et protéger les données  
Contrainte du logiciel  
- La plateforme doit être hébergée sous forme de site web statique (MkDocs + GitHub Pages).  
- Les API intégrées doivent suivre les protocoles REST et être accessibles via HTTPS.  
Contrainte de l'environnement  
- L’application doit être compatible avec les postes informatiques et réseaux de l’Université de Montréal.  
- Le système doit fonctionner sur les navigateurs récents (Chrome, Firefox, Edge, Safari).  
- Le service doit être accessible depuis n’importe quel appareil (ordinateur portable, tablette, mobile).  
Contrainte de performance  
- Le site doit se charger en moins de 3 secondes sur une connexion Internet standard (≥ 2 Mbps).  
- L’interface doit supporter tous les cours sans ralentissement notable.  