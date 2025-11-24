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
public class TelefoneDAO {

    // SINGLETON
    private static TelefoneDAO instance;
    private TelefoneDAO() {}

    public static TelefoneDAO getInstance() {
        if (instance == null) instance = new TelefoneDAO();
        return instance;
    }

    // ============================
    // INSERT
    // ============================
    public void insert(PessoaContato p, Connection conn) throws SQLException {
        if (p.getTelefone() == null) return;

        String sql = """
            INSERT INTO telefone (numero, id_usuario, id_cliente)
            VALUES (?, ?, ?)
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getTelefone().getNumero());

        if (p instanceof Usuario) {
            ps.setInt(2, p.getId());
            ps.setNull(3, java.sql.Types.INTEGER);
        } else {
            ps.setNull(2, java.sql.Types.INTEGER);
            ps.setInt(3, p.getId());
        }

        ps.executeUpdate();
    }

    // ============================
    // UPDATE
    // ============================
    public void update(PessoaContato p, Connection conn) throws SQLException {
        if (p.getTelefone() == null) return;

        String sql = """
            UPDATE telefone SET numero=?
            WHERE (id_usuario=? OR id_cliente=?)
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getTelefone().getNumero());

        if (p instanceof Usuario) {
            ps.setInt(2, p.getId());
            ps.setNull(3, java.sql.Types.INTEGER);
        } else {
            ps.setNull(2, java.sql.Types.INTEGER);
            ps.setInt(3, p.getId());
        }

        ps.executeUpdate();
    }
}
