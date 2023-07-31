/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.console.main;

import com.console.dao.NhanVienDAO;
import com.console.helper.Auth;
import com.console.helper.DialogHelper;
import com.console.helper.XImage;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author DELL
 */
public class DoiMk extends javax.swing.JFrame {

    NhanVienDAO dao = new NhanVienDAO();
    public DoiMk() {
        initComponents();
        init();
    }

    void init(){
        setIconImage(XImage.APP_ICON);
        txtUser.setText(Auth.user.getMaNV());
        if(Auth.user.getImg().equals("")||Auth.user.getImg().equals(" ") ){
            avatar.setIcon(XImage.readLogo(Auth.user.getImg()));
            ImageIcon hinhanh = new ImageIcon(new ImageIcon("logos//avtTrang.png").
            getImage().getScaledInstance(avatar.getWidth(), 
            avatar.getHeight(), Image.SCALE_SMOOTH)); 
                avatar.setIcon(hinhanh);                
                avatar.setToolTipText(Auth.user.getImg());
                
        }else{
            ImageIcon hinhanh = new ImageIcon(new ImageIcon("logos//"+Auth.user.getImg()).
            getImage().getScaledInstance(avatar.getWidth(), 
            avatar.getHeight(), Image.SCALE_SMOOTH)); 
                avatar.setIcon(hinhanh);                
                avatar.setToolTipText(Auth.user.getImg()); 
            
        }
    }
    void DoiMK(){
        if(!txtMK1.getText().equals(txtMK2.getText())){
            DialogHelper.alert(this, "Mật khẩu xác nhận không khớp");
        }else{
            dao.DoiMK(txtUser.getText(),txtMK1.getText());
            DialogHelper.alert(this, "Mật khẩu đã được cập nhật");
            this.dispose();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        avatar = new com.console.component.ImageAvatar();
        txtMK2 = new com.console.swing.Passwordfield();
        button1 = new com.console.swing.Button();
        jLabel2 = new javax.swing.JLabel();
        txtMK1 = new com.console.swing.Passwordfield();
        txtUser = new com.console.swing.TextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        avatar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(avatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 110, 100));

        txtMK2.setLabelText("Mật khẩu xác nhận");
        txtMK2.setShowAndHide(true);
        txtMK2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMK2ActionPerformed(evt);
            }
        });
        jPanel2.add(txtMK2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 290, -1));

        button1.setBackground(new java.awt.Color(78, 143, 227));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Đổi mật khẩu");
        button1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel2.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 260, -1));

        jLabel2.setForeground(new java.awt.Color(109, 109, 109));
        jLabel2.setText("Bạn muốn đăng nhập?");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 122, -1));

        txtMK1.setLabelText("Mật khẩu");
        txtMK1.setShowAndHide(true);
        jPanel2.add(txtMK1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 290, -1));

        txtUser.setEnabled(false);
        txtUser.setLabelText("Tài khoản");
        jPanel2.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 290, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(78, 143, 227));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ĐỔI MẬT KHẨU");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel3.setForeground(new java.awt.Color(109, 109, 109));
        jLabel3.setText("Thoát");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 460));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        Auth.clear();
        new LoGin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void txtMK2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMK2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMK2ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        
        if(txtMK1.getText().equals("")){
            txtMK1.setHelperText("Không được bỏ trống");
            return;
        }
        if(txtMK2.getText().equals("")){
            txtMK2.setHelperText("Không được bỏ trống");
            return;
        }
        DoiMK();
    }//GEN-LAST:event_button1ActionPerformed

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
            java.util.logging.Logger.getLogger(DoiMk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoiMk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoiMk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoiMk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoiMk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.console.component.ImageAvatar avatar;
    private com.console.swing.Button button1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.console.swing.Passwordfield txtMK1;
    private com.console.swing.Passwordfield txtMK2;
    private com.console.swing.TextField txtUser;
    // End of variables declaration//GEN-END:variables
}
