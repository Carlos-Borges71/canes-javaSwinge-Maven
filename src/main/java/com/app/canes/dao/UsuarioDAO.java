/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.canes.dao;

import com.app.canes.model.Endereco;
import com.app.canes.model.Telefone;
import com.app.canes.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Borges
 */
public class UsuarioDAO {

    private static UsuarioDAO instance;
    
   private TelefoneDAO telefoneDAO = TelefoneDAO.getInstance();
   private EnderecoDAO enderecoDAO = EnderecoDAO.getInstance();

    private UsuarioDAO () {}
    


    public static UsuarioDAO getInstance() {
        if (instance == null) {
            instance = new UsuarioDAO();
        }
        return instance;
    }

    // --------------------------------------------------------
    // LISTAR TODOS
    // --------------------------------------------------------
    public List<Usuario> findAll() {
        List<Usuario> lista = new ArrayList<>();

        String sql = """
            SELECT u.*, 
                   t.id AS tel_id, t.numero AS tel_numero,
                   e.id AS end_id, e.logradouro, e.numero AS end_numero,
                   e.bairro, e.cidade, e.estado, e.cep
            FROM usuario u
            LEFT JOIN telefone t ON t.id_usuario = u.id
            LEFT JOIN endereco e ON e.id_usuario = u.id
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

                Usuario u = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("setor"),
                        rs.getString("login"),
                        rs.getDate("data"),
                        rs.getString("senha"),
                        tel,
                        end
                );

                lista.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // --------------------------------------------------------
    // SALVAR (INSERT + UPDATE)
    // --------------------------------------------------------
    public Usuario save(Usuario u) {

        if (u.getId() == null) {
            return insert(u);
        } else {
            return update(u);
        }
    }

    private Usuario insert(Usuario u) {
        String sql = """
            INSERT INTO usuario (nome, setor, login, data, senha)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConectaDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, u.getNome());
            ps.setString(2, u.getSetor());
            ps.setString(3, u.getLogin());
            ps.setDate(4, new java.sql.Date(u.getData().getTime()));
            ps.setString(5, u.getSenha());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                u.setId(rs.getInt(1));
            }
            
          telefoneDAO.insert(u, conn);
            
           enderecoDAO.insert(u, conn);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }

    private Usuario update(Usuario u) {
        String sql = """
            UPDATE usuario 
            SET nome=?, setor=?, login=?, data=?, senha=?
            WHERE id=?
        """;

        try (Connection conn = ConectaDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getNome());
            ps.setString(2, u.getSetor());
            ps.setString(3, u.getLogin());
            ps.setDate(4, new java.sql.Date(u.getData().getTime()));
            ps.setString(5, u.getSenha());
            ps.setInt(6, u.getId());
            ps.executeUpdate();
           
            telefoneDAO.update(u, conn);
            enderecoDAO.update(u, conn);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }

    // --------------------------------------------------------
    // DELETE
    // --------------------------------------------------------
    public void delete(Integer id) {
        String sql = "DELETE FROM usuario WHERE id=?";

        try (Connection conn = ConectaDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --------------------------------------------------------
    // AUTENTICAÇÃO
    // --------------------------------------------------------
    public Usuario autenticar(String login, String senha) {

        String sql = "SELECT * FROM usuario WHERE login=? AND senha=?";

        try (Connection conn = ConectaDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return findById(rs.getInt("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // --------------------------------------------------------
    // BUSCAR POR ID (carrega telefone + endereço)
    // --------------------------------------------------------
    public Usuario findById(Integer id) {

        String sql = """
            SELECT u.*, 
                   t.id AS tel_id, t.numero AS tel_numero,
                   e.id AS end_id, e.logradouro, e.numero AS end_numero,
                   e.bairro, e.cidade, e.estado, e.cep
            FROM usuario u
            LEFT JOIN telefone t ON t.id_usuario = u.id
            LEFT JOIN endereco e ON e.id_usuario = u.id
            WHERE u.id = ?
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

            return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("setor"),
                    rs.getString("login"),
                    rs.getDate("data"),
                    rs.getString("senha"),
                    tel,
                    end
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

 
}
