/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.canes.dao;

import com.app.canes.model.Endereco;
import com.app.canes.model.Telefone;
import com.app.canes.model.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Carlos Borges
 */
public class UsuarioDAO {

    private static UsuarioDAO instance;

    private final Map<Integer, Usuario> map = new LinkedHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public UsuarioDAO() {

        // Seed inicial baseado no seu Figma/PDF
        save(new Usuario(
                null,                
                "Carlos Borges",
                "VENDA",
                "venda",
                new Date(),
                "1234",
                new Telefone(1, "99999-1111"),
                new Endereco(1, "Rua das Flores", "123", "Centro", "São Paulo", "SP", "23000-000")));

        save(new Usuario(
                null,
                "Maria Silva",
                "ADMINISTRATIVO",                
                "admin",
                new Date(),
                "1234",
                new Telefone(2, "98888-2222"),
                new Endereco(2, "Av. Brasil", "456", "Copacabana", "Rio de Janeiro", "RJ", "22000-000")
                
                
        ));
        
        save(new Usuario(
                null,
                "José da Silva",
                "GERÊNCIA",                
                "geren",
                new Date(),
                "1234",
                new Telefone(3, "99999-1111"),
                new Endereco(3, "Rua das Flores", "123", "Centro", "São Paulo", "SP", "23000-000")));

    }

    public static UsuarioDAO getInstance() {
        if (instance == null) {
            instance = new UsuarioDAO();
        }
        return instance;
    }

    public List<Usuario> findAll() {
        return new ArrayList<>(map.values());
    }

    public Usuario findById(Integer id) {
        return map.get(id);
    }

    public Usuario save(Usuario u) {
        if (u.getId() == null) {
            u.setId((int) seq.getAndIncrement());
        }
        map.put(u.getId(), u);
        return u;
    }

    public void delete(Integer id) {
        map.remove(id);
    }

    public Usuario autenticar(String login, String senha) {
        for (Usuario u : map.values()) {

            if ((u.getLogin().equalsIgnoreCase(login)) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }
}
