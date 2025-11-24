/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.canes.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Carlos Borges
 */
public class Cliente implements PessoaContato{
    private Integer id;
    private String nome;
    private Date data;
    private Telefone telefone;
    private Endereco endereco;
    private List<Produto> produto;
    public Cliente(){
        
    }

    public Cliente(Integer id, String nome, Date data, Telefone telefone, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    public Endereco getEndereco() {
        return endereco;
    }

    @Override
    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }
    
    
    
}
