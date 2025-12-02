---
title: Analyse des besoins - Cas d'utilisation
---

# Cas d'utilisation

## Vue d’ensemble

![alt text](<Diagramme CU.png>)
## Liste des cas d’utilisation

| ID | Nom | Acteurs principaux | Description |
|----|-----|---------------------|-------------|
| CU01 | Connexion | Utilisateur | L'utilisateur se connecte à la plateforme. |
| CU02 | Recherche de cours | Utilisateur | L'utlisateur peut effectuer une recherche de cours sur l'application. |
| CU03 | Personnalisation du profil | Utilisateur | L'utilisateur peut configurer ses préférences de cours pour obtenir des suggestions personnalisées. |
| CU04 | Comparaison de cours | Utilisateur | L'utilisateur peut comparer des cours pour estimer la charge total de travail d'une combinaison. |
| CU05 | Création d'un compte | Utilisateur | L'utilisateur peut se créer un compte s'il n'en a pas déjà un. |
| CU06 | Recommandations automatiques | Utilisateur | L'utilisateur obtient des recommandatations adaptées à son profil. |
| CU07 | Ajout des intérêts | Utilisateur | L'utilisateur peut ajouter ses intérêts académiques spécifiques à son profil. |
| CU08 | Ajout des cours réussis | Utilisateur | L'utilisateur peut ajouter ses cours réussis à son profil pour obtenir des recommandations de cours avec les préalables déjà acquis. |
| CU09 | Affichage des résultats académiques | Utilisateur | L'utilisateur peut voir les résultats des cours sélectionnés pour estimer la difficulté de ces cours. |
| CU010 | Éligibilité au cours | Utilisateur | L'utilisateur peut vérifier s'il est éligible pour un cours spécifique avec les données de son profil. |
| CU011 | Consulter les avis sur les cours | Utilisateur | L'utilisateur peut voir les avis des autres étudiants sur des cours spécifiques. |
| CU012 | Mettre à jour les avis des cours | Utilisateur | L'utilisateur peut modifier et améliorer un ancien avis publié sous un cours. |
| CU013 | Voir l'horaire des cours | Utilisateur | L'utilisateur peut consulter l'horaire des cours sélectionnés pour organiser sa session. |


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

### CU06 - Recommandations automatiques

**Acteurs** : 

- Utilisateur

**Préconditions** : 

- Le profil de l'étudiant possède ses préférences ainsi que ses historiques des cours réussis.

**PostConditions** :

- Affichage d'une liste de cours recommandés et personnalisés pour l'étudiant.

**Déclencheur** : 

- L'étudiant consulte la section de recommandations sur l'application.

**Dépendances** : 

- Personnalisation du profil de l'étudiant.
- Base de données des cours par l'API Planifium.

**But** :

- Offrir une liste de recommandations de cours adaptés à l'étudiant et à ses préférences pour faciliter ses choix de cours.

#### Scénario:
- Scénario principal
    1. L’utilisateur se connecte et accède à la section « Recommandations ».
    2. La plateforme récupère les informations du profil (intérêts, cours réussis, résultats académiques, contraintes d’horaire).
    3. Le système analyse les données du profil et la base de cours disponibles.
    4. La plateforme génère une liste de cours recommandés.
    5. La liste des recommandations est affichée à l’utilisateur avec une courte justification.

- Scénario alternatif
    1. L’utilisateur ouvre la section « Recommandations ».
    2. La plateforme détecte que certaines informations du profil sont manquantes (ex.: intérêts non remplis, aucun cours réussi).
    3. La plateforme affiche un message : « Veuillez compléter votre profil. »
    4. L’utilisateur est redirigé vers la page de personnalisation du profil.

### CU07 - Ajout des intérêts

**Acteurs** : 

- Utilisateur

**Préconditions** : 

- L'étudiant est connecté à son compte.
- La fonction de personnalisation de profil est opérationnelle.

**PostConditions** :

- Les intérêts au choix de l'étudiant sont rajoutés sur le profil.
- Les intérêts sur le profil sont utilisés pour les recherches et les recommandations.

**Déclencheur** : 

- L'étudiant se dirige vers la personnalisation du profil et sélectionne l'option d'ajouter des intérêts.

**Dépendances** : 

- Base de données du profil de l'étudiant.

**But** :

- Permettre à l'étudiant de choisir ses intérêts académiques pour avoir des recommandations et des recherches personnalisées.

#### Scénario:
- Scénario principal
    1. L’utilisateur accède à la section « Profil » puis à l’onglet « Intérêts académiques ».
    2. La plateforme affiche la liste des domaines.
    3. L’utilisateur sélectionne un ou plusieurs intérêts dans la liste.
    4. L’utilisateur clique sur « Enregistrer ».
    5. Le système met à jour les intérêts dans la base de données.
    6. La plateforme confirme l’enregistrement et indique que les recommandations seront mises à jour.

- Scénario alternatif
    1. L’utilisateur clique sur « Enregistrer » sans avoir choisi d’intérêt.
    2. La plateforme affiche un message d’erreur : « Veuillez sélectionner au moins un intérêt. »
    3. L’utilisateur retourne au formulaire et choisit un intérêt.

### CU08 - Ajout des cours réussis

**Acteurs** : 

- Utilisateur

**Préconditions** : 

- L'étudiant est connecté à son compte.
- La fonction de personnalisation de profil est opérationnelle.

**PostConditions** :

- Les cours réussis sélectionnés par l'étudiant sont rajoutés sur le profil.
- La recommandation des cours sera basée sur les prérequis des cours de l'étudiant.

**Déclencheur** : 

- L'étudiant se dirige vers la personnalisation du profil et sélectionne l'option d'ajouter des cours réussis.

**Dépendances** : 

- Base de données du profil de l'étudiant.
- API Planifium.

**But** :

- Permettre à l'étudiant de rajouter des cours réussis pour faciliter les choix de cours avec les préalables demandés.

#### Scénario:
- Scénario principal
    1. L’utilisateur accède à la section « Profil » puis à la section « Cours réussis ».
    2. La plateforme affiche un champ de recherche et une liste de cours.
    3. L’utilisateur recherche un cours qu’il a réussi et le sélectionne.
    4. L’utilisateur confirme l’ajout du cours à sa liste.
    6. La plateforme enregistre le cours complété dans le profil de l’utilisateur.
    7. Le système met à jour les préalables considérés dans les recommandations et l’éligibilité.

- Scénario alternatif
    1. L’utilisateur cherche un cours, mais aucun résultat ne correspond.
    2. La plateforme affiche : « Aucun cours trouvé pour cette recherche. »
    4. L’utilisateur peut modifier le mot-clé ou choisir un cours dans une liste suggérée.

### CU09 - Affichage des résultats académiques

**Acteurs** : 

- Utilisateur

**Préconditions** : 

- Les données académiques disponibles dans le système sous format CSV.

**PostConditions** :

- Les résultats académiques du cours sont affichés clairement.

**Déclencheur** : 

- L'étudiant a sélectionné l'affichage des détails d'un cours ou a effectué une recherche de cours.

**Dépendances** : 

- API Planifium.

**But** :

- L'étudiant peut consulter les notes des cours sélectionnés pour anticiper la difficulté et la charge de travail des cours aux choix.

#### Scénario:
- Scénario principal
    1. L’utilisateur ouvre la section « Historique des notes ».
    2. La plateforme récupère les résultats scolaires enregistrés.
    3. Les résultats sont affichés sous forme de liste ou de tableau classé selon le cours et la session.
    4. L’utilisateur sélectionne un ou plusieurs cours pour voir la difficulté ou de la charge de travail associée.
    5. La plateforme affiche des indicateurs comme la moyenne générale et le taux de réussite.

- Scénario alternatif
    1. L’utilisateur accède à la page des résultats.
    2. La plateforme constate qu’aucun résultat n’est enregistré.
    3. Un message s’affiche : « Aucun résultat disponible. Veuillez ajouter vos cours réussis ou connecter votre dossier académique. »

### CU010 - Éligibilité au cours

**Acteurs** : 

- Utilisateur

**Préconditions** : 

- Le profil de l'étudiant possède assez d'informations par rapport à son parcours et ses cours réussis.
- Les données des cours sont disponibles via Planifium.

**PostConditions** :

- Les cours éligibles pour l'étudiant sont affichés avec des détails.

**Déclencheur** : 

- L'étudiant sélectionne un cours spécifique ou effectue une recherche.

**Dépendances** : 

- API Planifium.
- Profil de l'étudiant avec les personnalisations et intérêts mis à jour.

**But** :

- Présenter à l'étudiant les cours éligibles par rapport à son parcours pour faciliter ses choix de cours.

#### Scénario:
- Scénario principal
    1. Depuis une fiche de cours, l’utilisateur clique sur « Vérifier mon éligibilité ».
    2. La plateforme récupère les données du profil : cours réussis, programme, année d’étude, résultats.
    3. Le système compare ces données avec les préalables et les contraintes du cours.
    4. La plateforme affiche le résultat : « Vous êtes éligible à ce cours » ou « Il vous manque les préalables: ... ».

- Scénario alternatif
    1. L’utilisateur clique sur « Vérifier mon éligibilité ».
    2. La plateforme détecte que certaines informations (cours réussis, programme, etc.) sont manquantes.
    3. La plateforme affiche : « Impossible de vérifier votre éligibilité. Veuillez compléter votre profil. »

### CU011 - Consulter les avis sur les cours

**Acteurs** : 

- Utilisateur

**Préconditions** : 

- Les avis des cours sont disponibles sous format JSON.

**PostConditions** :

- Les avis des étudiants sur les cours sont affichés clairement.

**Déclencheur** : 

- L'étudiant sélectionne l'option des avis des étudiants d'un cours.

**Dépendances** : 

- Base de données des avis obtenue via Discord.

**But** :

- L'étudiant peut consulter les avis pertinents des cours afin d'analyser la difficulté et la charge de travail des cours.

#### Scénario:
- Scénario principal
    1. L’utilisateur ouvre la fiche d’un cours.
    2. La plateforme affiche un onglet ou une section « Avis des étudiants ».
    3. L’utilisateur clique sur cette section.
    4. Le système récupère les avis existants pour ce cours dans la base de données du bot Discord.
    5. Les avis sont affichés à l’utilisateur.

- Scénario alternatif
    1. L’utilisateur consulte la section « Avis » d’un cours.
    2. La plateforme ne trouve aucun avis pour ce cours dans la base de données du bot Discord.
    3. La plateforme affiche : « Aucun avis n’a encore été publié pour ce cours. Soyez la première personne à laisser un commentaire ! »

### CU012 - Mettre à jour les avis des cours

**Acteurs** : 

- Utilisateur

**Préconditions** : 

- L'étudiant est connecté à son compte.
- La fonction de la gestion des avis est fonctionnelle.

**PostConditions** :

- L'avis de l'étudiant est modifié pour le cours.

**Déclencheur** : 

- L'étudiant clique sur l'option de modifier son avis déjà existant sous un cours.

**Dépendances** : 

- Base de données des avis.

**But** :

- Permettre à l'étudiant de modifier un avis déjà publié pour améliorer la qualité des informations disponibles pour les autres étudiants.

#### Scénario:
- Scénario principal
    1. L’utilisateur accède à la fiche d’un cours pour lequel il a déjà laissé un avis.
    2. La plateforme affiche son avis avec un bouton « Modifier ».
    3. L’utilisateur clique sur « Modifier ».
    4. La plateforme affiche un formulaire rempli avec son ancien commentaire.
    5. L’utilisateur modifie le texte.
    6. L’utilisateur clique sur « Enregistrer ».
    7. Le système met à jour l’avis dans la base de données de Discord et affiche un message de confirmation.

- Scénario alternatif
    1. L’utilisateur tente de modifier un avis appartenant à un autre étudiant.
    2. Le système vérifie l’identité de l’auteur de l’avis.
    3. La plateforme refuse l’accès et affiche : « Vous ne pouvez modifier que vos propres avis. »

### CU013 - Voir l'horaire des cours

**Acteurs** : 

- Utilisateur

**Préconditions** : 

- Les données des horaires des cours sont disponibles via API Planifium.

**PostConditions** :

- L'affichage clair des horaires des cours sélectionnés par l'étudiant.

**Déclencheur** : 

- L'étudiant sélectionne l'option de voir l'horaire des cours spécifiques ou recherchés.

**Dépendances** : 

- API Planifium.

**But** :

- Permettre à l'étudiant de consulter les horaires des cours choisis pour planifier sa session avec ses autres besoins.

#### Scénario:
- Scénario principal
    1. L’utilisateur sélectionne plusieurs cours.
    2. L’utilisateur clique sur « Voir l’horaire ».
    3. La plateforme récupère les informations d’horaire de chaque cours.
    4. Le système génère un horaire visuel comme un calendrier hebdomadaire.
    5. L’utilisateur voit son horaire global et peut vérifier la charge de travail et la répartition des cours.

- Scénario alternatif
    1. L’utilisateur clique sur « Voir l’horaire » avec des cours qui se chevauchent.
    2. Le système détecte un conflit entre deux cours.
    3. La plateforme affiche l’horaire avec les conflits en rouge et un message est affiché : « Conflit détecté entre ... et Cours ... . Veuillez ajuster votre sélection de cours. »