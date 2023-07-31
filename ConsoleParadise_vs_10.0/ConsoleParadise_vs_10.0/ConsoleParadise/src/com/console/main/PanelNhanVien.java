
package com.console.main;

import com.console.dao.ChamCongDAO;
import com.console.dao.NhanVienDAO;
import com.console.entity.ChamCong;
import com.console.entity.NhanVien;
import com.console.helper.Auth;
import com.console.helper.DateHelper;
import com.console.helper.DialogHelper;
import com.console.helper.XImage;
import com.console.swing.ScrollBar;
import javax.swing.JFileChooser;
import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class PanelNhanVien extends javax.swing.JPanel {

    NhanVienDAO dao = new NhanVienDAO();
    ChamCongDAO dao2 = new ChamCongDAO();
    
    int index = 0;
    boolean check=false;
    public PanelNhanVien() {
        initComponents();
        spNhanVien.setVerticalScrollBar(new ScrollBar());
//        chDate.setTextField(txtNgaySinh);
//        chDate.setLabelCurrentDayVisible(false);
//        chDate.setDateFormat(new SimpleDateFormat("MM-dd-yyy"));
        load();
        lblGioiTinh.setVisible(false);
        lblVaiTro.setVisible(false);
        lblHinhAnh.setToolTipText(" ");
    }
    void load() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();
            List<NhanVien> list = dao.selectByKeyword(keyword);
           // List<NhanVien> list = dao.select();
            for (NhanVien nv : list) {
                Object[] row = {
                    
                nv.getMaNV(),
                nv.getHoTen(),
                nv.isGioiTinh() ? "Nam": "Nữ",
                nv.getSoDT(),
                nv.getMatKhau(),
                nv.getNgaySinh(),
                nv.getDiaChi(),
                nv.isVaiTro() ? "Quản lí":"Nhân viên",
                nv.getImg()
                        
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    void check(){
        check=false;
            if(txtMaNV.getText().equals("")){
            txtMaNV.setHelperText("Không được để trống!");
            return;
        }else{
            txtMaNV.setHelperText("");
        }
        if(txtTenNv.getText().equals("")){
            txtTenNv.setHelperText("Không được để trống!");
            return;
        }else{
            txtTenNv.setHelperText("");
        }
        if(txtNgaySinh.getText().equals("")){
            txtNgaySinh.setHelperText("Không được để trống!");
            return;
        }else{
            txtNgaySinh.setHelperText("");
        }
        if(txtMatKhau.getText().equals("")){
            txtMatKhau.setHelperText("Không được để trống!");
            return;
        } 
        if(!txtMatKhau.getText().equals("")&&txtMatKhau.getText().length()<3){
            txtMatKhau.setHelperText("Mật khẩu phải từ 3 kí tự");
            return;
        }else{
            txtMatKhau.setHelperText("");
        }
        if(txtSdt.getText().equals("")){
            txtSdt.setHelperText("Không được để trống!");
            return;
        }       
        else{
            txtSdt.setHelperText("");
        }
        String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        boolean kt = txtSdt.getText().matches(reg);
        if(!kt){
            txtSdt.setHelperText("Định dạng số điện thoại không chính xác");
            return;
        }else{
            txtSdt.setHelperText("");
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
        }else{
            lblGioiTinh.setVisible(false);
        }
        if(!rdoQuanLi.isSelected()&&!rdoNhanVien.isSelected()){
            lblVaiTro.setVisible(true);
            return;
        }else{
            lblVaiTro.setVisible(false);
        }
        
            check=true;     
    }
    NhanVien getModel(){
        String x = txtNgaySinh.getText();
        String replaceString = x.replace('-', '/');
        
        NhanVien model = new NhanVien();
        
        model.setMaNV(txtMaNV.getText());
        model.setHoTen(txtTenNv.getText());
        model.setDiaChi(txtDiaChi.getText());
        model.setMatKhau(txtMatKhau.getText());
        model.setNgaySinh(DateHelper.toDate(replaceString));
        model.setVaiTro(rdoQuanLi.isSelected());
        model.setGioiTinh(rdoNam.isSelected());
        model.setImg(lblHinhAnh.getToolTipText());
        model.setSoDT(txtSdt.getText());
        
        return model;
        
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
    void update(){
        NhanVien model = getModel();
        try {
//            if(!txtMaNV.getText().equals(model.getMaNV())){
//                DialogHelper.alert(this, "Mã nhân viên không thể sửa đổi");
//                txtMaNV.setText(model.getMaNV());
//            }
            dao.update(model);
            this.load();
            clear();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    void setModel(NhanVien model){
        
        txtMaNV.setText(model.getMaNV());
        txtTenNv.setText(model.getHoTen());
        txtSdt.setText(String.valueOf(model.getSoDT()));
        txtMatKhau.setText(model.getMatKhau());
        txtNgaySinh.setText(DateHelper.toString(model.getNgaySinh()));
        txtDiaChi.setText(model.getDiaChi());
        rdoQuanLi.setSelected(model.isVaiTro());
        rdoNhanVien.setSelected(!model.isVaiTro());
        rdoNam.setSelected(model.isGioiTinh());
        rdoNu.setSelected(!model.isGioiTinh());                
        lblHinhAnh.setToolTipText(model.getImg());
        if(model.getImg().equals("")||model.getImg().equals(" ") ){
            lblHinhAnh.setIcon(XImage.readLogo(model.getImg()));
            ImageIcon hinhanh = new ImageIcon(new ImageIcon("logos//avtTrang.png").
            getImage().getScaledInstance(lblHinhAnh.getWidth(), 
            lblHinhAnh.getHeight(), Image.SCALE_SMOOTH)); 
                lblHinhAnh.setIcon(hinhanh);                
                lblHinhAnh.setToolTipText(model.getImg());
                return;
        }
        
            lblHinhAnh.setIcon(XImage.readLogo(model.getImg()));
            ImageIcon hinhanh = new ImageIcon(new ImageIcon("logos//"+model.getImg()).
            getImage().getScaledInstance(lblHinhAnh.getWidth(), 
            lblHinhAnh.getHeight(), Image.SCALE_SMOOTH)); 
                lblHinhAnh.setIcon(hinhanh);                
                lblHinhAnh.setToolTipText(model.getImg());              
    }
    void edit() {
        try {
            String maNV = (String) tblNhanVien.getValueAt(this.index, 0);
            NhanVien model = dao.findById(maNV);
            if(model != null){
                this.setModel(model);
//                this.setStatus(false);
            }
        } 
        catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    void clear() {
            txtMaNV.setText("");
            txtDiaChi.setText("");
            txtMatKhau.setText("");
            txtNgaySinh.setText("");
            txtSdt.setText("");
            txtTenNv.setText("");
            buttonGroup1.clearSelection();
            buttonGroup2.clearSelection();
        lblHinhAnh.setIcon(null);
        lblHinhAnh.setToolTipText("");
    }

    void insert(){
        NhanVien model = dao.findById(txtMaNV.getText());
        if(model!=null){
            txtMaNV.setHelperText("Mã đã được sử dụng, vui lòng chọn mã khác");
            return;
        }else{
            txtMaNV.setHelperText("");
        }
        model = getModel();
        dao.insert(model);
        insertChamCong();  
        this.load();
        
        DialogHelper.alert(this, "Thêm mới thành công");
    }
    void insertChamCong(){
        ChamCong model = new ChamCong();
        model.setMaNv(txtMaNV.getText());
        model.setSoGioLam(Integer.parseInt("0"));
        model.setLuong(Double.parseDouble("0"));
        model.setLuongThuong(Double.parseDouble("0"));
        model.setLuongPhat(Double.parseDouble("0"));
        model.setTongLuong(Double.parseDouble("0"));
        model.setThang("Chưa cập nhật");
        model.setGhiChu("Nhân viên mới");
        dao2.insert(model);
        this.clear();
    }
    void xoa(){
        if (!Auth.isManager()) {
            DialogHelper.alert(this, "Bạn không có quyền xóa nhân viên");
        } else {
            String manv = txtMaNV.getText();
            if(manv.equals("")){
                DialogHelper.alert(this,"Bạn phải nhập mã nhân viên chớ =)))");return;
            }
            
            if(manv.equals(Auth.user.getMaNV())){
                DialogHelper.alert(this,"Bạn không thể xóa chính mình =)))");
            }else if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa nhân viên này,?")) {
                try {
                    dao.delete(manv);
                    this.load();
                    this.clear();
                    
                    tabs.setSelectedIndex(1);
                } catch (Exception e) {
                    
                    DialogHelper.alert(this, "Không thể xóa, có thông tin nhân viên nằm trong đơn hàng và chấm công!");
                }
            }
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel4 = new javax.swing.JLabel();
        dateChooser1 = new com.raven.datechooser.DateChooser();
        tabs = new com.console.swing.MaterialTabbed();
        jPanel1 = new javax.swing.JPanel();
        txtTenNv = new com.console.swing.TextField();
        txtMatKhau = new com.console.swing.TextField();
        txtDiaChi = new com.console.swing.TextField();
        txtSdt = new com.console.swing.TextField();
        txtNgaySinh = new com.console.swing.TextField();
        txtMaNV = new com.console.swing.TextField();
        lblHinhAnh = new javax.swing.JLabel();
        rdoQuanLi = new javax.swing.JRadioButton();
        rdoNhanVien = new javax.swing.JRadioButton();
        btnThem = new com.console.swing.Button();
        btnSua = new com.console.swing.Button();
        btnXoa = new com.console.swing.Button();
        btnLamMoi = new com.console.swing.Button();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblVaiTro = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtTimKiem = new com.console.swing.TextField();
        spNhanVien = new javax.swing.JScrollPane();
        tblNhanVien = new com.console.swing.Table();

        jLabel4.setText("jLabel4");

        dateChooser1.setTextRefernce(txtNgaySinh);

        setBackground(new java.awt.Color(255, 255, 255));

        tabs.setForeground(new java.awt.Color(109, 109, 109));
        tabs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabsMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTenNv.setLabelText("Tên nhân viên");
        jPanel1.add(txtTenNv, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, 230, -1));

        txtMatKhau.setLabelText("Mật khẩu");
        jPanel1.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, 230, -1));

        txtDiaChi.setLabelText("Địa chỉ");
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });
        jPanel1.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 250, 230, -1));

        txtSdt.setLabelText("Số điện thoại");
        txtSdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSdtActionPerformed(evt);
            }
        });
        jPanel1.add(txtSdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, 230, -1));

        txtNgaySinh.setLabelText("Ngày Sinh");
        txtNgaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgaySinhActionPerformed(evt);
            }
        });
        jPanel1.add(txtNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 230, -1));

        txtMaNV.setLabelText("Mã nhân viên");
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });
        jPanel1.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 230, -1));

        lblHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnh.setToolTipText("");
        lblHinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhMouseClicked(evt);
            }
        });
        jPanel1.add(lblHinhAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 250, 350));

        buttonGroup1.add(rdoQuanLi);
        rdoQuanLi.setForeground(new java.awt.Color(109, 109, 109));
        rdoQuanLi.setText("Quản lí");
        jPanel1.add(rdoQuanLi, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, -1, -1));

        buttonGroup1.add(rdoNhanVien);
        rdoNhanVien.setForeground(new java.awt.Color(109, 109, 109));
        rdoNhanVien.setText("Nhân viên");
        jPanel1.add(rdoNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 380, -1, -1));

        btnThem.setBackground(new java.awt.Color(46, 170, 223));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 90, 60, -1));

        btnSua.setBackground(new java.awt.Color(46, 170, 223));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 170, 60, -1));

        btnXoa.setBackground(new java.awt.Color(46, 170, 223));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 250, 60, -1));

        btnLamMoi.setBackground(new java.awt.Color(46, 170, 223));
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel1.add(btnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 330, 60, -1));

        buttonGroup2.add(rdoNam);
        rdoNam.setForeground(new java.awt.Color(109, 109, 109));
        rdoNam.setText("Nam");
        jPanel1.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 330, -1, -1));

        buttonGroup2.add(rdoNu);
        rdoNu.setForeground(new java.awt.Color(109, 109, 109));
        rdoNu.setText("Nữ");
        jPanel1.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 330, -1, -1));

        jLabel1.setForeground(new java.awt.Color(109, 109, 109));
        jLabel1.setText("*Lưu ý: mã nhân viên không thể sửa đổi, nó như thẻ căn cước vậy, mất làm cái mới");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, -1, -1));

        jLabel2.setForeground(new java.awt.Color(109, 109, 109));
        jLabel2.setText("Vai trò");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, -1, -1));

        lblVaiTro.setForeground(new java.awt.Color(255, 102, 102));
        lblVaiTro.setText("Vui lòng chọn vai trò");
        jPanel1.add(lblVaiTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 400, 210, -1));

        jLabel5.setForeground(new java.awt.Color(109, 109, 109));
        jLabel5.setText("Giới tính");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, -1, -1));

        lblGioiTinh.setForeground(new java.awt.Color(255, 102, 102));
        lblGioiTinh.setText("Vui lòng chọn giới tính");
        jPanel1.add(lblGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 350, 210, -1));

        tabs.addTab("Cập nhật", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtTimKiem.setLabelText("Tìm kiếm theo tên");
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Họ và tên", "Giới tính", "Số điện thoại", "Mật khẩu", "Năm sinh", "Địa chỉ", "Vai trò", "IMG"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        spNhanVien.setViewportView(tblNhanVien);
        if (tblNhanVien.getColumnModel().getColumnCount() > 0) {
            tblNhanVien.getColumnModel().getColumn(0).setMinWidth(40);
            tblNhanVien.getColumnModel().getColumn(0).setMaxWidth(100);
            tblNhanVien.getColumnModel().getColumn(1).setMinWidth(150);
            tblNhanVien.getColumnModel().getColumn(1).setMaxWidth(150);
            tblNhanVien.getColumnModel().getColumn(2).setMinWidth(50);
            tblNhanVien.getColumnModel().getColumn(2).setMaxWidth(100);
            tblNhanVien.getColumnModel().getColumn(3).setMinWidth(100);
            tblNhanVien.getColumnModel().getColumn(3).setMaxWidth(100);
            tblNhanVien.getColumnModel().getColumn(4).setMinWidth(80);
            tblNhanVien.getColumnModel().getColumn(4).setMaxWidth(100);
            tblNhanVien.getColumnModel().getColumn(5).setMinWidth(100);
            tblNhanVien.getColumnModel().getColumn(5).setMaxWidth(100);
            tblNhanVien.getColumnModel().getColumn(7).setMinWidth(50);
            tblNhanVien.getColumnModel().getColumn(7).setMaxWidth(100);
            tblNhanVien.getColumnModel().getColumn(8).setMinWidth(100);
            tblNhanVien.getColumnModel().getColumn(8).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(spNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Danh sách", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void txtSdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSdtActionPerformed

    private void tabsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabsMouseClicked
        
    }//GEN-LAST:event_tabsMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        check();
        try {
            if(check==true){
            insert();
             
            
        }
        } catch (Exception e) {
            DialogHelper.alert(this, String.valueOf(e));
        }
        
        
        
        
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        if(evt.getClickCount() == 1){
            this.index = tblNhanVien.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
                tabs.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void lblHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMouseClicked
        this.selectImage();
    }//GEN-LAST:event_lblHinhAnhMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clear();
        
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        check();
        if(check){
            update();
        }
        
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        xoa();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        load();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtNgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgaySinhActionPerformed
        dateChooser1.showPopup();
    }//GEN-LAST:event_txtNgaySinhActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.console.swing.Button btnLamMoi;
    private com.console.swing.Button btnSua;
    private com.console.swing.Button btnThem;
    private com.console.swing.Button btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblVaiTro;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoQuanLi;
    private javax.swing.JScrollPane spNhanVien;
    private com.console.swing.MaterialTabbed tabs;
    private com.console.swing.Table tblNhanVien;
    private com.console.swing.TextField txtDiaChi;
    private com.console.swing.TextField txtMaNV;
    private com.console.swing.TextField txtMatKhau;
    private com.console.swing.TextField txtNgaySinh;
    private com.console.swing.TextField txtSdt;
    private com.console.swing.TextField txtTenNv;
    private com.console.swing.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
