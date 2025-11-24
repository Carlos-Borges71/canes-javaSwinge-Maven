/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.canes.dao;

import com.app.canes.model.PessoaContato;
import com.app.canes.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Carlos Borges
 */
public class EnderecoDAO {
    
    private static EnderecoDAO instance;
    private EnderecoDAO() {}

    public static EnderecoDAO getInstance() {
        if (instance == null) instance = new EnderecoDAO();
        return instance;
    }

    public void  insert(PessoaContato p, Connection conn) throws SQLException {
        if (p.getEndereco()== null) return ;
            String sql = """
                         INSERT INTO endereco (cep, estado, cidade, bairro, logradouro, numero, id_usuario, id_cliente) 
                         VALUES (?,?,?,?,?,?,?,?)
                         """;
            PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, p.getEndereco().getCep());
                ps.setString(2, p.getEndereco().getEstado());
                ps.setString(3, p.getEndereco().getCidade());
                ps.setString(4, p.getEndereco().getBairro());
                ps.setString(5, p.getEndereco().getLogradouro());
                ps.setString(6, p.getEndereco().getNumero());
                
                if (p instanceof Usuario) {
            ps.setInt(7, p.getId());
            ps.setNull(8, java.sql.Types.INTEGER);
        } else {
            ps.setNull(7, java.sql.Types.INTEGER);
            ps.setInt(8, p.getId());
        }
                ps.executeUpdate();
           
    }
    
    public void update(PessoaContato p, Connection conn) throws SQLException {
        if (p.getEndereco()== null) return;

        String sql = """
                         UPDATE endereco SET cep=?, estado=?, cidade=?, bairro=?, logradouro=?, numero=?
                         WHERE (id_usuario=? OR id_cliente=?)
                         """;
           

        PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, p.getEndereco().getCep());
                ps.setString(2, p.getEndereco().getEstado());
                ps.setString(3, p.getEndereco().getCidade());
                ps.setString(4, p.getEndereco().getBairro());
                ps.setString(5, p.getEndereco().getLogradouro());
                ps.setString(6, p.getEndereco().getNumero());
                
                if (p instanceof Usuario) {
            ps.setInt(7, p.getId());
            ps.setNull(8, java.sql.Types.INTEGER);
        } else {
            ps.setNull(7, java.sql.Types.INTEGER);
            ps.setInt(8, p.getId());
        }
                ps.executeUpdate();
    }

    
}



