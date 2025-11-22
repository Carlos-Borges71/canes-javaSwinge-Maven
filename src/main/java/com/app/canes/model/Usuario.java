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
public class Usuario {
    private Integer id;
    private String nome;
    private String setor;    
    private String login;
    private Date data;
    private String senha;
    private Telefone telefone;
    private Endereco endereco;
    
    public Usuario(){        
    }

    public Usuario(Integer id,  String nome, String setor,String login, Date data, String senha, Telefone telefone, Endereco endereco) {
        this.id = id;
        this.setor = setor;
        this.nome = nome;
        this.login = login;
        this.data = data;
        this.senha = senha;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
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
        final Usuario other = (Usuario) obj;
        return this.id == other.id;
    }
    
    
}
