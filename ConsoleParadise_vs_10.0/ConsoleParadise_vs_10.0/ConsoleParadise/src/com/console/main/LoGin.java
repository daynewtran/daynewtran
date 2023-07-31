
package com.console.main;

import com.console.dao.LuuThongTinDAO;
import com.console.dao.NhanVienDAO;
import com.console.entity.LuuThongTin;
import com.console.entity.NhanVien;
import com.console.helper.Auth;
import com.console.helper.DialogHelper;
import com.console.helper.XImage;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.Timer;


public class LoGin extends javax.swing.JFrame {
        NhanVienDAO dao = new NhanVienDAO();
        LuuThongTinDAO dao2 = new LuuThongTinDAO();
    public LoGin() {
        initComponents();
        init();
        
    }
    void loGin(){
        if(chkLuu.isSelected()){
            dao2.XoaTT();
            dao2.LuuTT(txtUser.getText(), txtpass.getText());            
        }else{
            dao2.XoaTT();
        }
        String user = txtUser.getText();
        String mk = new String(txtpass.getPassword());
        if(!txtUser.getText().equals("")){
            NhanVien nv = dao.findById(user);
            if(nv!=null){
                Auth.user = nv;
                String mk2 = nv.getMatKhau();
                if(mk2.equals(mk)){
                    
                    this.dispose();
                    new Main().setVisible(true);
                }
                else{
                    DialogHelper.alert(this, "Thông tin đăng nhập không chính xác");
                }
            }else{
                DialogHelper.alert(this, "Tài khoản không tồn tại");
            }
        }
        }
   public void init(){
       setIconImage(XImage.APP_ICON);
       LuuThongTin model = dao2.findById();
       if(model!=null){
           txtUser.setText(model.getMaNV());
            txtpass.setText(model.getMatKhau());
       }       
    }
   public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            loGin();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.console.swing.PanelBorder();
        txtUser = new com.console.swing.TextField();
        jLabel1 = new javax.swing.JLabel();
        txtpass = new com.console.swing.Passwordfield();
        chkLuu = new javax.swing.JCheckBox();
        btnLogin = new com.console.swing.Button();
        lblThoat = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pictureBox1 = new com.console.swing.PictureBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUser.setLabelText("Tài Khoản");
        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });
        panelBorder1.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 250, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(46, 170, 223));
        jLabel1.setText("ĐĂNG NHẬP");
        panelBorder1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 250, 70));

        txtpass.setLabelText("Mật Khẩu");
        txtpass.setShowAndHide(true);
        txtpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpassActionPerformed(evt);
            }
        });
        txtpass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpassKeyReleased(evt);
            }
        });
        panelBorder1.add(txtpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 250, -1));

        chkLuu.setForeground(new java.awt.Color(109, 109, 109));
        chkLuu.setText("Bạn muốn lưu thông tin?");
        chkLuu.setOpaque(false);
        chkLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLuuActionPerformed(evt);
            }
        });
        panelBorder1.add(chkLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 195, -1));

        btnLogin.setBackground(new java.awt.Color(46, 170, 223));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("LOGIN");
        btnLogin.setAutoscrolls(true);
        btnLogin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        panelBorder1.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 250, -1));

        lblThoat.setForeground(new java.awt.Color(109, 109, 109));
        lblThoat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThoat.setText("Thoát");
        lblThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThoatMouseClicked(evt);
            }
        });
        panelBorder1.add(lblThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 50, 30));

        getContentPane().add(panelBorder1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 560));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/img/nen2jpg.jpg"))); // NOI18N
        jPanel1.add(pictureBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 0, 600, 570));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 562));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpassActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        if(txtUser.getText().equals("")){
            txtUser.setHelperText("Không được bỏ trống!");return;
        }else{
            txtUser.setHelperText("");
        }
        if(txtpass.getText().equals("")){
            txtpass.setHelperText("Không được bỏ trống!");return;
        }else{
            txtpass.setHelperText("");
        }
        loGin();

    }//GEN-LAST:event_btnLoginActionPerformed

    private void lblThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThoatMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblThoatMouseClicked

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserActionPerformed

    private void chkLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLuuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkLuuActionPerformed

    private void txtpassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpassKeyReleased
        keyPressed(evt);
    }//GEN-LAST:event_txtpassKeyReleased

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
            java.util.logging.Logger.getLogger(LoGin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoGin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoGin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoGin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoGin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.console.swing.Button btnLogin;
    private javax.swing.JCheckBox chkLuu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblThoat;
    private com.console.swing.PanelBorder panelBorder1;
    private com.console.swing.PictureBox pictureBox1;
    private com.console.swing.TextField txtUser;
    private com.console.swing.Passwordfield txtpass;
    // End of variables declaration//GEN-END:variables
}
