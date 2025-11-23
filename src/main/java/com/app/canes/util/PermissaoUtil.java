/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.canes.util;

import com.app.canes.model.Usuario;
import javax.swing.JButton;

/**
 *
 * @author Carlos Borges
 */
public class PermissaoUtil {

    public static void aplicarPermissoes(Usuario usuario, 
                                         JButton btnCadastrar,
                                         JButton btnAtualizar,
                                         JButton btnDeletar) 
    {
        String setor = usuario.getSetor().toUpperCase();

        switch (setor) {

            case "VENDA":
                // vendedor só consulta                
                if (btnAtualizar != null) btnAtualizar.setEnabled(false);
                if (btnDeletar != null) btnDeletar.setEnabled(false);
                
                break;

            case "ADMINISTRATIVO":
                // administrativo não pode vendas ou admin geral
                if (btnDeletar != null) btnDeletar.setEnabled(false);
                break;

            case "GERÊNCIA":
                // gerente tem tudo → nenhuma restrição
                break;

            default:
                System.out.println("Setor desconhecido para permissões.");
        }
    }
}
