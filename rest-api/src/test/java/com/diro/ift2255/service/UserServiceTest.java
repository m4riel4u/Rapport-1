package com.diro.ift2255.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.diro.ift2255.model.User;

import java.util.ArrayList;

public class UserServiceTest {
    private UserService userService; // créer une instance pour pouvoir la tester

    @BeforeEach
    void setup() {
        userService = new UserService();
        // Initialize with test users
        userService.createUser(new User(0, "Alice", "alice@example.com", null, new ArrayList<>()));
        userService.createUser(new User(0, "Bob", "bob@example.com", null, new ArrayList<>()));
    }

    @AfterEach
    void cleanup() {
        // Reset the users file for next test
        try {
            java.nio.file.Files.deleteIfExists(java.nio.file.Paths.get("../users.json"));
        } catch (Exception e) {
            // Ignore
        }
    }

    /**
     * Test : vérifier que getAllUsers retourne tous les utilisateurs.
     */
    @Test
    void testGetAllUsers() {
        // Arrange & Act
        var users = userService.getAllUsers();

        // Assert
        assertEquals(2, users.size(), "Il devrait avoir 2 utilisateurs initialement.");
    }

    /**
     * Test : vérifier que getUserById retourne l'utilisateur correct pour un ID existant.
     */
    @Test
    void testGetUserById_existingUser() {
        // Arrange
        var userId = 1;

        // Act
        var userOpt = userService.getUserById(userId);

        // Assert
        assertTrue(userOpt.isPresent(), "L'utilisateur avec l'ID 1 devrait exister.");
        assertEquals("Alice", userOpt.get().getName(), "Le nom de l'utilisateur de l'ID 1 devrait être Alice.");
        assertEquals("alice@example.com", userOpt.get().getEmail(),
                "Le email de l'utilisateur de l'ID 1 devrait être alice@example.com.");
    }

    /**
     * Test : vérifier que getUserById retourne empty pour un ID de 0.
     */
    @Test
    void testGetUserById_zeroId() {
        // Arrange & Act
        var userOpt = userService.getUserById(0);

        // Assert
        assertTrue(userOpt.isEmpty(), "Aucun utilisateur possède un ID de 0.");
    }

    /**
     * Test : vérifier que getUserById retourne empty pour un ID négatif.
     */
    @Test
    void testGetUserById_negativeId() {
        // Arrange & Act
        var userOpt = userService.getUserById(-1);

        // Assert
        assertTrue(userOpt.isEmpty(), "Aucun utilisateur possède un ID négatif.");
    }

    /**
     * Test : vérifier que getUserById retourne l'utilisateur correct pour l'ID 2.
     */
    @Test
    void testGetUserById_existingUser2() {
        // Arrange
        var userId = 2;

        // Act
        var userOpt = userService.getUserById(userId);

        // Assert
        assertTrue(userOpt.isPresent(), "L'utilisateur avec l'ID 2 devrait exister.");
        assertEquals("Bob", userOpt.get().getName(), "Le nom de l'utilisateur de l'ID 2 devrait être Bob.");
        assertEquals("bob@example.com", userOpt.get().getEmail(),
                "Le email de l'utilisateur de l'ID 2 devrait être bob@example.com.");
    }

    /**
     * Test : vérifier que getUserById retourne empty pour un ID trop grand.
     */
    @Test
    void testGetUserById_largeId() {
        // Arrange
        var userId = 999;

        // Act
        var userOpt = userService.getUserById(userId);

        // Assert
        assertTrue(userOpt.isEmpty(), "Un ID inexistant (999) devrait retourner empty.");
    }

    /**
     * Test : vérifier que createUser fonctionne correctement.
     */
    @Test
    void testCreateUser() {
        // Arrange
        var newUser = new User(0, "John", "john@example.com", null, new ArrayList<>());

        // Act
        userService.createUser(newUser);

        // Assert
        var users = userService.getAllUsers();
        assertEquals(3, users.size(), "Il devrait y avoir 3 utilisateurs après création.");

        var userOpt = userService.getUserById(3);
        assertTrue(userOpt.isPresent(), "L'utilisateur créé devrait exister.");
        assertEquals("John", userOpt.get().getName(), "Le nom devrait être John.");
        assertEquals("john@example.com", userOpt.get().getEmail(), "L'email devrait être john@example.com.");
    }

    /**
     * Test : vérifier que createUser augmente le nombre d'utilisateurs.
     */
    @Test
    void testCreateUser_increasesUserCount() {
        // Arrange
        var newUser = new User(0, "Marie", "marie@example.com", null, new ArrayList<>());

        // Act
        userService.createUser(newUser);

        // Assert
        var users = userService.getAllUsers();
        assertEquals(3, users.size(), "Après ajout, il devrait y avoir 3 utilisateurs.");
    }

    /**
     * Test : vérifier que createUser attribue un ID valide.
     */
    @Test
    void testCreateUser_assignsValidId() {
        // Arrange
        var newUser = new User(0, "Marie", "marie@example.com", null, new ArrayList<>());

        // Act
        userService.createUser(newUser);

        // Assert
        // Si le service marche comme prévu, Marie devient ID 3
        var userOpt = userService.getUserById(3);
        assertTrue(userOpt.isPresent(), "Après createUser, l'utilisateur avec ID 3 devrait exister.");
        assertEquals("Marie", userOpt.get().getName(), "Le nom de l'utilisateur créé devrait être Marie.");
        assertEquals("marie@example.com", userOpt.get().getEmail(),
                "Le email de l'utilisateur créé devrait être marie@example.com.");
    }

    
    @Test
    void testCreateUser_withNullName() {
        // Arrange
        var newUser = new User(0, null, "nullname@example.com", null, new ArrayList<>());

        // Act
        userService.createUser(newUser);

        // Assert
        var userOpt = userService.getUserById(3);
        assertTrue(userOpt.isPresent(), "L'utilisateur avec ID 3 devrait exister.");
        assertEquals(null, userOpt.get().getName(), "Le nom devrait être null.");
        assertEquals("nullname@example.com", userOpt.get().getEmail(), "L'email devrait être correct.");
    }

    /**
     * Test : vérifier que createUser gère correctement un nom vide.
     */
    @Test
    void testCreateUser_withEmptyName() {
        // Arrange
        var newUser = new User(0, "", "emptyname@example.com", null, new ArrayList<>());

        // Act
        userService.createUser(newUser);

        // Assert
        var userOpt = userService.getUserById(3);
        assertTrue(userOpt.isPresent(), "L'utilisateur avec ID 3 devrait exister.");
        assertEquals("", userOpt.get().getName(), "Le nom devrait être vide.");
        assertEquals("emptyname@example.com", userOpt.get().getEmail(), "L'email devrait être correct.");
    }

    /**
     * Test : vérifier que createUser gère correctement un email null.
     */
    @Test
    void testCreateUser_withNullEmail() {
        // Arrange
        var newUser = new User(0, "NullEmail", null, null, new ArrayList<>());

        // Act
        userService.createUser(newUser);

        // Assert
        var userOpt = userService.getUserById(3);
        assertTrue(userOpt.isPresent(), "L'utilisateur avec ID 3 devrait exister.");
        assertEquals("NullEmail", userOpt.get().getName(), "Le nom devrait être correct.");
        assertEquals(null, userOpt.get().getEmail(), "L'email devrait être null.");
    }

    /**
     * Test : vérifier que createUser gère correctement un email vide.
     */
    @Test
    void testCreateUser_withEmptyEmail() {
        // Arrange
        var newUser = new User(0, "EmptyEmail", "", null, new ArrayList<>());

        // Act
        userService.createUser(newUser);

        // Assert
        var userOpt = userService.getUserById(3);
        assertTrue(userOpt.isPresent(), "L'utilisateur avec ID 3 devrait exister.");
        assertEquals("EmptyEmail", userOpt.get().getName(), "Le nom devrait être correct.");
        assertEquals("", userOpt.get().getEmail(), "L'email devrait être vide.");
    }

    /**
     * Test : vérifier que createUser attribue des IDs séquentiels pour plusieurs utilisateurs.
     */
    @Test
    void testCreateUser_multipleUsersSequentialIds() {
        // Arrange
        var user1 = new User(0, "User1", "user1@example.com", null, new ArrayList<>());
        var user2 = new User(0, "User2", "user2@example.com", null, new ArrayList<>());
        var user3 = new User(0, "User3", "user3@example.com", null, new ArrayList<>());

        // Act
        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);

        // Assert
        var users = userService.getAllUsers();
        assertEquals(5, users.size(), "Il devrait y avoir 5 utilisateurs au total.");

        var userOpt3 = userService.getUserById(3);
        var userOpt4 = userService.getUserById(4);
        var userOpt5 = userService.getUserById(5);

        assertTrue(userOpt3.isPresent(), "User avec ID 3 devrait exister.");
        assertTrue(userOpt4.isPresent(), "User avec ID 4 devrait exister.");
        assertTrue(userOpt5.isPresent(), "User avec ID 5 devrait exister.");

        assertEquals("User1", userOpt3.get().getName(), "Le nom du user 3 devrait être User1.");
        assertEquals("User2", userOpt4.get().getName(), "Le nom du user 4 devrait être User2.");
        assertEquals("User3", userOpt5.get().getName(), "Le nom du user 5 devrait être User3.");
    }


    @Test
    void testUpdateUser() {
        // Arrange
        var updatedUser = new User(0, "AliceUpdated", "alice.updated@example.com", null, new ArrayList<>());

        // Act
        userService.updateUser(1, updatedUser);

        // Assert
        var userOpt = userService.getUserById(1);
        assertTrue(userOpt.isPresent(), "L'utilisateur ID 1 devrait toujours exister après update.");
        assertEquals("AliceUpdated", userOpt.get().getName(), "Le nom devrait être mis à jour.");
        assertEquals("alice.updated@example.com", userOpt.get().getEmail(), "Le email devrait être mis à jour.");
    }

    /**
     * Test : vérifier que updateUser gère correctement un utilisateur inexistant.
     */
    @Test
    void testUpdateUser_nonExistingUser() {
        // Arrange
        var updatedUser = new User(0, "BobUpdated", "bob.updated@example.com", null, new ArrayList<>());

        // Act
        userService.updateUser(2, updatedUser);

        // Assert
        var userOpt = userService.getUserById(2);
        assertTrue(userOpt.isPresent(), "L'utilisateur avec ID 2 devrait exister après update.");
        assertEquals("BobUpdated", userOpt.get().getName(), "Le nom devrait être mis à jour.");
        assertEquals("bob.updated@example.com", userOpt.get().getEmail(), "L'email devrait être mis à jour.");
        var users = userService.getAllUsers();
        assertEquals(2, users.size(), "Il devrait y avoir 2 utilisateurs au total.");
    }

    /**
     * Test : vérifier que updateUser gère correctement un nom null.
     */
    @Test
    void testUpdateUser_withNullName() {
        // Arrange
        var updatedUser = new User(0, null, "nullname@example.com", null, new ArrayList<>());

        // Act
        userService.updateUser(1, updatedUser);

        // Assert
        var userOpt = userService.getUserById(1);
        assertTrue(userOpt.isPresent(), "L'utilisateur ID 1 devrait exister.");
        assertEquals(null, userOpt.get().getName(), "Le nom devrait être null.");
        assertEquals("nullname@example.com", userOpt.get().getEmail(), "L'email devrait être mis à jour.");
    }

    /**
     * Test : vérifier que updateUser gère correctement un nom vide.
     */
    @Test
    void testUpdateUser_withEmptyName() {
        // Arrange
        var updatedUser = new User(0, "", "emptyname@example.com", null, new ArrayList<>());

        // Act
        userService.updateUser(1, updatedUser);

        // Assert
        var userOpt = userService.getUserById(1);
        assertTrue(userOpt.isPresent(), "L'utilisateur ID 1 devrait exister.");
        assertEquals("", userOpt.get().getName(), "Le nom devrait être vide.");
        assertEquals("emptyname@example.com", userOpt.get().getEmail(), "L'email devrait être mis à jour.");
    }

    /**
     * Test : vérifier que updateUser gère correctement un email null.
     */
    @Test
    void testUpdateUser_withNullEmail() {
        // Arrange
        var updatedUser = new User(0, "NullEmail", null, null, new ArrayList<>());

        // Act
        userService.updateUser(1, updatedUser);

        // Assert
        var userOpt = userService.getUserById(1);
        assertTrue(userOpt.isPresent(), "L'utilisateur ID 1 devrait exister.");
        assertEquals("NullEmail", userOpt.get().getName(), "Le nom devrait être mis à jour.");
        assertEquals(null, userOpt.get().getEmail(), "L'email devrait être null.");
    }

    /**
     * Test : vérifier que updateUser gère correctement un email null.
     */
    @Test
    void testUpdateUser_withEmptyEmail() {
        // Arrange
        var updatedUser = new User(0, "EmptyEmail", "", null, new ArrayList<>());

        // Act
        userService.updateUser(1, updatedUser);

        // Assert
        var userOpt = userService.getUserById(1);
        assertTrue(userOpt.isPresent(), "L'utilisateur ID 1 devrait exister.");
        assertEquals("EmptyEmail", userOpt.get().getName(), "Le nom devrait être mis à jour.");
        assertEquals("", userOpt.get().getEmail(), "L'email devrait être vide.");
    }
    
    /**
     * Test : vérifier que deleteUser fonctionne correctement.
     */
    @Test
    void testDeleteUser() {
        // Arrange
        var idToDelete = 1;

        // Act
        userService.deleteUser(idToDelete);

        // Assert
        var userOpt = userService.getUserById(idToDelete);
        assertTrue(userOpt.isEmpty(), "Après suppression, l'utilisateur ID 1 ne devrait plus exister.");

        var users = userService.getAllUsers();
        assertEquals(1, users.size(), "Après suppression d'un utilisateur, il devrait en rester 1.");
    }

    /**
     * Test : vérifier que deleteUser gère correctement un utilisateur inexistant.
     */
    @Test
    void testDeleteUser_nonExistingUser() {
        // Arrange
        var idToDelete = 999; // ID qui n'existe pas

        // Act
        boolean result = userService.deleteUser(idToDelete);

        // Assert
        assertTrue(!result, "La suppression d'un utilisateur inexistant devrait retourner false.");
        var users = userService.getAllUsers();
        assertEquals(2, users.size(), "Le nombre d'utilisateurs devrait rester 2.");
    }
    
    /**
     * Test : vérifier que deleteUser gère correctement un ID négatif.
     */
    @Test
    void testDeleteUser_negativeId() {
        // Arrange
        var idToDelete = -1;

        // Act
        boolean result = userService.deleteUser(idToDelete);

        // Assert
        assertTrue(!result, "La suppression avec un ID négatif devrait retourner false.");
        var users = userService.getAllUsers();
        assertEquals(2, users.size(), "Le nombre d'utilisateurs devrait rester 2.");
    }

    /**
     * Test : vérifier que deleteUser gère correctement un ID de 0.
     */
    @Test
    void testDeleteUser_zeroId() {
        // Arrange
        var idToDelete = 0;

        // Act
        boolean result = userService.deleteUser(idToDelete);

        // Assert
        assertTrue(!result, "La suppression avec un ID de 0 devrait retourner false.");
        var users = userService.getAllUsers();
        assertEquals(2, users.size(), "Le nombre d'utilisateurs devrait rester 2.");
    }

    /**
     * Test : vérifier que deleteUser gère correctement plusieurs suppressions.
     */
    @Test
    void testDeleteUser_multipleDeletions() {
        // Arrange
        var idToDelete1 = 1;
        var idToDelete2 = 2;

        // Act
        boolean result1 = userService.deleteUser(idToDelete1);
        boolean result2 = userService.deleteUser(idToDelete2);

        // Assert
        assertTrue(result1, "La suppression du premier utilisateur devrait réussir.");
        assertTrue(result2, "La suppression du deuxième utilisateur devrait réussir.");
        var users = userService.getAllUsers();
        assertEquals(0, users.size(), "Après suppression de tous les utilisateurs, il devrait en rester 0.");
    }
}
