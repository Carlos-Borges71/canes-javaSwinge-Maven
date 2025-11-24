/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.canes.dao;

import com.app.canes.model.Cliente;
import com.app.canes.model.Endereco;
import com.app.canes.model.Telefone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Borges
 */
public class ClienteDAO {
    
    private static ClienteDAO instance;
    
    private TelefoneDAO telefoneDAO = TelefoneDAO.getInstance(); 
    private EnderecoDAO enderecoDAO = EnderecoDAO.getInstance(); 
    
   

    
     public static ClienteDAO getInstance() {
        if (instance == null) {
            instance = new ClienteDAO();
        }
        return instance;
    }

    public Cliente save(Cliente c) {
        if (c.getId() == null) return insert(c);
        else return update(c);
    }

    private Cliente insert(Cliente c) {
        String sql = "INSERT INTO cliente (nome, data) VALUES (?, ?)";
        try (Connection conn = ConectaDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNome());            
            ps.setDate(2, new java.sql.Date(c.getData().getTime()));
           
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                c.setId(rs.getInt(1));
            }
            
            
            
          telefoneDAO.insert(c, conn);
         enderecoDAO.insert(c, conn);
         
                  
            //insertEndereco(c, conn);
            
        } catch (SQLException e) { e.printStackTrace(); }
        return c;
    }
    
    

    private Cliente update(Cliente c) {
        String sql = "UPDATE cliente SET nome=?, data=? WHERE id=?";
         try (Connection conn = ConectaDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             

            ps.setString(1, c.getNome());           
            ps.setDate(2, new java.sql.Date(c.getData().getTime()));
            
            ps.setInt(3, c.getId());
            ps.executeUpdate();
          
            telefoneDAO.update(c, conn);
            enderecoDAO.update(c, conn);
            
        } catch (SQLException e) { e.printStackTrace(); }
        return c;
    }
    
    

    

    public List<Cliente> findAll() {
        List<Cliente> lista = new ArrayList<>();
        
        String sql = """
            SELECT c.*, 
                   t.id AS tel_id, t.numero AS tel_numero,
                   e.id AS end_id, e.logradouro, e.numero AS end_numero,
                   e.bairro, e.cidade, e.estado, e.cep
            FROM cliente c
            LEFT JOIN telefone t ON t.id_cliente = c.id
            LEFT JOIN endereco e ON e.id_cliente = c.id
        """;
        try (Connection conn = ConectaDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Telefone tel = null;
                if (rs.getInt("tel_id") != 0) {
                    tel = new Telefone(
                            rs.getInt("tel_id"),
                            rs.getString("tel_numero")
                    );
                }
                        System.out.println();

                Endereco end = null;
                if (rs.getInt("end_id") != 0) {
                    end = new Endereco(
                            rs.getInt("end_id"),
                            rs.getString("logradouro"),
                            rs.getString("end_numero"),
                            rs.getString("bairro"),
                            rs.getString("cidade"),
                            rs.getString("estado"),
                            rs.getString("cep")
                    );
                }

                Cliente c = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),                        
                        rs.getDate("data"),                       
                        tel,
                        end
                );

                lista.add(c);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
    
   
    public Cliente findById(int id) {
        String sql = """
            SELECT c.*, 
                   t.id AS tel_id, t.numero AS tel_numero,
                   e.id AS end_id, e.logradouro, e.numero AS end_numero,
                   e.bairro, e.cidade, e.estado, e.cep
            FROM cliente c
            LEFT JOIN telefone t ON t.id_usuario = c.id
            LEFT JOIN endereco e ON e.id_usuario = c.id
            WHERE c.id = ?
        """;
        try (Connection conn = ConectaDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
             ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) return null;

            Telefone tel = (rs.getInt("tel_id") != 0)
                    ? new Telefone(rs.getInt("tel_id"), rs.getString("tel_numero"))
                    : null;

            Endereco end = (rs.getInt("end_id") != 0)
                    ? new Endereco(
                        rs.getInt("end_id"),
                        rs.getString("logradouro"),
                        rs.getString("end_numero"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("cep")
                    )
                    : null;

            return new Cliente(
                    rs.getInt("id"),
                    rs.getString("nome"),                  
                    rs.getDate("data"),                    
                    tel,
                    end
            );
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public void delete(int id) {
        String sql = "DELETE FROM cliente WHERE id=?";
        try (Connection conn = ConectaDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
