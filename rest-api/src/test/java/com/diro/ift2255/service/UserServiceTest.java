package com.diro.ift2255.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        //Arange & All
        var users = userService.getAllUsers();
        //Assert

        assertEquals(users.size(), 2, "Il devrait avoir 2 utilisateurs initialement.");
    }

    @Test
    void testGetUserById(){
        //Arrange 
        var userId = 1;
        //All
        var userOpt = userService.getUserById(1);
        //Assert
        assertTrue(userOpt.isPresent(), "L'utilisateur avec l'ID 1 devrait exister.");
        assertEquals("Alice", userOpt.get().getName(), "Le nom de l'utilisateur de l'iD 1 devrait être Alice.");
        assertEquals("alice@example.com", userOpt.get().getEmail(), "Le email de l'utilisateur de l'iD 1 devrait être alice@example.com.");
    }

    @Test
    void testGetUserById_zero() {
        // Arrange & Act
        var userOpt = userService.getUserById(0);

        // Assert
        assertTrue(userOpt.isEmpty(),
            "Aucun utilisateur possède un ID de 0.")
    }

    @Test testGetUserById_idNegatif() {
        // Arrange & Act
        var userOpt = userService.getUserById(-1);

        // Assert
        assertTrue(userOpt.isEmpty(),
            "Aucun utilisateur possède un ID négatif.")
    }
}
