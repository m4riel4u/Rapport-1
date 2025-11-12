---
title: Analyse des besoins - Risques
---

# Analyse des risques

## Identification des risques

### Risque 1 – Un membre de l'équipe doit s'absenté pour une période indéterminée.  

- **Probabilité** : Moyenne  
- **Impact** : Élevé  
- **Plan de mitigation** :  
  - Revoir la répartition des tâches pour que chaque membre puisse prendre une partie de la charge de travail de la personne absente. 
  - Documenter clairement les avancements pour simplifier la communication dans l'équipe et la réintégration du membre absent dans le cas où il revient avant la fin du projet.   
  - Favoriser le travail d'équipe pour que la charge soit répartie équitablement entre les membres restants. 
- **Justification** :

Il est fort probable qu'un membre de l'équipe de développement doive s'absenter pour des raisons personnelles, pour un congé de maladie ou autre durant le projet. Dans cette situation, il faut être bien préparé pour ne pas prendre du retard dans l'échéancier malgré un membre de l'équipe en moins. Par conséquent, il est important de documenter tous les avancements dans l'éventualité où un membre doit prendre en charge le travail d'un autre, pour qu'il sache qu'est-ce qu'il y a de réaliser et qu'est-ce qu'il reste à faire. Il est aussi primordial de diviser la charge de travail équitablement entre les personnes restantes pour ne pas surcharger un seul membre et qu'il n'arrive pas à suivre l'échéancier. 


### Risque 2 –  Fiabilité : Échec de la connexion à Planifium 

- **Probabilité** : Moyenne
- **Impact** : Élevé  
- **Plan de mitigation** :  
  - Mise en place d'un message d'erreur. 
  - Communication avec l'équipe responsable du Planifium.
  - Limiter la dépendance entre les modules pour que les parties du programme n'ayant pas besoin de Planifium puisse continuer de fonctionner. 
- **Justification** : 

Il se peut que Planifium soit hors service dû à une opération de maintenance ou aute, donc notre système doit pouvoir avertir les utilisateurs que l'application est hors d'usage pour un certains temps et les autres fonctionnalités de notre application doivent pouvoir continuer à fonctionner. Pour gérer cette situation, une communication avec l'équipe de Planifium sera faite pour savoir combien de temps Planifium sera hors service et un message d'erreur avertissant les utilisateurs sera affiché pour indiquer le délai d'attente. Aussi, les sections de l'application non-concerné par Planifium, comme la modification du profil, seront toujours accessibles et fonctionnelles.  

### Risque 3 – Absence ou pas assez d'avis étudiants pour les afficher. 

- **Probabilité** : Moyenne  
- **Impact** : Faible 
- **Plan de mitigation** :  
  - Mise en place d'un seuil minimum de 5 avis
  - Sensibilisation à l'importance de mettre des avis
  - Mise en place d'un message de gestion s'il n'y a pas assez d'avis
- **Justification** : 

Il est possible qu'il n'y ait aucun ou peu d'avis d'étudiants pour un cours, par exemple, s'il est nouveau ou peu populaire. Dans cette situation, l'application affichera un message pour indiquer qu'il n'y a pas assez d'avis d'étudiant pour ce cours. Le seuil minimal de 5 avis est important pour représenter un avis globale de la population étudiante sur un cours et non l'opinion de seulement quelques personens, car ce n'est pas une représentation globale. Pour favoriser la rédaction d'avis, sur la page d'accueil de note application, il sera écrit clairement où aller pour pouvoir écrire un avis et pourquoi c'est important ("Si vous voulez des avis, il faut en donner aussi!"). 


### Risque 4 – Performance : Surcharge de traffic sur le site web

- **Probabilité** : Faible 
- **Impact** : Moyen  
- **Plan de mitigation** :  
  - Performer des tests de performance régulièrement   
  - Implémenter un système de limitation de requêtes durant les périodes achalandées  
  - Page de maintenance avec une affichage du délai de rétablissement du site
- **Justification** : 

La surcharge de traffic sur le site web ne devrait pas arriver très souvent, car l'application est seulement pour les étudiants de l'Université de Montréal et elle sera probablement achalandée seulement lors de la période d'inscription aux cours. Lors de ces périodes, un système de limitation de requêtes sera mis en place et une page de maintenance affichera le délai d'attente pour accéder au site web.  

### Risque 5 – Sécurité : Fuite d'informations personnelles

- **Probabilité** : Rare  
- **Impact** : Élevé  
- **Plan de mitigation** :  
  - Aucun stockage de données personnelles (Loi 25).
  - Utilisation d'une clé pour chiffrer les données. 
- **Justification** : 

Une fuite d'informations personnelles serait assez rares, mais pour éviter cette situation, les informations contenues dans le site web seront crypter par 2 clés (asymétrique et symétrique) comme TLS vu qu'on utilise de la transmission via HTTPS. 
