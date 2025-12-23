---
title: Évaluation et tests
---

<style>
    @media screen and (min-width: 76em) {
        .md-sidebar--primary {
            display: none !important;
        }
    }
</style>

# Tests et évaluation

## Plan de test

- Types de tests réalisés :
  - Tests unitaires
- Outils utilisés : Mockito, Postman

## Oracle de tests
|Nom du test|Entrée|Sortie attendue|Cas d'utilisation |État après l'appel|Type|Description|
|-----------|------|---------------|------------------|------------------|----|-----------|
|testIsNotEmpty|"Hello"|true|Validation|N/A|Succès|Vérifie que la méthode détecte une chaîne non vide.|
|testIsEmail|"abc@mail.com"|true|Validation|N/A|Succès|Vérifie qu'un email valide est reconnu.|
|testGetAllUsers|Aucune (2 utilisateurs initiaux)|Liste de 2 utilisateurs|Consulter utilisateurs|N/A|Succès|Retourne Alice et Bob présents au démarrage.|
|testGetUserById_existingUser|userId=1|Optional contenant Alice|Consulter utilisateur|N/A|Succès|Renvoie l'utilisateur existant avec ID 1.|
|testGetUserById_zeroId|userId=0|Optional.empty()|Consulter utilisateur|N/A|Succès|Aucun utilisateur ne possède l'ID 0.|
|testGetUserById_negativeId|userId=-1|Optional.empty()|Consulter utilisateur|N/A|Succès|Aucun utilisateur ne possède un ID négatif.|
|testGetUserById_existingUser2|userId=2|Optional contenant Bob|Consulter utilisateur|N/A|Succès|Renvoie l'utilisateur existant avec ID 2.|
|testGetUserById_largeId|userId=999|Optional.empty()|Consulter utilisateur|N/A|Succès|Retourne vide pour un ID inexistant très grand.|
|testCreateUser|newUser John|3 utilisateurs, John créé ID 3|Créer un compte|Utilisateur ajouté|Succès|Ajoute un utilisateur avec les bonnes informations.|
|testCreateUser_increasesUserCount|newUser Marie|3 utilisateurs au total|Créer un compte|Utilisateur ajouté|Succès|Le nombre d'utilisateurs augmente après création.|
|testCreateUser_assignsValidId|newUser Marie|ID 3 assigné automatiquement|Créer un compte|Utilisateur ajouté|Succès|Un ID valide est attribué automatiquement.|
|testCreateUser_withNullName|newUser name=null|User ID 3 avec nom null|Créer un compte|Utilisateur ajouté|Succès|Accepte un nom null à la création.|
|testCreateUser_withEmptyName|newUser name=""|User ID 3 avec nom vide|Créer un compte|Utilisateur ajouté|Succès|Accepte un nom vide à la création.|
|testCreateUser_withNullEmail|newUser email=null|User ID 3 avec email null|Créer un compte|Utilisateur ajouté|Succès|Accepte un email null à la création.|
|testCreateUser_withEmptyEmail|newUser email=""|User ID 3 avec email vide|Créer un compte|Utilisateur ajouté|Succès|Accepte un email vide à la création.|
|testCreateUser_multipleUsersSequentialIds|Ajout User1, User2, User3|IDs séquentiels 3,4,5; total 5 users|Créer plusieurs comptes|3 utilisateurs ajoutés|Succès|Les IDs sont attribués séquentiellement.|
|testUpdateUser|id=1, maj AliceUpdated|Alice mise à jour nom+email|Modifier profil|Utilisateur modifié|Succès|Met à jour correctement un utilisateur existant.|
|testUpdateUser_nonExistingUser|id=2, maj BobUpdated|Bob mis à jour, total 2 users|Modifier profil|Utilisateur modifié|Succès|Met à jour l'utilisateur ID 2 avec nouvelles valeurs.|
|testUpdateUser_withNullName|id=1, name=null|User 1 avec nom null|Modifier profil|Nom mis à null|Succès|Accepte un nom null lors de la mise à jour.|
|testUpdateUser_withEmptyName|id=1, name=""|User 1 avec nom vide|Modifier profil|Nom mis à vide|Succès|Accepte un nom vide lors de la mise à jour.|
|testUpdateUser_withNullEmail|id=1, email=null|User 1 avec email null|Modifier profil|Email mis à null|Succès|Accepte un email null lors de la mise à jour.|
|testUpdateUser_withEmptyEmail|id=1, email=""|User 1 avec email vide|Modifier profil|Email mis à vide|Succès|Accepte un email vide lors de la mise à jour.|
|testDeleteUser|id=1|User 1 supprimé, 1 user restant|Supprimer un compte|Utilisateur ID 1 supprimé|Succès|Supprime correctement un utilisateur existant.|
|testDeleteUser_nonExistingUser|id=999|false, 2 users toujours présents|Supprimer un compte|Aucun changement|Succès|Retourne false pour un ID inexistant.|
|testDeleteUser_negativeId|id=-1|false, 2 users toujours présents|Supprimer un compte|Aucun changement|Succès|Retourne false pour un ID négatif.|
|testDeleteUser_zeroId|id=0|false, 2 users toujours présents|Supprimer un compte|Aucun changement|Succès|Retourne false pour un ID de 0.|
|testDeleteUser_multipleDeletions|delete 1 puis 2|true/true, 0 users restants|Supprimer plusieurs comptes|Deux utilisateurs supprimés|Succès|Plusieurs suppressions successives fonctionnent.|
|testGetAllCourses|Mock retourne 2 cours|Liste de 2 cours|Consulter cours|N/A|Succès|Retourne tous les cours mockés.|
|testGetAllCourses_apiRetourneListeVide|Mock liste vide|Liste vide|Consulter cours|N/A|Succès|Retourne une liste vide si l'API renvoie vide.|
|testGetCourseById|courseId="IFT2255"|Optional contenant IFT2255|Consulter un cours|N/A|Succès|Retourne le bon cours pour un ID valide.|
|testSearchCourses_filtreParIdOuNom|query "IFT2255" puis "design"|1 cours pour chaque requête|Rechercher cours|N/A|Succès|Filtre par ID et par nom partiel (casse insensible).|
|testSearchCourses_champVide|query=""|Liste de 2 cours|Rechercher cours|N/A|Succès|Une requête vide retourne tous les cours.|
|testSearchCourses_nullQuery|query=null|Liste de 2 cours|Rechercher cours|N/A|Succès|Une requête null retourne tous les cours.|
|testSearchCourses_upperCase|query="LOGICIEL"|1 cours (IFT2255)|Rechercher cours|N/A|Succès|Recherche insensible à la casse (majuscules).|
|testSearchCourses_lowerCase|query="design"|1 cours (IFT1005)|Rechercher cours|N/A|Succès|Recherche insensible à la casse (minuscules).|
|testSearchCourses_noMatches|query="informatique"|Liste vide|Rechercher cours|N/A|Succès|Retourne vide si aucune correspondance.|
|testSearchCourses_multipleMatches|query="IFT"|Liste de 2 cours|Rechercher cours|N/A|Succès|Retourne toutes les correspondances (IFT2255, IFT1005).|
|testCheckEligibility_courseNotFound|completed=[IFT1005], courseId invalid|{eligible:false, missing:["Cours introuvable"]}|Vérifier éligibilité|N/A|Succès|Inéligible si le cours n'existe pas.|
|testCheckEligibility_noPrerequisites|completed=[], course sans prérequis|{eligible:true, missing:[]}|Vérifier éligibilité|N/A|Succès|Éligible quand aucun prérequis.|
|testCheckEligibility_allPrerequisitesCompleted|completed=[IFT1005,IFT1010,IFT1020], prereq=[IFT1005,IFT1010]|{eligible:true, missing:[]}|Vérifier éligibilité|N/A|Succès|Éligible si tous les prérequis sont complétés.|
|testCheckEligibility_missingPrerequisites|completed=[IFT1005], prereq=[IFT1005,IFT1010,IFT1020]|{eligible:false, missing:[IFT1010,IFT1020]}|Vérifier éligibilité|N/A|Succès|Liste correctement les prérequis manquants.|
|testCheckEligibility_noPrerequisitesCompleted|completed=[], prereq=[IFT1005,IFT1010]|{eligible:false, missing:[IFT1005,IFT1010]}|Vérifier éligibilité|N/A|Succès|Inéligible quand aucun prérequis n'est complété.|
|testCheckEligibilityForUser_eligible|user completed=[IFT1005,IFT1010,IFT1020], prereq=[IFT1005,IFT1010]|{eligible:true, missing:[]}|Éligibilité utilisateur|N/A|Succès|Utilisateur éligible avec tous les prérequis.|
|testCheckEligibilityForUser_ineligible|user completed=[IFT1005], prereq=[IFT1005,IFT1010]|{eligible:false, missing:[IFT1010]}|Éligibilité utilisateur|N/A|Succès|Utilisateur inéligible s'il manque un prérequis.|
|testCheckEligibilityForUser_noPrerequisites|user completed=[], cours sans prérequis|{eligible:true, missing:[]}|Éligibilité utilisateur|N/A|Succès|Éligible quand le cours n'a pas de prérequis.|
|testCheckEligibilityForUser_courseNotFound|user quelconque, courseId invalide|{eligible:false, missing:["Cours introuvable"]}|Éligibilité utilisateur|N/A|Succès|Inéligible si le cours est introuvable.|
|testCheckEligibilityForUser_noCompletedCourses|user completed=[], prereq=[IFT1005,IFT1010]|{eligible:false, missing:[IFT1005,IFT1010]}|Éligibilité utilisateur|N/A|Succès|Inéligible si l'utilisateur n'a complété aucun prérequis.|


