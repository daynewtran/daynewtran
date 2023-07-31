/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.console.main;

import com.console.dao.ChamCongDAO;
import com.console.dao.NhanVienDAO;
import com.console.entity.ChamCong;
import com.console.entity.DonHang;
import com.console.entity.NhanVien;
import com.console.helper.DateHelper;
import com.console.helper.DialogHelper;
import com.console.helper.XImage;
import com.console.swing.ScrollBar;
import java.awt.Image;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class PanelChamCong extends javax.swing.JPanel {

    DecimalFormat f = new DecimalFormat("###,###,###");
    ChamCongDAO ccdao = new ChamCongDAO();
    NhanVienDAO nvdao = new NhanVienDAO();
    int index = 0;
    public PanelChamCong() {
        initComponents();
        txtTongLuong.setEditable(false);
        spGhiChu.setVerticalScrollBar(new ScrollBar());
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable1.setVerticalScrollBar(new ScrollBar());
        txtTongLuong.setText("0");
        txtThang.setEnabled(false);
        load();
        load2();
        fillComboBox();
    }
    boolean check(){
        if(Double.parseDouble(txtSoGioLam.getText())<0){
                txtSoGioLam.setHelperText("Bạn phải nhập số dương");
                return false;
            }
        if(txtSoGioLam.getText().equals("")){
            txtSoGioLam.setHelperText("Không được để trống!");
            return false;
        }
        try {
            Double.parseDouble(txtSoGioLam.getText());
        } catch (Exception e) {
            txtSoGioLam.setHelperText("Không được nhập chữ, ký tự đặc biệt và số âm!");
            return false;
        }
        txtSoGioLam.setHelperText("");
        
        if(Double.parseDouble(txtLuongTheoH.getText())<0){
                txtLuongTheoH.setHelperText("Bạn phải nhập số dương");
                return false;
        }
        if(txtLuongTheoH.getText().equals("") ||  txtLuongTheoH.getText().equals("0")){
            txtLuongTheoH.setHelperText("Không được để trống!");
            return false;
        }
        
        try {
            Double.parseDouble(txtLuongTheoH.getText());
        } catch (Exception e) {
            txtLuongTheoH.setHelperText("Không được nhập chữ hoặc ký tự đặc biệt!");
            return false;
        }
        txtLuongTheoH.setHelperText("");
        if(txtLuongThuong.getText().equals("")){
            txtLuongThuong.setHelperText("Không được để trống!");
            return false;
        }
        txtLuongTheoH.setHelperText("");
        
        try {
            Double.parseDouble(txtLuongThuong.getText());
        } catch (Exception e) {
            txtLuongThuong.setHelperText("Không được nhập chữ hoặc ký tự đặc biệt!");
            return false;
        }
        if(Double.parseDouble(txtLuongThuong.getText())<0){
                txtLuongThuong.setHelperText("Bạn phải nhập số dương");
                return false;
            }
        txtLuongThuong.setHelperText("");
        
        if(Double.parseDouble(txtLuongTru.getText())<0){
                txtLuongTru.setHelperText("Bạn phải nhập số dương");
                return false;
        }
        if(txtLuongTru.getText().equals("")){
            txtLuongTru.setHelperText("Không được để trống!");
            return false;
        }
        try {
            Double.parseDouble(txtLuongTru.getText());
        } catch (Exception e) {
            txtLuongTru.setHelperText("Không được nhập chữ hoặc ký tự đặc biệt!");
            return false;
        }
        txtLuongTru.setHelperText("");
        
        return true;
    }
    void load2(){
        DefaultTableModel model = (DefaultTableModel) tblChamCong2.getModel();
        model.setRowCount(0);
        
        try {
            List<ChamCong> list = ccdao.findByKeyword(txtTimKiem.getText());
            for (ChamCong cc : list) {
                DecimalFormat f = new DecimalFormat("###,###,###");
                String x= cc.getMaNv();
                Object[] row = {
                    cc.getMaNv(),
                    nvdao.findByID(x),
                    cc.getSoGioLam(),
                    f.format(cc.getLuong()).replace(',', '.'),                   
                    f.format(cc.getLuongThuong()).replace(',', '.'),
                    f.format(cc.getLuongPhat()).replace(',', '.'),
                    f.format(cc.getTongLuong()).replace(',', '.'),
                    cc.getThang(),
                    cc.getGhiChu()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, String.valueOf(e));
        }
    }
    void load() {
        DefaultTableModel model = (DefaultTableModel) tblChamCong.getModel();
        model.setRowCount(0);
        try {
            List<ChamCong> list = ccdao.select();
            for (ChamCong cc : list) {
                DecimalFormat f = new DecimalFormat("###,###,###");
              
            
            
            
                String x= cc.getMaNv();
                Object[] row = {
                    cc.getMaNv(),
                    nvdao.findByID(x),
                    cc.getSoGioLam(),
                    f.format(cc.getLuong()).replace(',', '.'),                   
                    f.format(cc.getLuongThuong()).replace(',', '.'),
                    f.format(cc.getLuongPhat()).replace(',', '.'),
                    f.format(cc.getTongLuong()).replace(',', '.'),
                    cc.getThang(),
                    cc.getGhiChu()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, String.valueOf(e));
        }
    }
    void edit() {
        try {
            String maNV = (String) tblChamCong2.getValueAt(this.index, 0);
            ChamCong model = ccdao.findById(maNV);
            if(model != null){
                this.setModel(model);
//                this.setStatus(false);
            }
        } 
        catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    ChamCong getModel(){
        
        ChamCong model = new ChamCong();
        
        model.setMaNv(String.valueOf(cboMaNhanVien.getSelectedItem()));
        model.setSoGioLam(Integer.parseInt(txtSoGioLam.getText()));
        model.setLuong(Double.parseDouble(txtLuongTheoH.getText()));
        model.setLuongThuong(Double.parseDouble(txtLuongThuong.getText()));
        model.setLuongPhat(Double.parseDouble(txtLuongTru.getText()));
        model.setTongLuong(Double.parseDouble(txtTongLuong.getText().replace(" ", "")));
        model.setThang(txtThang.getText());
        model.setGhiChu(txtGhiChu.getText());
        return model;
        
        
    }
    void tinhLuongNhan(){
        if(!txtLuongCB.equals("")&&!txtLuongThuong.equals("")&&!txtLuongTru.equals("")){
            Double luongCB = Double.parseDouble(txtLuongCB.getText().replace(" ", ""));
            Double luongThuong = Double.parseDouble(txtLuongThuong.getText());
            Double luongTru = Double.parseDouble(txtLuongTru.getText());
            txtTongLuong.setText(String.valueOf(luongCB+luongThuong-luongTru));
        }
    }
    void insert(){
        
        ChamCong model = getModel();
        ccdao.insert(model);
        load();
        load2();
        
    }
    void setModel(ChamCong model){
        try{
            cboMaNhanVien.setSelectedItem(model.getMaNv());
            txtSoGioLam.setText(String.valueOf(model.getSoGioLam()));
            txtLuongTheoH.setText(String.valueOf(model.getLuong()));
            txtLuongThuong.setText(String.valueOf(model.getLuongThuong()));
            String s1 = f.format(Double.parseDouble(txtLuongThuong.getText()));
            String s2 = s1.replace(',', ' ');
            //thay thế tất cả ký tự ',' thành ' '    
             txtLuongThuong.setHelperText(s2);
            txtLuongTru.setText(String.valueOf(model.getLuongPhat()));
             s1 = f.format(Double.parseDouble(txtLuongTru.getText()));
             s2 = s1.replace(',', ' ');
            //thay thế tất cả ký tự ',' thành ' '    
             txtLuongTru.setHelperText(s2);
            txtTongLuong.setText(String.valueOf(model.getTongLuong()));
            
            s1 = f.format(Double.parseDouble(txtTongLuong.getText()));
             s2 = s1.replace(',', ' ');
            //thay thế tất cả ký tự ',' thành ' '    
             txtTongLuong.setText(s2);
             
//            cboThang.setSelectedItem("Tháng "+model.getThang().substring(0, 2));
            txtThang.setText(model.getThang());
            txtGhiChu.setText(model.getGhiChu());
        }catch(Exception e){
            DialogHelper.alert(this, String.valueOf(e));
        }
    }
    
    void fillComboBox(){
//        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaNhanVien.getModel();
//        model.removeAllElements();
        try {
            List<NhanVien> list = nvdao.select();
            for(NhanVien nv : list){
//                String x= nv.getMaNV();
                cboMaNhanVien.addItem(nv.getMaNV());
//                 nvdao.findByID(x);
            }
        } 
        catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
        String thoiGian = DateHelper.toString(DateHelper.now());
        txtThang.setText((thoiGian.substring(3,5).trim())+"-"+(thoiGian.substring(6,10).trim()));
//        System.out.println("Tháng "+thoiGian.substring(0, thoiGian.length()-8).trim());
    }
    void tinhLuongCB(){
        if(!txtSoGioLam.equals("")&&!txtLuongTheoH.equals("")){
            int soGioLam = Integer.parseInt(txtSoGioLam.getText());
        double luongTheoH = Double.parseDouble(txtLuongTheoH.getText());        
        double luongCB = soGioLam*luongTheoH;        
            String s1 = f.format(luongTheoH);
            String s2 = s1.replace(',', ' ');
            //thay thế tất cả ký tự 't' thành ' '   
            txtLuongTheoH.setHelperText(s2);
            String s3 = f.format(luongCB);
            String s4 = s3.replace(',', ' ');
            txtLuongCB.setText(s4);
        }
        
    }
    void update(){
        ChamCong model = getModel();
        try {
            ccdao.update(model);
            this.load();
            DialogHelper.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            System.out.println(e);
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    void clear(){
        cboMaNhanVien.setSelectedIndex(0);
        txtSoGioLam.setText("0");
        txtLuongTheoH.setText("0");
        txtLuongThuong.setText("0");
        txtLuongTru.setText("0");
        txtTongLuong.setText("0");
//        cboThang.setSelectedIndex(-1);
        String thoiGian = DateHelper.toString(DateHelper.now());
        txtThang.setText((thoiGian.substring(3,5).trim())+"-"+(thoiGian.substring(6,10).trim()));
        txtGhiChu.setText("ghi chú");
        txtSoGioLam.setHelperText("");
        txtLuongTheoH.setHelperText("");
        txtLuongThuong.setHelperText("");
        txtLuongTru.setHelperText("");
        load();
    }
    
    void tinhLuong(){
        int a = Integer.parseInt(txtSoGioLam.getText())*100000;
        double luongne = a + Double.parseDouble(txtLuongThuong.getText()) - Double.parseDouble(txtThang.getText());
        txtTongLuong.setText(String.valueOf(luongne));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        materialTabbed1 = new com.console.swing.MaterialTabbed();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        tblChamCong2 = new com.console.swing.Table();
        cboMaNhanVien = new com.console.swing.Combobox();
        txtLuongThuong = new com.console.swing.TextField();
        txtTimKiem = new com.console.swing.TextField2();
        spGhiChu = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        txtThang = new com.console.swing.TextField();
        txtTongLuong = new com.console.swing.TextField();
        btnCapNhat = new com.console.swing.Button();
        btnLamMoi = new com.console.swing.Button();
        txtSoGioLam = new com.console.swing.TextField();
        txtLuongTheoH = new com.console.swing.TextField();
        txtLuongCB = new com.console.swing.TextField();
        button1 = new com.console.swing.Button();
        btnThem = new com.console.swing.Button();
        txtLuongTru = new com.console.swing.TextField();
        jPanel4 = new javax.swing.JPanel();
        spTable1 = new javax.swing.JScrollPane();
        tblChamCong = new com.console.swing.Table();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblChamCong2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Số giờ làm", "Lương theo giờ", "Lương thưởng", "Lương trừ", "Lương nhận", "Tháng", "Ghi chú"
            }
        ));
        tblChamCong2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChamCong2MouseClicked(evt);
            }
        });
        spTable.setViewportView(tblChamCong2);

        jPanel2.add(spTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 1020, 220));

        cboMaNhanVien.setLabeText("Mã nhân viên");
        jPanel2.add(cboMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 170, 50));

        txtLuongThuong.setText("0");
        txtLuongThuong.setLabelText("Lương thưởng");
        txtLuongThuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLuongThuongActionPerformed(evt);
            }
        });
        txtLuongThuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLuongThuongKeyReleased(evt);
            }
        });
        jPanel2.add(txtLuongThuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 150, -1));

        txtTimKiem.setLabelText("Tìm kiếm");
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        jPanel2.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, 210, -1));

        txtGhiChu.setColumns(20);
        txtGhiChu.setForeground(new java.awt.Color(153, 153, 153));
        txtGhiChu.setRows(5);
        txtGhiChu.setText("Ghi chú");
        spGhiChu.setViewportView(txtGhiChu);

        jPanel2.add(spGhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 460, 70));

        txtThang.setToolTipText("");
        txtThang.setLabelText("Tháng");
        txtThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThangActionPerformed(evt);
            }
        });
        txtThang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtThangKeyReleased(evt);
            }
        });
        jPanel2.add(txtThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 150, -1));

        txtTongLuong.setEnabled(false);
        txtTongLuong.setLabelText("Lương nhận");
        txtTongLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongLuongActionPerformed(evt);
            }
        });
        txtTongLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTongLuongKeyReleased(evt);
            }
        });
        jPanel2.add(txtTongLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 310, -1));

        btnCapNhat.setBackground(new java.awt.Color(78, 143, 227));
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/console/icon/Upload.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        jPanel2.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 190, 90, 40));

        btnLamMoi.setBackground(new java.awt.Color(78, 143, 227));
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/console/icon/Refresh.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel2.add(btnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 190, 90, 40));

        txtSoGioLam.setText("0");
        txtSoGioLam.setLabelText("Số giờ làm");
        txtSoGioLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoGioLamActionPerformed(evt);
            }
        });
        txtSoGioLam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoGioLamKeyReleased(evt);
            }
        });
        jPanel2.add(txtSoGioLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 150, -1));

        txtLuongTheoH.setText("0");
        txtLuongTheoH.setLabelText("Lương theo giờ");
        txtLuongTheoH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLuongTheoHActionPerformed(evt);
            }
        });
        txtLuongTheoH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLuongTheoHKeyReleased(evt);
            }
        });
        jPanel2.add(txtLuongTheoH, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 170, -1));

        txtLuongCB.setBackground(new java.awt.Color(242, 242, 242));
        txtLuongCB.setDisabledTextColor(new java.awt.Color(142, 142, 142));
        txtLuongCB.setEnabled(false);
        txtLuongCB.setLabelText("Lương cơ bản");
        txtLuongCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLuongCBActionPerformed(evt);
            }
        });
        jPanel2.add(txtLuongCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 240, -1));

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/console/icon/Refresh.png"))); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel2.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        btnThem.setBackground(new java.awt.Color(78, 143, 227));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/console/icon/Upload.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel2.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 190, 90, 40));

        txtLuongTru.setText("0");
        txtLuongTru.setLabelText("Lương trừ");
        txtLuongTru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLuongTruActionPerformed(evt);
            }
        });
        txtLuongTru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLuongTruKeyReleased(evt);
            }
        });
        jPanel2.add(txtLuongTru, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 150, -1));

        jPanel3.add(jPanel2);

        materialTabbed1.addTab("Cập nhật", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblChamCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Số giờ làm", "Lương theo giờ", "Lương thưởng", "Lương trừ", "Lương nhận", "Tháng", "Ghi chú"
            }
        ));
        tblChamCong.setEnabled(false);
        tblChamCong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChamCongMouseClicked(evt);
            }
        });
        spTable1.setViewportView(tblChamCong);

        jPanel4.add(spTable1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1020, 470));

        materialTabbed1.addTab("Danh Sách", jPanel4);

        add(materialTabbed1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 560));
    }// </editor-fold>//GEN-END:initComponents

    private void txtThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThangActionPerformed

    private void txtLuongThuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLuongThuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLuongThuongActionPerformed

    private void txtTongLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongLuongActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        try {
            if(check() == true){
                
        
        update();   
        load();
        load2();
//        DialogHelper.alert(this, "Thêm lương tháng mới cho nhân viên "+cboMaNhanVien.getSelectedItem()+" thành công!");
            
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Vui lòng nhập đủ số liệu!");
                    
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clear();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblChamCong2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChamCong2MouseClicked
        if(evt.getClickCount() == 1){
            this.index = tblChamCong2.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
            }
            tinhLuongCB();
        }
    }//GEN-LAST:event_tblChamCong2MouseClicked

    private void txtSoGioLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoGioLamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoGioLamActionPerformed

    private void txtTongLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTongLuongKeyReleased
        
    }//GEN-LAST:event_txtTongLuongKeyReleased

    private void txtLuongTheoHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLuongTheoHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLuongTheoHActionPerformed

    private void txtLuongCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLuongCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLuongCBActionPerformed

    private void txtLuongTheoHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLuongTheoHKeyReleased
        try {
           if(txtSoGioLam != null  && txtLuongTheoH != null){
           tinhLuongCB(); 
        }
        } catch (Exception e) {
            
        }
        
    }//GEN-LAST:event_txtLuongTheoHKeyReleased

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        fillComboBox();
    }//GEN-LAST:event_button1ActionPerformed

    private void txtSoGioLamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoGioLamKeyReleased
        try {
        if(txtSoGioLam != null  && txtLuongTheoH != null){
           tinhLuongCB(); 
        }
        } catch (Exception e) {
            
        }
        
    }//GEN-LAST:event_txtSoGioLamKeyReleased

    private void txtLuongThuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLuongThuongKeyReleased
        try {
            if(!txtLuongThuong.getText().equals("")){
            String s1 = f.format(Double.parseDouble(txtLuongThuong.getText()));
            String s2 = s1.replace(',', ' ');
            txtLuongThuong.setHelperText(s2);
        }else{
            txtLuongThuong.setHelperText("0");
        }           
        tinhLuongNhan();
        } catch (Exception e) {
            
        }
        
    }//GEN-LAST:event_txtLuongThuongKeyReleased

    private void txtThangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtThangKeyReleased
        try {
            if(!txtThang.getText().equals("")){
            String s1 = f.format(Double.parseDouble(txtThang.getText()));
            String s2 = s1.replace(',', ' ');
            txtThang.setHelperText(s2);
        }else{
            txtThang.setHelperText("0");
        }        
        tinhLuongNhan();
        } catch (Exception e) {
            
        }
        
    }//GEN-LAST:event_txtThangKeyReleased

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        load2();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void tblChamCongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChamCongMouseClicked
        DialogHelper.alert(this, "Bảng chỉ xem");
    }//GEN-LAST:event_tblChamCongMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        ChamCong cc = ccdao.select3(String.valueOf(cboMaNhanVien.getSelectedItem()));
        if(cc!=null){
            DialogHelper.alert(this, "Nhân viên đã có trong danh sách chấm lương tháng này");
            return;
        }
        try {
            if(check() == true){
                
        
        insert();   
        load();
        load2();
        DialogHelper.alert(this, "Thêm lương tháng mới cho nhân viên "+cboMaNhanVien.getSelectedItem()+" thành công!");
            
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Vui lòng nhập đủ số liệu!");
                    
        }
        
        
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtLuongTruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLuongTruActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLuongTruActionPerformed

    private void txtLuongTruKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLuongTruKeyReleased
        try {
            if(!txtLuongTru.getText().equals("")){
            String s1 = f.format(Double.parseDouble(txtLuongTru.getText()));
            String s2 = s1.replace(',', ' ');
            txtLuongTru.setHelperText(s2);
        }else{
            txtLuongTru.setHelperText("0");
        }           
        tinhLuongNhan();
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_txtLuongTruKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.console.swing.Button btnCapNhat;
    private com.console.swing.Button btnLamMoi;
    private com.console.swing.Button btnThem;
    private com.console.swing.Button button1;
    private com.console.swing.Combobox cboMaNhanVien;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private com.console.swing.MaterialTabbed materialTabbed1;
    private javax.swing.JScrollPane spGhiChu;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JScrollPane spTable1;
    private com.console.swing.Table tblChamCong;
    private com.console.swing.Table tblChamCong2;
    private javax.swing.JTextArea txtGhiChu;
    private com.console.swing.TextField txtLuongCB;
    private com.console.swing.TextField txtLuongTheoH;
    private com.console.swing.TextField txtLuongThuong;
    private com.console.swing.TextField txtLuongTru;
    private com.console.swing.TextField txtSoGioLam;
    private com.console.swing.TextField txtThang;
    private com.console.swing.TextField2 txtTimKiem;
    private com.console.swing.TextField txtTongLuong;
    // End of variables declaration//GEN-END:variables
}
