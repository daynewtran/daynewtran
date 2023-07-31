
package com.console.main;

import com.console.dao.CapBacDAO;
import com.console.dao.KhachHangDAO;
import com.console.entity.CapBac;
import com.console.entity.KhachHang;
import com.console.entity.NhanVien;
import com.console.helper.DialogHelper;
import com.console.helper.JdbcHelper;
import com.console.swing.ScrollBar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class PanelKhachHang extends javax.swing.JPanel {

    KhachHangDAO dao = new KhachHangDAO();
    CapBacDAO dao2 = new CapBacDAO();
    int index=0;
    boolean check = false;
    
    public PanelKhachHang() {
        initComponents();
        spTable.setVerticalScrollBar(new ScrollBar());
        spGhiChu.setVerticalScrollBar(new ScrollBar());
        lblGioiTinh.setVisible(false);
        load();
        fillComboBoxCapBac();
        
    }
    void fillComboBoxCapBac() {
//        DefaultComboBoxModel model = (DefaultComboBoxModel) cboCapBac.getModel();
//        model.removeAllElements();
        
            List<CapBac> list = dao2.select();
            for (CapBac cd : list) {
                cboCapBac.addItem(cd.getSoCapBac());               
            }
        
    }
    void check(){
        check = false ;
        if(txtSDT.getText().equals("")){
            txtSDT.setHelperText("Không được để trống SDT!");
            return;
        }       
        
            String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        boolean kt = txtSDT.getText().matches(reg);
        if(!kt){
            txtSDT.setHelperText("Định dạng số điện thoại không chính xác");
            return;
        }else{
            txtSDT.setHelperText("");
        }
        
        if(txtHoTen.getText().equals("")){
            txtHoTen.setHelperText("Không được bỏ trống  tên khách hàng");
            return;
        }
            txtHoTen.setHelperText("");
        
        if(txtEmail.getText().equals("")){
            txtEmail.setHelperText("Không được để trống!");
            return;
        }       
        
        
        String email = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        boolean kt1 = txtEmail.getText().matches(email);
        if(!kt1){
            txtEmail.setHelperText("Định dạng email không chính xác");
            return;
        }else{
            txtEmail.setHelperText("");
        }
        if(txtDiaChi.getText().equals("")){
            txtDiaChi.setHelperText("Không được để trống!");
            return;
        }else{
            txtDiaChi.setHelperText("");
        }
        
         if(!rdoNam.isSelected()&&!rdoNu.isSelected()){
            lblGioiTinh.setVisible(true);
            return;
        }
         lblGioiTinh.setVisible(false);
         check=true;
    }
//    void fillComboBoxCapBac(String capBac) {
//        DefaultComboBoxModel model = (DefaultComboBoxModel) cboCapBac.getModel();
//        model.removeAllElements();
//        try {
//            List<CapBac> list = dao2.select(capBac);
//            for (CapBac cd : list) {
//                cboCapBac.setSelectedItem(cd);
//            }
//        } catch (Exception e) {
//            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
//            System.out.println(e);
//
//        }
//    }
    void load() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        try {
            List<KhachHang> list = dao.findByKeyword(txtTimKiem.getText());
            for (KhachHang kh : list) {
                Object[] row = {
                    kh.getTenKH(),
                    kh.getSDT(),
                    kh.isGioiTinh() ? "Nam":"Nữ",
                    kh.getEmail(),
                    kh.getDiaChi(),
                    kh.getSoCap(),
                    kh.getGhiChu()    
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, String.valueOf(e));
        }
    }
    void edit() {
        try {
            String SoDT = (String) tblKhachHang.getValueAt(this.index, 1);
            KhachHang model = dao.findById(SoDT);
            if (model != null) {
                this.setModel(model);
//                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    void update(){
        KhachHang model = getModel();
        try {
//            if(!txtSDT.getText().equals(model.getSDT())){
//                DialogHelper.alert(this, "SDT không thể sửa đổi");
//                txtSDT.setText(model.getSDT());
//            }
            dao.update(model);
            this.load();
            DialogHelper.alert(this, "Đã cập nhật");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    KhachHang getModel() {

        KhachHang model = new KhachHang();

        model.setTenKH(txtHoTen.getText());
        model.setSDT(txtSDT.getText());
        model.setEmail(txtEmail.getText());
        model.setDiaChi(txtDiaChi.getText());
        model.setSoCap(1);
        model.setGioiTinh(rdoNam.isSelected());
        model.setGhiChu(txtGhiChu.getText());
        
        return model;

    }
    void setModel(KhachHang model) {
        
        try {
        txtSDT.setText(model.getSDT());
        txtHoTen.setText(model.getTenKH());
        txtDiaChi.setText(model.getDiaChi());
        txtEmail.setText(model.getEmail());
        txtGhiChu.setText(model.getGhiChu());
        rdoNam.setSelected(model.isGioiTinh());
        rdoNu.setSelected(!model.isGioiTinh());
        //cboCapBac.setToolTipText(String.valueOf(model.getSoCap()));
        cboCapBac.setSelectedItem(model.getSoCap());
        } catch (Exception e) {
            DialogHelper.alert(this, String.valueOf(e));
        }
        

    }
    void insert(){
        KhachHang model = dao.findById(txtSDT.getText());
        if(model!=null){
            txtSDT.setHelperText( "SDT đã được sử dụng");
            return;
        }
        txtSDT.setHelperText( "");
        check();
        if(check==true){
            model = getModel();
        dao.insert(model);
        this.load();
        this.clear();
        DialogHelper.alert(this, "Thêm mới thành công");
        }
        
    }
    void clear() {
        txtSDT.setText("");
        txtDiaChi.setText("");
        txtHoTen.setText("");
        txtEmail.setText("");
        txtGhiChu.setText("");
        //txtTimKiem.setText("");
        buttonGroup1.clearSelection();
        cboCapBac.setSelectedIndex(0);
        

    }
    void delete() {
        String SDT = txtSDT.getText();
        if (SDT.equals("")) {
            DialogHelper.alert(this, "Bạn phải nhập số điện thoại khách hàng");
            return;
        } else if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa nhân viên này,?")) {
            try {
                dao.delete(SDT);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                System.out.println(e);
                DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        tblKhachHang = new com.console.swing.Table();
        txtHoTen = new com.console.swing.TextField();
        txtSDT = new com.console.swing.TextField();
        txtEmail = new com.console.swing.TextField();
        rdoNu = new javax.swing.JRadioButton();
        rdoNam = new javax.swing.JRadioButton();
        spGhiChu = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        button1 = new com.console.swing.Button();
        button2 = new com.console.swing.Button();
        button3 = new com.console.swing.Button();
        button4 = new com.console.swing.Button();
        txtDiaChi = new com.console.swing.TextField();
        txtTimKiem = new com.console.swing.TextField2();
        lblGioiTinh = new javax.swing.JLabel();
        cboCapBac = new com.console.swing.Combobox();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên khách hàng", "Số điện thoại", "Giới tính", "Email", "Địa chỉ", "Cấp bậc", "Ghi chú"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        spTable.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(0).setMinWidth(130);
            tblKhachHang.getColumnModel().getColumn(0).setMaxWidth(250);
            tblKhachHang.getColumnModel().getColumn(1).setMinWidth(100);
            tblKhachHang.getColumnModel().getColumn(1).setPreferredWidth(10);
            tblKhachHang.getColumnModel().getColumn(1).setMaxWidth(200);
            tblKhachHang.getColumnModel().getColumn(2).setMinWidth(70);
            tblKhachHang.getColumnModel().getColumn(2).setPreferredWidth(2);
            tblKhachHang.getColumnModel().getColumn(2).setMaxWidth(100);
            tblKhachHang.getColumnModel().getColumn(5).setMinWidth(60);
            tblKhachHang.getColumnModel().getColumn(5).setPreferredWidth(2);
            tblKhachHang.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        jPanel1.add(spTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 1010, 200));

        txtHoTen.setLabelText("Tên khách hàng");
        jPanel1.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 330, -1));

        txtSDT.setLabelText("Số điện thoại");
        jPanel1.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 270, -1));

        txtEmail.setLabelText("Email");
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 330, -1));

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");
        jPanel1.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 260, -1, -1));

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");
        jPanel1.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 260, -1, -1));

        txtGhiChu.setColumns(20);
        txtGhiChu.setForeground(new java.awt.Color(153, 153, 153));
        txtGhiChu.setRows(5);
        txtGhiChu.setText("Ghi chú");
        txtGhiChu.setCaretColor(new java.awt.Color(153, 153, 153));
        spGhiChu.setViewportView(txtGhiChu);

        jPanel1.add(spGhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 330, 90));

        button1.setBackground(new java.awt.Color(46, 170, 223));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Sửa");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel1.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 220, 60, -1));

        button2.setBackground(new java.awt.Color(46, 170, 223));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Xóa");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        jPanel1.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 130, 60, -1));

        button3.setBackground(new java.awt.Color(46, 170, 223));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Làm mới");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        jPanel1.add(button3, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 220, 60, -1));

        button4.setBackground(new java.awt.Color(46, 170, 223));
        button4.setForeground(new java.awt.Color(255, 255, 255));
        button4.setText("Thêm");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });
        jPanel1.add(button4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 130, 60, -1));

        txtDiaChi.setLabelText("Địa chỉ");
        jPanel1.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 270, -1));

        txtTimKiem.setLabelText("Tìm kiếm theo tên");
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        jPanel1.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 270, -1));

        lblGioiTinh.setForeground(new java.awt.Color(255, 102, 102));
        lblGioiTinh.setText("Vui lòng chọn giới tính");
        jPanel1.add(lblGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, 130, 20));

        cboCapBac.setLabeText("Cấp bậc");
        jPanel1.add(cboCapBac, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, 270, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        if(evt.getClickCount() == 1){
            this.index = tblKhachHang.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
                
            }
        }
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        update();
    }//GEN-LAST:event_button1ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        try {
            insert();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }//GEN-LAST:event_button4ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        delete();
    }//GEN-LAST:event_button2ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        clear();
        load();
    }//GEN-LAST:event_button3ActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        load();
    }//GEN-LAST:event_txtTimKiemKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.console.swing.Button button1;
    private com.console.swing.Button button2;
    private com.console.swing.Button button3;
    private com.console.swing.Button button4;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.console.swing.Combobox cboCapBac;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JScrollPane spGhiChu;
    private javax.swing.JScrollPane spTable;
    private com.console.swing.Table tblKhachHang;
    private com.console.swing.TextField txtDiaChi;
    private com.console.swing.TextField txtEmail;
    private javax.swing.JTextArea txtGhiChu;
    private com.console.swing.TextField txtHoTen;
    private com.console.swing.TextField txtSDT;
    private com.console.swing.TextField2 txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
