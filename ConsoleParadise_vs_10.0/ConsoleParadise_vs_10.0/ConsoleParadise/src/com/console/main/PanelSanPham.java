/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.console.main;

import com.console.dao.LoaiSanPhamDAO;
import com.console.dao.SanPhamDAO;
import com.console.entity.LoaiSanPham;
import com.console.entity.NhanVien;
import com.console.entity.SanPham;
import com.console.helper.Auth;
import com.console.helper.DialogHelper;
import com.console.helper.XImage;
import com.console.swing.ScrollBar;
import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

public class PanelSanPham extends javax.swing.JPanel {

    SanPhamDAO dao = new SanPhamDAO();
    LoaiSanPhamDAO dao2 = new LoaiSanPhamDAO();
    int index =0;
    
    public PanelSanPham() {
        initComponents();
        spTable.setVerticalScrollBar(new ScrollBar());
        load();
        fillComboLoai();
        lblHinhAnh.setToolTipText(" ");
        
    }
        private boolean CKNull(){
        if(txtMaSP.getText().length()==0){
            txtMaSP.setHelperText("Không được để trống mã sản phẩm!");
            return false;
        }
        if(txtMaSP.getText().length()!=5){
            txtMaSP.setHelperText("Mã phải nhập đúng 5 kí tự!");
            return false;
        }
        txtMaSP.setHelperText("");
        if(txtTenSP.getText().length()==0){
            txtTenSP.setHelperText("Không được để trống tên sản phẩm!");
            return false;
        }
        
        
        else{
            txtTenSP.setHelperText("");
        }
        if(txtHang.getText().length()==0){
            txtHang.setHelperText("Không được để trống hãng sản xuất!");
            return false;
        }else{
            txtHang.setHelperText("");
        }
        
        if(txtGiaBan.getText().length()==0){
            txtGiaBan.setHelperText("Không được để trống giá bán!");
            return false;
        }
        try {
                Double.parseDouble(txtGiaBan.getText());
            } catch (Exception e) {
                txtGiaBan.setHelperText("Giá bán phải nhập số!");
                return false;
            }
            if(Double.parseDouble(txtGiaBan.getText())<0){
                txtGiaBan.setHelperText("Bạn phải nhập số dương");
                return false;
            }
        
            txtGiaBan.setHelperText("");
        
        if(txtSL.getText().length()==0){
            txtSL.setHelperText("Không được để trống số lượng!");
            return false;
        }
        
        else{
            txtSL.setHelperText("");
        }
        
            
            txtGiaBan.setHelperText("");
            try {
                Double.parseDouble(txtSL.getText());
            } catch (Exception e) {
                txtSL.setHelperText("Số lượng phải nhập số!");
                return false;
            }
            if(Double.parseDouble(txtSL.getText())<0){
                txtSL.setHelperText("Bạn phải nhập số dương");
                return false;
            }
            txtSL.setHelperText("");

        return true;
    }
    void insert(){
        SanPham model = dao.findById(txtMaSP.getText());
        if(model!=null){
            DialogHelper.alert(this, "Mã đã tồn tại");
            return;
        }
        model = getModel();
        dao.insert(model);
        this.load();
        this.clear();
        DialogHelper.alert(this, "Thêm mới thành công");
    }
    void fillComboLoai(){      
            List<LoaiSanPham> list = dao2.select();
            for(LoaiSanPham cd : list){
                 cboLoai.addItem(cd.getMaLoai());
            }        
    }
    void clear(){
        txtGhiChu.setText("");
        txtGiaBan.setText("");
        txtHang.setText("");
        txtMaSP.setText("");
        txtSL.setText("");
        txtTenSP.setText("");
        cboLoai.setSelectedIndex(-1);
        lblHinhAnh.setIcon(null);
    }
    void setModel(SanPham model) throws SQLException {
            txtMaSP.setText(model.getMaSP());
            txtTenSP.setText(model.getTenSP());
            txtGiaBan.setText(String.valueOf(model.getGiaBan()));
            txtSL.setText(String.valueOf(model.getSoLuong()));
            txtGhiChu.setText(model.getGhiChu());
            txtHang.setText(model.getHangSanXuat());
            cboLoai.setToolTipText(model.getMaLoai());
            cboLoai.setSelectedItem(model.getMaLoai());
            if(model.getImg().equals("")||model.getImg().equals(" ") ){
            lblHinhAnh.setIcon(XImage.readLogo("nenRong.jpg"));
//            ImageIcon hinhanh = new ImageIcon(new ImageIcon("logos//nenRong.jpg").
//            getImage().getScaledInstance(lblHinhAnh.getWidth(), 
//            lblHinhAnh.getHeight(), Image.SCALE_SMOOTH)); 
//                lblHinhAnh.setIcon(hinhanh);                
//                lblHinhAnh.setToolTipText(model.getImg());  
                return;
        }
            
                lblHinhAnh.setToolTipText(model.getImg());
            lblHinhAnh.setIcon(XImage.readLogo(model.getImg()));
            ImageIcon hinhanh = new ImageIcon(new ImageIcon("logos//"+model.getImg()).
            getImage().getScaledInstance(lblHinhAnh.getWidth(), 
            lblHinhAnh.getHeight(), Image.SCALE_SMOOTH)); 
                lblHinhAnh.setIcon(hinhanh);                
                lblHinhAnh.setToolTipText(model.getImg());
    }
    SanPham getModel() {
        SanPham model = new SanPham();
        model.setMaSP(txtMaSP.getText());
        model.setTenSP(txtTenSP.getText());
        model.setHangSanXuat(txtHang.getText());
        model.setMaLoai((String) cboLoai.getSelectedItem());
        model.setGiaBan(Double.parseDouble(txtGiaBan.getText()));//error
        model.setSoLuong(Integer.parseInt(txtSL.getText()));
        model.setGhiChu(txtGhiChu.getText());
        if(lblHinhAnh.getToolTipText().equals(" ")){
            model.setImg(" ");
        }
        model.setImg(lblHinhAnh.getToolTipText());
        return model;
    }
    void edit() {
        try {
            String maSP = (String) tblSanPham.getValueAt(this.index, 0);
            SanPham model = dao.findById(maSP);            
            if (model != null) {
                this.setModel(model);
//                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, String.valueOf(e));
        }
    }

    void load(){
        DefaultTableModel model =  (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();
            List<SanPham> list = dao.selectByKeyword(keyword);
           
            for(SanPham nv : list){
                 LoaiSanPham l = dao2.findByID(nv.getMaLoai());
                 DecimalFormat f = new DecimalFormat("###,###,###");
            String s1 = f.format(nv.getGiaBan());
            String s2 = s1.replace(',', '.');
            //thay thế tất cả ký tự ',' thành '.'   
            
                Object[] row ={
                    nv.getMaSP(),
                    nv.getTenSP(),
                    nv.getHangSanXuat(),
                    l.getTenLoai(),
                    s2,
                    nv.getSoLuong(),
                    nv.getGhiChu(),
                    nv.getImg(),
                };
                model.addRow(row);
            }
        } catch (Exception e) {
        }
    }
    void update(){
        SanPham model =getModel();
        dao.update(model);
        this.load();
        this.clear();
        
    }
    void selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){            
            File file = fileChooser.getSelectedFile();                                  
                // Hiển thị hình lên form 
                
                  XImage.saveLogo(file);
                 
              ImageIcon icon =XImage.readLogo(file.getName());
              lblHinhAnh.setIcon(icon);                
                lblHinhAnh.setToolTipText(file.getName());
                
                File path = new File("C:\\logos", file.getName());
        
                ImageIcon hinhanh = new ImageIcon(new ImageIcon(path.getAbsolutePath()).
            getImage().getScaledInstance(lblHinhAnh.getWidth(), 
            lblHinhAnh.getHeight(), Image.SCALE_SMOOTH)); 
                lblHinhAnh.setIcon(hinhanh);                
                lblHinhAnh.setToolTipText(file.getName());      
        }
    }
  
    void delete(){
        if(txtMaSP.getText().equals("")){
            txtMaSP.setHelperText("Vui lòng nhập mã sản phẩm cần xóa");
            return;
        }else{
            txtMaSP.setHelperText("");
        }
        if(DialogHelper.confirm(this, "Bạn muốn xóa sản phẩm này?")){
             try {            
                    dao.delete(txtMaSP.getText());
                    this.load();
                    this.clear();
                    DialogHelper.alert(this, "Xóa thành công!");
                    tabs.setSelectedIndex(1);
                } catch (Exception e) {
                    dao.update2(txtMaSP.getText());
                    DialogHelper.alert(this, "Sản phẩm được chuyển về trạng thái 'Ngưng kinh doanh'!");
                }
        }
       
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new com.console.swing.MaterialTabbed();
        jPanel1 = new javax.swing.JPanel();
        txtMaSP = new com.console.swing.TextField();
        txtTenSP = new com.console.swing.TextField();
        txtGiaBan = new com.console.swing.TextField();
        txtSL = new com.console.swing.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnThem = new com.console.swing.Button();
        btnSua = new com.console.swing.Button();
        btnXoa = new com.console.swing.Button();
        btnLamMoi = new com.console.swing.Button();
        cboLoai = new com.console.swing.Combobox();
        txtHang = new com.console.swing.TextField();
        lblHinhAnh = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        tblSanPham = new com.console.swing.Table();
        txtTimKiem = new com.console.swing.TextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtMaSP.setLabelText("Mã sản phẩm");

        txtTenSP.setLabelText("Tên sản phẩm");

        txtGiaBan.setLabelText("Giá bán(VND)");

        txtSL.setLabelText("Số lượng");

        txtGhiChu.setColumns(20);
        txtGhiChu.setForeground(new java.awt.Color(109, 109, 109));
        txtGhiChu.setRows(5);
        txtGhiChu.setText("Ghi chú");
        txtGhiChu.setCaretColor(new java.awt.Color(153, 153, 153));
        jScrollPane1.setViewportView(txtGhiChu);

        btnThem.setBackground(new java.awt.Color(46, 170, 223));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(46, 170, 223));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(46, 170, 223));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(46, 170, 223));
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        cboLoai.setLabeText("Loại ");
        cboLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiActionPerformed(evt);
            }
        });

        txtHang.setLabelText("Hãng");

        lblHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnh.setToolTipText("");
        lblHinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHinhAnhMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHang, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(723, Short.MAX_VALUE)
                    .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(36, 36, 36)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addComponent(txtHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(121, 121, 121)
                    .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(327, Short.MAX_VALUE)))
        );

        tabs.addTab("Cập nhật", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "   Mã SP", "Tên sản phẩm", "Hãng", "Tên loại", "Giá bán", "Số lượng", "Ghi chú", "Ảnh"
            }
        ));
        tblSanPham.setToolTipText("");
        tblSanPham.setEnabled(false);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        spTable.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(0).setMinWidth(70);
            tblSanPham.getColumnModel().getColumn(0).setMaxWidth(150);
            tblSanPham.getColumnModel().getColumn(2).setMinWidth(85);
            tblSanPham.getColumnModel().getColumn(2).setMaxWidth(100);
            tblSanPham.getColumnModel().getColumn(3).setMinWidth(60);
            tblSanPham.getColumnModel().getColumn(3).setMaxWidth(150);
            tblSanPham.getColumnModel().getColumn(4).setMinWidth(80);
            tblSanPham.getColumnModel().getColumn(4).setMaxWidth(150);
            tblSanPham.getColumnModel().getColumn(5).setMinWidth(30);
            tblSanPham.getColumnModel().getColumn(5).setMaxWidth(70);
            tblSanPham.getColumnModel().getColumn(7).setMinWidth(150);
            tblSanPham.getColumnModel().getColumn(7).setMaxWidth(200);
        }

        jPanel2.add(spTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 52, 1005, 410));

        txtTimKiem.setLabelText("Tìm kiếm theo tên");
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        jPanel2.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 510, -1));

        tabs.addTab("Danh sách", jPanel2);

        add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 530));
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        SanPham sp = dao.findById(txtMaSP.getText());
        if(sp!=null){
            txtTenSP.setHelperText("Mã sản phẩm đã sử dụng");
            return ;
        }
        try {
            if(CKNull()){
                insert();
                
            }
            
        } catch (Exception e) {
            DialogHelper.alert(this, String.valueOf(e));
        }
        
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
       if (evt.getClickCount() == 1) {
            this.index = tblSanPham.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
                tabs.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void cboLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            if(CKNull()) {
            update();
        }             
        } catch (Exception e) {
            DialogHelper.alert(this, String.valueOf(e));
        }
  
        

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clear();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        load();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void lblHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMouseClicked
        this.selectImage();
    }//GEN-LAST:event_lblHinhAnhMouseClicked

    private void lblHinhAnhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblHinhAnhMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.console.swing.Button btnLamMoi;
    private com.console.swing.Button btnSua;
    private com.console.swing.Button btnThem;
    private com.console.swing.Button btnXoa;
    private com.console.swing.Combobox cboLoai;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JScrollPane spTable;
    private com.console.swing.MaterialTabbed tabs;
    private com.console.swing.Table tblSanPham;
    private javax.swing.JTextArea txtGhiChu;
    private com.console.swing.TextField txtGiaBan;
    private com.console.swing.TextField txtHang;
    private com.console.swing.TextField txtMaSP;
    private com.console.swing.TextField txtSL;
    private com.console.swing.TextField txtTenSP;
    private com.console.swing.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
