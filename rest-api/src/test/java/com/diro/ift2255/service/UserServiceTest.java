package com.diro.ift2255.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.diro.ift2255.model.User;
import com.diro.ift2255.service.*;

public class UserServiceTest {
    private UserService userService; //créer une instance pour pouvoir la testr

    @BeforeAll
    static void initAll(){

    }

    @BeforeEach
    void setup(){
        userService = new UserService();
    }

    @Test
    void testGetAllUsers(){
        //Arange & Act
        var users = userService.getAllUsers();
        //Assert

        assertEquals(users.size(), 2, "Il devrait avoir 2 utilisateurs initialement.");
    }

    @Test
    void testGetUserById(){
        //Arrange 
        var userId = 1;
        //Act
        var userOpt = userService.getUserById(userId);
        //Assert
        assertTrue(userOpt.isPresent(), "L'utilisateur avec l'ID 1 devrait exister.");
        assertEquals("Alice", userOpt.get().getName(), "Le nom de l'utilisateur de l'iD 1 devrait être Alice.");
        assertEquals("alice@example.com", userOpt.get().getEmail(), "Le email de l'utilisateur de l'iD 1 devrait être alice@example.com.");
    }
    @Test
    void testCreateUser(){
        //Arrange
        User newUser = new User(0, "Marie", "marie@example.com");

        //Act
        userService.createUser(newUser);
        
        //Assert
        var users = userService.getAllUsers(); 
        //Vérifie qu'on est rendu à 3 users
        assertEquals(3, users.size(), "Le nombre d'utilisateur devrait avoir augmenté à 3."); 
        // Vérifie que l'ID a bien été attribué automatiquement 
        assertEquals(newUser.getId(), 3, "Le nouvel utilisateur devrait avoir l'id 3." );
        // Vérifier la présence de l'utilisateur dans le système 
        var newUserRetrieved = userService.getUserById(3);
        assertTrue(newUserRetrieved.isPresent(),"Le nouvel utilisateur devrait être présent dans le système.");
        // Vérifier que les données de l'utilisateur sont les bonnes 
        assertEquals(newUser.getName(), "Marie","Le nouvel utilisateur devrait avoir comme nom Marie.");
        assertEquals(newUser.getEmail(), "marie@example.com","Le nouvel utilisateur devrait avoir comme email marie@example.com.");
    }
    @Test
    void testUpdateUser(){
        //Arrange
        User updatedUser = new User(0, "Marie", "marie@example.com");
        //Act
        userService.updateUser(1, updatedUser);
        //Assert
        // Vérifier que l'utilisateur existe
        var userOpt = userService.getUserById(1);
        assertTrue(userOpt.isPresent(), "L'utilisateur 1 devrait exister après la mise à jour.");
        //Vérifier que l'ID a été modifier 
        assertEquals(updatedUser.getId(),1, "L'id devrait être mis à jour à 1.");
        //Vérifier que les données ont étés remplacées
        assertEquals("Marie", userOpt.get().getName(), "Le nom doit être remplacé par Marie.");
        assertEquals("marie@example.com", userOpt.get().getEmail(), "Le courriel doit être remplacé par marie@example.com");
    }

    @Test
    void testDeleteUser(){
        //Arrange & act
        userService.deleteUser(1);
        //Assert
        var userOpt = userService.getUserById(1);
        var users = userService.getAllUsers();
        //Vérifier que l'utilisateur 3 n'est plus présent
        assertFalse(userOpt.isPresent(),"L'utilisateur 1 ne devrait plus exister.");
        // Vérifier que le nombre d'utilisateur a décendu à 3
        assertEquals(1, users.size(),"Le nombre d'utilisateur devrait être descendu à 1.");
    }

}
