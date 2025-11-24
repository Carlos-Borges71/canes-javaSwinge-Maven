/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.canes.model;

/**
 *
 * @author Carlos Borges
 */
public class Telefone {
    private Integer id;
    private String numero;
    
    
    public Telefone(){
        
    }

    public Telefone(Integer id, String numero) {
        this.id = id;
        this.numero = numero;
    }
   

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

   
    
}
