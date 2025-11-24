/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.canes.dao;

import com.app.canes.model.Produto;
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
public class ProdutoDAO {

    private static ProdutoDAO instance;

    public static ProdutoDAO getInstance() {
        if (instance == null) {
            instance = new ProdutoDAO();
        }
        return instance;
    }

    public Produto save(Produto p) {
        if (p.getId() == null) {
            return insert(p);
        } else {
            return update(p);
        }
    }

    private Produto insert(Produto p) {
        String sql = "INSERT INTO produto (codigo, nome, estoque, valor) VALUES (?,?,?,?)";
        try (Connection conn = ConectaDAO.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, p.getCodigo());
            ps.setString(2, p.getNome());
            ps.setInt(3, p.getEstoque());
            ps.setDouble(4, p.getValor());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                p.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    private Produto update(Produto p) {
        String sql = "UPDATE produto SET codigo=?, nome=?, estoque=?, valor=? WHERE id=?";
        try (Connection conn = ConectaDAO.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getCodigo());
            ps.setString(2, p.getNome());
            ps.setInt(3, p.getEstoque());
            ps.setDouble(4, p.getValor());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public List<Produto> findAll() {
        List<Produto> list = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        try (Connection conn = ConectaDAO.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCodigo(rs.getInt("codigo"));
                p.setEstoque(rs.getInt("estoque"));
                p.setValor(rs.getDouble("valor"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Produto findById(int id) {
        String sql =  """
            SELECT id, nome,codigo, estoque, valor                  
            FROM produto             
            WHERE id = ?
        """;

        try (Connection conn = ConectaDAO.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCodigo(rs.getInt("codigo"));
                p.setEstoque(rs.getInt("estoque"));
                p.setValor(rs.getDouble("valor"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(int id) {

        String sql = "DELETE FROM produto WHERE id=?";
        try (Connection conn = ConectaDAO.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> findByCliente(int idCliente) {
        List<Produto> lista = new ArrayList<>();

        String sql = "SELECT * FROM produto WHERE id_cliente = ?";

        try (Connection conn = ConectaDAO.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getInt("estoque"),
                        rs.getDouble("valor")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

}
