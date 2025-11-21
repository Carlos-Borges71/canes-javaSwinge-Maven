/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.canes.dao;

import com.app.canes.model.Produto;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Carlos Borges
 */
public class ProdutoDAO {
    
   
    
       private final Map<Integer, Produto> map = new LinkedHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public ProdutoDAO() {
        // Produtos baseados no Figma
        save(new Produto(null, 34580,  "Calça",12, 120.9));
        save(new Produto(null, 2457, "Vestido", 28, 102.0));
    }

    public List<Produto> findAll() {
        return new ArrayList<>(map.values());
    }

    public Produto findById(Integer id) {
        return map.get(id);
    }

    public Produto save(Produto p) {
         if (p.getId() == null) {
            p.setId((int) seq.getAndIncrement());
        }
        map.put(p.getId(), p);
        return p;
    }

    public void delete(Integer id) {
        map.remove(id);
    }
}
