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
  - Tests d’intégration
  - Tests manuels
- Outils utilisés : Pytest, Postman, Selenium, etc.

## Critères d'évaluation

- Présenter les critères utilisés pour évaluer le système.

## Résultats des tests

- Résumé qualitatif :
  - Comportement attendu obtenu
  - Bonne robustesse générale

- Résumé quantitatif :
  - 85 % de couverture de code
  - Temps de réponse moyen : 1.2s

## Oracle de tests
|Nom du test|Entrée|Sortie attendue|Cas d'utilisation |État après l'appel|Type|Description|
|-----------|------|---------------|------------------|------------------|----|-----------|
|testGetAllUsers |Aucune |Nombre d'utilisateurs |Créer un compte |N/A |Succès |Lorsqu'on appel tous les utilisateurs, on s'attend à avoir le bon nombre d'utilisateur. |
|testGetUserById |userId = 1 |{id:1, name: Alice, email: alice@example.com} |Créer un compte |N/A |Succès |Lorsqu'on appel un utilisateur par son ID, on s'attend à avoir les informations liées au bon utilisateur. |
|testCreateUser |newUser = new User(0, "Marie", "marie@example.com") |{{id:0, name: Marie, email: marie@example.com}, {id:1, name: Alice, email: alice@example.com}, {id:2, name: Charlie, email: charlie@example.com}} |Créer un compte |Un utilisateur ajouté. |Succès |Lorsqu'on ajoute un utilisateur, il devrait être présent dans le système avec les bonnes informations liées à ce profil. |
|testUpdateUser |userService.updateUser(1, updatedUser); |{{id:1, name: Marie, email: marie@example.com}, {id:2, name: Charlie, email: charlie@example.com}} |Modifier un profil |Utilisateur modifié. |Succès| Lorsqu'on met à un jour un utilisateur, on s'attend à ce que les bonnes informations soient modifiées sur le bon utilisateur. |
|testDeleteUser|userService.deleteUser(1);|{{id:2, name: Charlie, email: charlie@example.com}}|Modifier un profil|Utilisateur supprimé.|Succès|Lorsqu'on supprime un utilisateur, on s'attend qu'il soit retiré du système.|


## Évaluation du système

- Qualité globale perçue
- Facilité d’utilisation
- Performance en conditions réelles