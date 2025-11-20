/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.canes.model;

import java.util.Date;

/**
 *
 * @author Carlos Borges
 */
public class Cliente {
    private int id;
    private String nome;
    private Date data;
    
    public Cliente(){
        
    }

    public Cliente(int id, String nome, Date data) {
        this.id = id;
        this.nome = nome;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return this.id == other.id;
    }
    
}
