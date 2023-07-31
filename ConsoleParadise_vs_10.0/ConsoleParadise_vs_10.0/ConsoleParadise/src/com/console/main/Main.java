
package com.console.main;

import com.console.component.Menu;
import com.console.component.Menu1;
import com.console.event.EventMenu;
import com.console.helper.Auth;
import com.console.helper.XImage;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Main extends javax.swing.JFrame {
    private DoiMk dk;
    int index=0;
    boolean check=false;
    public Main() {
        initComponents();
        init();
    }
    void init(){
        setIconImage(XImage.APP_ICON);
        btnBack.setVisible(false);
        pnlUser.setVisible(check); 
        dk=new DoiMk();
        if(Auth.isManager()){
            EventMenu event = new EventMenu() {
            @Override
            public void selected(int index) {
                if(index==6){
                    check=!check;
                    pnlUser.setVisible(check);
                }else{                    
                    pnlUser.setVisible(false);
                    btnBack.setVisible(true);
                    slideshow.slideTo(index + 1);
                }
                if(index==0){
                    lblTieuDe.setText("SẢN PHẨM");
                    return;
                }
                if(index==1){
                    lblTieuDe.setText("NHÂN VIÊN");
                    return;
                }
                if(index==2){
                    lblTieuDe.setText("KHÁCH HÀNG");
                    return;
                }
                if(index==3){
                    lblTieuDe.setText("ĐƠN HÀNG");
                    return;
                }
                if(index==4){
                    lblTieuDe.setText("CHẤM CÔNG");
                    return;
                }
                if(index==5){
                    lblTieuDe.setText("THỐNG KÊ");
                    return;
                }
                
            }
        };
        Menu menu = new Menu();
        menu.initMenu(event);
        slideshow.initSlideshow(menu,  
                new PanelSanPham(),
                new PanelNhanVien(),
                new PanelKhachHang(),
                new PanelDonHang(),
                new PanelChamCong(),
                new PanelThongKe());
        }else if(!Auth.isManager()){
            pnlUser.setLocation(10, 350);
            EventMenu event = new EventMenu() {
            @Override
            public void selected(int index) {
                if(index==3){
                    check=!check;
                    pnlUser.setVisible(check);
                }else{                    
                    pnlUser.setVisible(false);
                    btnBack.setVisible(true);
                    slideshow.slideTo(index + 1);
                }
                if(index==0){
                    lblTieuDe.setText("SẢN PHẨM");
                    return;
                }
                
                if(index==1){
                    lblTieuDe.setText("KHÁCH HÀNG");
                    return;
                }
                if(index==2){
                    lblTieuDe.setText("ĐƠN HÀNG");
                    return;
                }
                
                
            }
        };
        Menu1 menu1 = new Menu1();
        menu1.initMenu(event);
        slideshow.initSlideshow(menu1,  
                new PanelSanPham(),               
                new PanelKhachHang(),
                new PanelDonHang()
                );
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pictureBox1 = new com.console.swing.PictureBox();
        pnlUser = new com.console.swing.PanelBorder();
        button11 = new com.console.swing.Button1();
        button12 = new com.console.swing.Button1();
        button13 = new com.console.swing.Button1();
        slideshow = new slideshow.Slideshow();
        btnBack = new com.console.swing.Button1();
        lblTieuDe = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/img/nenHome.jpg"))); // NOI18N

        pnlUser.setBackground(new java.awt.Color(32, 46, 75));
        pnlUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        button11.setText("Thoát");
        button11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11ActionPerformed(evt);
            }
        });
        pnlUser.add(button11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 190, -1));

        button12.setText("Đổi mật khẩu");
        button12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button12ActionPerformed(evt);
            }
        });
        pnlUser.add(button12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 190, -1));

        button13.setText("Đăng xuất");
        button13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button13ActionPerformed(evt);
            }
        });
        pnlUser.add(button13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 190, -1));

        pictureBox1.add(pnlUser);
        pnlUser.setBounds(140, 460, 210, 130);

        slideshow.setForeground(new java.awt.Color(255, 255, 255));
        pictureBox1.add(slideshow);
        slideshow.setBounds(-10, 80, 1050, 520);

        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        pictureBox1.add(btnBack);
        btnBack.setBounds(20, 20, 60, 35);

        lblTieuDe.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(255, 255, 255));
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("Trang chủ");
        pictureBox1.add(lblTieuDe);
        lblTieuDe.setBounds(10, 10, 1010, 60);

        getContentPane().add(pictureBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        slideshow.slideTo(index);
        lblTieuDe.setText("TRANG CHỦ");
        btnBack.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void button11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_button11ActionPerformed

    private void button13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button13ActionPerformed
        Auth.clear();
        this.dispose();
        new LoGin().setVisible(true);
    }//GEN-LAST:event_button13ActionPerformed

    private void button12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button12ActionPerformed
        pnlUser.setVisible(check);
        dk.setVisible(true);
    }//GEN-LAST:event_button12ActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.console.swing.Button1 btnBack;
    private com.console.swing.Button1 button11;
    private com.console.swing.Button1 button12;
    private com.console.swing.Button1 button13;
    private javax.swing.JLabel lblTieuDe;
    private com.console.swing.PictureBox pictureBox1;
    private com.console.swing.PanelBorder pnlUser;
    private slideshow.Slideshow slideshow;
    // End of variables declaration//GEN-END:variables
}
