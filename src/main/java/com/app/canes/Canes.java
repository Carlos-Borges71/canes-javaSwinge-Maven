/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.app.canes;

import com.app.canes.view.Login;

/**
 *
 * @author Carlos Borges
 */
public class Canes {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {         
        Login view = new Login();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setTitle("LOGIN");
        
        
        });
    }
}
