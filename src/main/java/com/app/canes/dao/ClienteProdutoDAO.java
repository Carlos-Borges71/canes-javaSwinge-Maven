/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.canes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Carlos Borges
 */
public class ClienteProdutoDAO {
    
     private static ClienteProdutoDAO instance;
     
    public static ClienteProdutoDAO getInstance() {
        if (instance == null) {
            instance = new ClienteProdutoDAO();
        }
        return instance;
    }

    public void addProdutoToCliente(int idCliente, int idProduto) {
        String sql = "INSERT INTO cliente_produto (id_cliente, id_produto) VALUES (?, ?)";
        try (Connection conn = ConectaDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ps.setInt(2, idProduto);
            ps.executeUpdate();
            
            
        } catch (SQLException e) { e.printStackTrace(); }
    }

}

