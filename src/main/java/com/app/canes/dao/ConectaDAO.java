/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.canes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Carlos Borges
 */
public class ConectaDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/canesdb?useSSL=false&allowPublicKeyRetrieval=true";
private static final String USER = "root";
private static final String PASS = "316497";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

