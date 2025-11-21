/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.canes.dao;

import com.app.canes.model.Cliente;
import com.app.canes.model.Endereco;
import com.app.canes.model.Telefone;
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
public class ClienteDAO {
    
    private final Map<Integer, Cliente> map = new LinkedHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public ClienteDAO() {
        // Cliente inicial
        save(new Cliente(null, "Empresa X", new Date(),
                 new Telefone(2, "99999-1111"),
    new Endereco(3, "Rua das Flores", "123", "Centro", "São Paulo", "SP", "23000-000")));
    }

    public List<Cliente> findAll() {
        return new ArrayList<>(map.values());
    }

    public Cliente findById(Long id) {
        return map.get(id);
    }

    public Cliente save(Cliente c) {
        if (c.getId() == null) {
            c.setId((int) seq.getAndIncrement());
        }
        map.put(c.getId(), c);
        return c;
            
        }
   

    public void delete(Long id) {
        map.remove(id);
    }
}
