/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.console.main;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author DELL
 */
public class PanelHome extends javax.swing.JPanel {

    /**
     * Creates new form PanelHome
     */
    public PanelHome() {
        initComponents();
        String link = "src\\img\\nenHome.jpg";
        ImageIcon hinhanh = new ImageIcon(new ImageIcon(link).
            getImage().getScaledInstance(lblHinhAnh.getWidth(), 
            lblHinhAnh.getHeight(), Image.SCALE_SMOOTH));            
            lblHinhAnh.setIcon(hinhanh);
    }
    void init(){
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHinhAnh = new javax.swing.JLabel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblHinhAnh;
    // End of variables declaration//GEN-END:variables
}