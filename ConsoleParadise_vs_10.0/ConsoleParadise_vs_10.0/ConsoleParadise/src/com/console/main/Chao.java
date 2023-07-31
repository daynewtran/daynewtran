/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.console.main;

import com.console.helper.XImage;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author DELL
 */
public class Chao extends javax.swing.JFrame {

    Timer thoiGian;
    int width = 930;
    int height = 530;
    
    public Chao() {
        initComponents();
        lblNenMau.setVisible(false);
        init();
        hienHinh();
    }
    
    void hienHinh(){
               lblNenMau.setVisible(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= width; i=i+1) {
                    lblNenMau.setSize(i,height);
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException ex) {
                        }
                }
            }
        }).start();
    }

    void init(){
        setIconImage(XImage.APP_ICON);
        lblNenMau.setIcon(XImage.readLogo("nenChao.jpg"));
            ImageIcon hinhanh = new ImageIcon(new ImageIcon("logos//"+"nenChao.jpg").
            getImage().getScaledInstance(lblNenMau.getWidth(), 
            lblNenMau.getHeight(), Image.SCALE_SMOOTH)); 
                lblNenMau.setIcon(hinhanh);
                
                lbltrangDen.setIcon(XImage.readLogo("nenChao1.jpg"));
            ImageIcon hinhanh1 = new ImageIcon(new ImageIcon("logos//"+"nenChao1.jpg").
            getImage().getScaledInstance(lbltrangDen.getWidth(), 
            lbltrangDen.getHeight(), Image.SCALE_SMOOTH)); 
                lbltrangDen.setIcon(hinhanh1);

            thoiGian = new Timer(35, (e) -> {        
            int giaTriHienTai = prbLoad.getValue();                          
                if (giaTriHienTai < 100) {
                    prbLoad.setValue(giaTriHienTai + 1);
                    }               
            if(giaTriHienTai<33&&giaTriHienTai>5){               
                    }else if(giaTriHienTai>=33&&giaTriHienTai<=66){                
                }                        
                    if(giaTriHienTai>66){         
                    }       
            if (giaTriHienTai == 100) {
                thoiGian.stop();
                this.dispose();
                new LoGin().setVisible(true);
        
            }
        });
        thoiGian.start();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        prbLoad = new javax.swing.JProgressBar();
        lblNenMau = new javax.swing.JLabel();
        lbltrangDen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        prbLoad.setForeground(new java.awt.Color(255, 255, 255));
        prbLoad.setIndeterminate(true);
        prbLoad.setInheritsPopupMenu(true);
        prbLoad.setStringPainted(true);
        getContentPane().add(prbLoad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 910, -1));
        getContentPane().add(lblNenMau, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 530));
        getContentPane().add(lbltrangDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Chao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblNenMau;
    private javax.swing.JLabel lbltrangDen;
    private javax.swing.JProgressBar prbLoad;
    // End of variables declaration//GEN-END:variables
}
