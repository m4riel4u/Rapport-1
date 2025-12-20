---
title: Conception - Modèle de données
---

# Modèle de données

## Entités principales

- Utilisateur
- Cours
- Horaire
- Avis


## Relations entre entités

- 1 cours peut avoir plusieurs horaires
- 1 utilisateur peut être associé à plusieurs cours 
- 1 utilisateur peut émettre plusieurs avis
- 1 cours peut avoir plusieurs avis 

## Contraintes métier

- Un utilisateur ne peut pas avoir 2 fois le même cours. 
- Un utilisateur doit avoir les préalables pour être éligible à un cours.

## Évolution potentielle du modèle

- Ajouter la détection de conflit d'horaire entre des cours
- Comparer des ensembles de cours (horaires) différents 
