---
title: Analyse des besoins - Exigences
---

# Exigences

## Exigences fonctionnelles

- [ ] EF1 : L'utilisateur peut créer un nouveau compte étudiant.
- [ ] EF2 : L'utilisateur peut effectuer une nouvelle connexion à un compte étudiant.
- [ ] EF3 : L'utilisateur peut personnaliser son profil en modifiant ses préférences.
- [ ] EF4 : L'étudiant peut effectuer des recherches des cours spécifiques.
- [ ] EF5 : L'étudiant peut obtenir des résultats recommandés et personnalisés.
- [ ] EF6 : L'étudiant peut voir les détails des cours sélectionnés, comme la charge de travail et les moyennes.
- [ ] EF7 : L'étudiant peut voir les avis des anciens étudiants sur les cours sélectionnés.
- [ ] EF8 : L'étudiant peut comparer des cours selon les critères désirés.

## Exigences non fonctionnelles

- [ ] ENF1 : Le système doit répondre en moins de 2 secondes.
- [ ] ENF2 : L'application doit être compatible avec Chrome et Firefox.
- [ ] ENF3 : Le système est facilement accessible, clair et utile.
- [ ] ENF4 : Le système ne divulgue pas d'informations confidentielles.
- [ ] ENF5 : Les données officielles sont centralisées dans une même interface.

## Priorisation

- [ ] EF4 : Recherche de cours
- [ ] EF5 : Résultats personnalisés
- [ ] EF6 : Détails des cours
- [ ] EF7 : Avis des anciens étudiants
- [ ] ENF3 : Accessibilité / clarté de l'interface
- [ ] ENF4 : Confidentialité des données
- [ ] ENF5 : Centralisation des données officielles

## Types d'utilisateurs

> Identifier les différents profils qui interagiront avec le système.

| Type d’utilisateur | Description | Exemples de fonctionnalités accessibles |
|--------------------|-------------|------------------------------------------|
| Utilisateur invité | Accès limité, pas d’authentification | Consultation des ressources |
| Utilisateur authentifié | Compte personnel, fonctions principales | Réservation, historique |
| Administrateur | Droits étendus, gestion des ressources | Création/suppression de ressources, gestion des utilisateurs |

<!-- TODO: Détailler selon le périmètre du projet. -->

## Infrastructures

> Informations sur l’environnement d’exécution cible, les outils ou plateformes nécessaires.

- Le système sera hébergé sur un serveur Ubuntu 22.04.
- Base de données : PostgreSQL version 15.
- Serveur Web : Nginx + Gunicorn (pour une app Python, par exemple).
- Framework principal : [À spécifier selon le projet].

<!-- TODO: Compléter selon le stack technique prévu. -->

## Besoins matériels, solution de stockage et solution d'intégration

### Besoins matériels (exigences physiques)

> Le projet étant une plateforme web, ne requiert aucune installation locale ni configuration avancée de la part de l'utilisateur. 

| Équipement | Spécifications minimales | Rôles |
|-------------|--------------------------|------|
| Ordinateur portable ou de bureau | Processeur double cœur, 4 Go RAM, 1 Go d’espace libre | Accès via navigateur web, affichage de l’interface sans  |
| Tablette / mobile | Navigateur moderne, 2 Go RAM | Consultation mobile, lecture et recherche rapide |
| Connexion internet | Stable, ≥ 2 Mbps | Chargement fluide des données depuis les API (Planifium, Discord) |
| Navigateur web | Version récente (Chrome, Firefox, Edge, Safari) | Compatibilité avec les standards HTML5 et JavaScript |

Le matériel utilisé par la majorité des étudiants (ordinateurs portables personnels, postes dans les laboratoires de l’UdeM, appareils mobiles récents) est largement suffisant pour exécuter le système.  

Aucun équipement additionnel n’est requis.  
> Aucun achat, licence, ni composant externe n’est nécessaire pour accéder à la plateforme.

### Solution de stockage

Le stockage des données est au cœur du projet, qui agrège des informations issues de sources officielles et communautaires.  
L’objectif est de garantir un accès centralisé, cohérent et fiable aux données tout en respectant la confidentialité et la Loi 25 (Québec).

#### Types de données
| Catégorie | Source | Format | Fréquence | Accès |
|------------|---------|---------|------------|--------|
| Catalogue des cours | API Planifium | JSON | Temps réel | Lecture seule |
| Résultats académiques agrégés | Données institutionnelles | CSV | Périodique | Lecture seule |
| Avis étudiants | Discord Bot | JSON | Continu | Lecture/Écriture (via API interne) |
| Profils étudiants | Interface web | JSON | Intérêts, rythme, préférences théorie/pratique |

#### Type de base de données
Pour la phase actuelle, le système utilisera une base relationnelle SQLite :
- Intégrée à Python (aucune installation serveur)  
- Stockage local simple et portable  
- Support des jointures (ex. cours ↔ avis ↔ résultats)  
- Cohérence et intégrité des données garantie par schéma relationnel  

> En phase ultérieure, une migration vers PostgreSQL (hébergement cloud) est prévue pour améliorer la scalabilité, la disponibilité et la gestion multi-utilisateurs.

#### Type et architecture de stockage
| Élément | Choix | Justification |
|----------|--------|----------------|
| Type | Base de données relationnelle (SQLite) | Simple, portable, intégrée à Python |
| Architecture | Centralisée locale (prototype) | Adaptée à la phase 1 (données limitées) |
| Sauvegarde | Export CSV/JSON dans GitHub | Sauvegarde manuelle et versionnée |
| Récupération | Rafraîchissement depuis API Planifium / Discord | Données reconstructibles à la demande |
| Sécurité | Anonymisation des données utilisateurs | Conformité Loi 25 |
| Disponibilité | Lecture à la demande via interface web | Données statiques légères |
| Intégrité | Clés primaires et validation schéma JSON | Cohérence entre cours, résultats, avis |
| Scalabilité | Hébergement Cloud | Bonne scalabilité, multi-utilisateurs |


### Solution d'intégration

Les systèmes modernes ne fonctionnent pas isolément.  
L’intégration est donc essentielle pour assurer la communication fluide entre la plateforme et les systèmes externes (Planifium, Discord, résultats institutionnels).  
Elle garantit la cohérence des données, la sécurité des échanges et la résilience des flux d’information.

#### Composantes intégrées
| Composant | Type | Rôle | Méthode d’intégration |
|------------|------|------|------------------------|
| API Planifium | Source officielle | Fournit les cours, horaires, programmes, prérequis | Requêtes REST + JSON |
| Résultats académiques | Données institutionnelles | Moyenne, nombre d’inscrits, taux d’échec | Lecture local |
| Discord Bot | Source communautaire | Collecte des avis étudiants | API Discord |
| Moteur d’agrégation | Interne | Fusionne et normalise les données | Python (JSON) |
| Interface Web | Présentation | Génère l’interface de consultation | Génération statique |
| GitHub | Hébergement | Publication du site | Déploiement automatisé |

#### Principes d’intégration
- Cohérence des données : Validation des schémas avant insertion.  
- Synchronisation : Rafraîchissement périodique des données (Planifium / Discord).  
- Découplage : Chaque source peut évoluer sans affecter le reste du système. 
