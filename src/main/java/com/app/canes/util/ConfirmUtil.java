/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.canes.util;

/**
 *
 * @author Carlos Borges
 */
import javax.swing.JOptionPane;
import java.awt.Component;

public class ConfirmUtil {

    /**
     * Exibe uma caixa de confirmação com "Sim" e "Não".
     * Retorna true se o usuário confirmar.
     */
    public static boolean confirmar(Component parent, String mensagem) {
        int opcao = JOptionPane.showConfirmDialog(
                parent,
                mensagem,
                "Confirmação",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        return opcao == JOptionPane.YES_OPTION;
    }

    /**
     * Método específico para exclusão.
     */
    public static boolean confirmarExclusao(Component parent) {
        return confirmar(parent, "Deseja realmente excluir este registro?");
    }
}
