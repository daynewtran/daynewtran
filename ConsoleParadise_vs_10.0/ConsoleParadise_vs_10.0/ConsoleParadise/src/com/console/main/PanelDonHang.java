
package com.console.main;
import com.console.dao.CapBacDAO;
import com.console.dao.DonHangChiTietDAO;
import com.console.dao.DonHangDAO;
import com.console.dao.KhachHangDAO;
import com.console.dao.SanPhamDAO;
import com.console.entity.DonHang;
import com.console.entity.DonHangCT;
import com.console.entity.KhachHang;
import com.console.entity.SanPham;
import com.console.helper.Auth;
import com.console.helper.DateHelper;
import com.console.helper.DialogHelper;
import com.console.helper.SendMail;
import static com.console.main.test.decorate;
import com.console.swing.ScrollBar;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class PanelDonHang extends javax.swing.JPanel {

    DonHangDAO dao = new DonHangDAO();
    KhachHangDAO dao2 = new KhachHangDAO();
    SanPhamDAO dao3 = new SanPhamDAO();
    DonHangChiTietDAO dao4 = new DonHangChiTietDAO();
    CapBacDAO daoCB = new CapBacDAO();
    double tongTien=0;
    int index;
    int stt =0;
    
    public PanelDonHang() {
        initComponents();
        init();
    }
    void init(){
        spGhiChu.setVerticalScrollBar(new ScrollBar());
        spGioHang.setVerticalScrollBar(new ScrollBar());
        spTable.setVerticalScrollBar(new ScrollBar()); 
        lblDangXem.setVisible(false);
        lblDX.setVisible(false);
        load();
        fillComboTenSP();
        txtNgay.setText(DateHelper.toString(DateHelper.now()));
        txtNguoiTao.setText(Auth.user.getMaNV());
        batDau();
        AutoCompleteDecorator.decorate(cboTenSP);
    }
    private void XuatHoaDonPDF() throws IOException, DocumentException {
        File dir = new File("C:\\hoaDon");
        // Tạo thư mục nếu chưa tồn tại
        if(!dir.exists()){
            dir.mkdirs();
        }
        Document doc = new Document();
        String x = txtNgay.getText();
        String replaceString = x.replace('/', '-');
       Font f = new Font(BaseFont.createFont("/com/console/font/font-times-new-roman/font-times-new-roman/font-times-new-roman.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
            
        //for(int i=1;i<100;i++){
            try {
//generate a PDF at the specified location  
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("C:\\hoaDon\\HD_" +lblDX.getText()+"_"+ txtSDT.getText() + "_" + txtNguoiTao.getText() + "_" + replaceString + ".pdf"));
            
//opens the PDF  
            doc.open();
//adds paragraph to the PDF file  
            Image image1 = Image.getInstance("logos\\logo.jpg");
            //doc.add(new Paragraph("Logo"));
            image1.scalePercent(50f);
            image1.setAlignment(Element.ALIGN_CENTER);
            doc.add(image1);
            Paragraph title1 = new Paragraph("CONSOLE PARADISE",
					FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17)));
            Paragraph paragraph = new Paragraph(" CONSOLE PARADISE  \n");
            //Phrase phrase00 = new Phrase("\nCONSOLE PARADISE\n");
            
            paragraph.setAlignment(Element.ALIGN_CENTER);
            
            //paragraph.setFont(new Font("Times new roman", 200));
            //doc.add(paragraph);
            title1.setAlignment(Element.ALIGN_CENTER);
            doc.add(title1);
            
            Paragraph paragraph1 = new Paragraph();
            paragraph1.setIndentationLeft(80);
            paragraph1.setIndentationRight(80);
            paragraph1.setAlignment(Element.ALIGN_CENTER);
            paragraph1.setSpacingAfter(15);
            paragraph1.setFont(new Font(Font.FontFamily.TIMES_ROMAN));
            //Thêm nội dung cho cả 2 đoạn văn bản trên
    
    Phrase phrase1 = new Phrase("\n------------------------------------------------\n");
    Phrase phrase2 = new Phrase("Số điện thoại KH : " + txtSDT.getText() + "\n",f);
    Phrase phrase3 = new Phrase("Thu ngân : " + txtNguoiTao.getText() + "\n",f);
    Phrase phrase4 = new Phrase("Ngày tạo : " + txtNgay.getText() + "\n",f);
            paragraph1.add(phrase1);
            paragraph1.add(phrase2);
            paragraph1.add(phrase3);
            paragraph1.add(phrase4);
    List<DonHangCT> model = dao4.findById(Integer.parseInt(lblDX.getText()));
                    PdfPTable t = new PdfPTable(4);
                    
                    t.setSpacingBefore(20);
                    t.setSpacingAfter(20);
                    PdfPCell c1 = new PdfPCell(new Phrase("Sản phẩm",f));
                    t.addCell(c1);
                    PdfPCell c2 = new PdfPCell(new Phrase("Số lượng",f));
                    t.addCell(c2);
                    PdfPCell c3 = new PdfPCell(new Phrase("Đơn giá",f));
                    t.addCell(c3);
                    PdfPCell c4 = new PdfPCell(new Phrase("Thành tiền",f));
                    t.addCell(c4);
                    //t.set(Element.ALIGN_CENTER);
            for(int y=0;y<model.size();y++){               
                SanPham sp = dao3.findById(String.valueOf(tblGioHang.getValueAt(y, 1)));
                    t.addCell(new Phrase(sp.getTenSP(),f));
                    t.addCell(new Phrase(String.valueOf(tblGioHang.getValueAt(y, 2)),f));
                    t.addCell(new Phrase(String.valueOf(tblGioHang.getValueAt(y, 3)),f));
                    t.addCell(new Phrase(String.valueOf(tblGioHang.getValueAt(y, 4))+" VND ",f));
                    

            }
            Paragraph paragraph2 = new Paragraph();
            paragraph2.setIndentationLeft(80);
            paragraph2.setIndentationRight(80);
            paragraph2.setAlignment(Element.ALIGN_CENTER);
            paragraph2.setSpacingAfter(15);
    Phrase phrase5 = new Phrase("Tổng tiền  : " +lblTT.getText()+" VND "+ "\n",f);
    Phrase phrase6 = new Phrase("Giảm giá : " + txtGiamGia.getText()+"% " + "\n",f);
    Phrase phrase7 = new Phrase("Tiền thanh toán : " + txtTienThanhToan.getText() +" VND "+ "\n",f);
    //Phrase phrase8 = new Phrase("Tien tra lai : " + txtTienTraLai.getText() + "\n");
    Phrase phrase9 = new Phrase("\n------------------------------------------------\n",f);
    Phrase phrase0 = new Phrase("Created by: Rito Software");
    paragraph1.add(phrase1);
            paragraph2.add(phrase5);
            paragraph2.add(phrase6);
            paragraph2.add(phrase7);
            //paragraph1.add(phrase8);
            paragraph2.add(phrase9);
            paragraph2.add(phrase0);

            
            
            doc.add(paragraph1);
            doc.add(t);
            doc.add(paragraph2);
            KhachHang kh = dao2.findById(txtSDT.getText());
            String tieuDe = "Thông báo về việc phát hành Hóa đơn điện tử";
            String noiDung = "Kính gửi Quý khách hàng,\n" +
"\n" +
"Xin trân trọng cảm ơn Quý khách hàng đã sử dụng dịch vụ Hóa đơn điện tử của Console Paradise Cần Thơ.\n" +
"\n" +
"Đơn vị Console Paradise CẦN THƠ vừa phát hành hóa đơn điện tử được đặt hàng vào ngày " +txtNgay.getText()+" của Quý khách hàng.\n" +
"\n" +
"1. Hóa đơn của khách hàng có:\n" +
"- Số hóa đơn: "+lblMaDH.getText()+"\n" +
"- Số điện thoại khách hàng:"+txtSDT.getText()+"\n"+
"-Tên khách hàng:"+kh.getTenKH()+"\n"
                    + "Tiền thanh toán: "+txtTienThanhToan.getText()+"\n"
                    + "\n";
            String gmail = kh.getEmail();
            String ff = "C:\\hoaDon\\HD_" +lblDX.getText()+"_"+ txtSDT.getText() + "_" + txtNguoiTao.getText() + "_" + replaceString + ".pdf"; 
            
            
            DialogHelper.alert(this, "Xuất hóa đơn thành công!");
          
            //Set image transparency
//close the PDF file  
            doc.close();
//closes the writer  
            writer.close();
            SendMail.SendPDF(tieuDe, noiDung, gmail, ff);
            return;
            
        } catch (Exception e) {
                DialogHelper.alert(this, "Vui lòng đóng hóa đơn đang mở");
        }
            
        //}
        
    }
    
    void fillComboTenSP(){
        cboTenSP.removeAllItems();
        List<SanPham> list = dao3.select2();
        
        for(SanPham s : list){
           cboTenSP.addItem(s);
        }
        
    }
    DonHangCT getModelCT(){        
        for(int i=0;i<stt;i++){
            DonHangCT model = new DonHangCT();
            model.setMaDH(Integer.valueOf(lblMaDH.getText()));
            model.setMaSP(String.valueOf(tblGioHang.getValueAt(i, 1)));
            model.setSoLuong(Integer.parseInt(String.valueOf(tblGioHang.getValueAt(i, 2)) ) );
            model.setThanhTien(Double.parseDouble(String.valueOf(tblGioHang.getValueAt(i, 4)) ));             
        }
        
        return null;
        
        
    }
    void insert(){
        KhachHang kh = dao2.findById(txtSDT.getText());
        if(kh==null){
           DialogHelper.alert(this, "Khách hàng này chưa đăng kí");
           return;
        }
        
        DonHang model = getModel();
        dao.insert(model);        
        int maDH = model.getMaDonHang();    
        this.load();
    }
    void insertDHCT(){
        try {
            for(int i=0;i<stt;i++){
            DonHangCT model = new DonHangCT();
            model.setMaDH(Integer.valueOf(lblMaDH.getText()));
            model.setMaSP(String.valueOf(tblGioHang.getValueAt(i, 1)));
            model.setSoLuong(Integer.parseInt(String.valueOf(tblGioHang.getValueAt(i, 2))));
            model.setThanhTien(Double.parseDouble(String.valueOf(tblGioHang.getValueAt(i, 4)).replace(".", "") ));
            SanPham sp = dao3.findById(String.valueOf(tblGioHang.getValueAt(i, 1)));
            int sl = sp.getSoLuong()- Integer.parseInt(String.valueOf(tblGioHang.getValueAt(i, 2)));           
            dao3.update3(sl, String.valueOf(tblGioHang.getValueAt(i, 1)));        
            if(sl<5){
                DialogHelper.alert(this, "Số lượng sản phẩm "+sp.toString()+" còn "+sl+", vui lòng nhập thêm hàng!!!");
            }
            dao4.insert(model);
            DialogHelper.alert(this, "Đã thêm đơn hàng");
        }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
    }
    
    
    void themSPvaoGH(){
        if(txtSL.getText().equals("0")||txtSL.getText().equals("") ||Double.parseDouble(txtSL.getText())<=0 ){
            txtSL.setHelperText("Vui lòng nhập số dương!");
            return;
        }
      
        
        try {
            Integer.parseInt(txtSL.getText());
        } catch (Exception e) {
            txtSL.setHelperText("Bạn phải nhập số");
            return;
        }
       //check số lượng
        txtSL.setHelperText("");
        String tenSp = String.valueOf(cboTenSP.getSelectedItem());
        String maSp = tenSp.substring(0, 5);
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        SanPham sp = dao3.findById(maSp);
        if(Integer.parseInt(txtSL.getText())>sp.getSoLuong()){
            DialogHelper.alert(this, "Không đủ số lượng, số sản phẩm còn lại: "+sp.getSoLuong());
            return;
        }
        
        int soLuong = Integer.parseInt(txtSL.getText());
        double thanhTien = soLuong*sp.getGiaBan();
        tongTien=thanhTien+tongTien;
        int check = 0;
        // check số thứ tự
        // tăng số lượng sản phẩm
        if(stt!=0){
            for(int i=0;i<stt;i++){
            if(maSp.equals(String.valueOf(tblGioHang.getValueAt(i, 1)))){
                int sl = soLuong + Integer.parseInt(String.valueOf(tblGioHang.getValueAt(i, 2)));               
                tblGioHang.setValueAt(sl, i, 2);  
                check=1;
                thanhTien = sl*sp.getGiaBan();
                DecimalFormat f = new DecimalFormat("###,###,###");
            String s = f.format(thanhTien);
            String ss = s.replace(',', '.');
                tblGioHang.setValueAt(ss, i, 4);
            }
        }
            if(check==1){
                DecimalFormat f = new DecimalFormat("###,###,###");
            String s1 = f.format(tongTien);
            String s2 = s1.replace(',', '.');
            lblTT.setText(s2);
        tienTT();
        return;
        }            
        }
        //thêm sản phẩm vào giỏ hàng
        DecimalFormat f = new DecimalFormat("###,###,###");
            String s = f.format(thanhTien);
            String ss = s.replace(',', '.');
            String s3 = f.format(sp.getGiaBan());
            String s4 = s.replace(',', '.');
            stt = stt+1;
           Object[] row = {
            stt,
            sp.getMaSP(),
            txtSL.getText(),
            s4,
            ss
        };
        model.addRow(row); 
        
            String s1 = f.format(tongTien);
            String s2 = s1.replace(',', '.');
         lblTT.setText(s2);        
        tienTT();     
    }
    void tienTraLai(){
        if(!txtTienThanhToan.getText().equals("")&&!txtTienNhan.getText().equals("")){
            
            
            DecimalFormat f = new DecimalFormat("###,###,###");
            String s1 = f.format(Double.parseDouble(txtTienNhan.getText())-Double.parseDouble(txtTienThanhToan.getText().replace(".", "")));
            String s2 = s1.replace(',', '.');
            txtTienTraLai.setText(s2);
        }else{
            txtTienTraLai.setText("");
        }
    }
    void clear(){
        txtSL.setText("1");
        txtSDT.setText("");
        txtNgay.setText(DateHelper.toString(DateHelper.now()));
        txtTienThanhToan.setText("");
        tongTien=0;
        txtTienThanhToan.setHelperText("");
        txtGiamGia.setText("0");
        txtTienNhan.setText("");
        txtTienTraLai.setText("");
        lblDangXem.setVisible(false);
        lblDX.setVisible(false);
        stt=0;
        lblTT.setText(String.valueOf(tongTien));
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);
    }
    void load() {
        DefaultTableModel model = (DefaultTableModel) tblDonHang.getModel();
        model.setRowCount(0);
        try {
            List<DonHang> list = dao.select();
            DecimalFormat f = new DecimalFormat("###,###,###");
           
            for (DonHang dh : list) {
                 String s1 = f.format(dh.getTongTien());
            String s2 = s1.replace(',', '.');
                //System.out.println(dh.getMaDonHang());
                //String x= dh.getSDT();
                Object[] row = {        
                   
                    dh.getMaDonHang(),
                    dh.getMaNV(),
                    //dao2.findByID(x),
                    dh.getSDT(),
                    s2,
                    dh.getNgayTaoDon(),
                    dh.getGhiChu(), 
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, String.valueOf(e));
        }
    }
    void setModel(DonHang model) {
        try {            
            DecimalFormat f = new DecimalFormat("###,###,###");          
         txtSDT.setText(model.getSDT());
         txtNgay.setText(DateHelper.toString(model.getNgayTaoDon()));
         txtNguoiTao.setText(model.getMaNV());
         String s3 = f.format(model.getTongTien());
            String s4 = s3.replace(',', '.');
         txtTienThanhToan.setText(s4);
            
         
            //thay thế tất cả ký tự 't' thành ' '   
            txtGhiChu.setText(model.getGhiChu());
            lblDX.setVisible(true);
            lblDX.setText(String.valueOf(model.getMaDonHang()));
            lblDangXem.setVisible(true);
        } catch (Exception e) {
            DialogHelper.alert(this, String.valueOf(e));
        }
        

    }
    void edit(){
        try {
            int maDH = Integer.parseInt( String.valueOf(tblDonHang.getValueAt(this.index, 0)));
            DonHang model = dao.findById(maDH);
            //List<DonHangCT> model2 = dao4.selectByKeyword(maDH);
            if (model != null) {
                this.setModel(model);
                this.setModelCT(maDH);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, String.valueOf(e));
        }
    }
    void xoa(){
        if(DialogHelper.confirm(this, "Bạn muốn xóa đơn hàng này?")){
         int maDh = Integer.valueOf(lblDX.getText());
        DonHang model = dao.findById(maDh);
        if(model!=null){
            dao4.delete(maDh);
           dao.delete(maDh);
            DialogHelper.alert(this, "Xóa thành công");
            load();
            clear();
        }   
        }
        
    }
    DonHang getModel(){
        DonHang model = new DonHang();
        
        model.setMaNV(txtNguoiTao.getText());
        model.setGhiChu(txtGhiChu.getText());
        model.setNgayTaoDon(DateHelper.toDate(txtNgay.getText()));
        model.setSDT(txtSDT.getText());
        model.setTongTien(Double.parseDouble(txtTienThanhToan.getText().replace(".", ""))); 
        return model;    
    }
    void checkCapBac() throws SQLException{
        
        double TienDaMua = dao.TongTien(txtSDT.getText());
        KhachHang kh = dao2.findById(txtSDT.getText());
        int CapBac = kh.getSoCap();
        if(CapBac==1&&TienDaMua>5000000){
            
            String tieuDe = "thông báo về việc tăng cấp bậc khách hàng";
            String noiDung ="Kính gửi Quý khách hàng,\n" +
"\n" +
"Xin trân trọng cảm ơn Quý khách hàng đã sử dụng dịch vụ của cửa hàng Console Paradise Cần Thơ.\n" +
"\n" +
"Đơn vị TRUNG TÂM FPT POLYTECHNIC CẦN THƠ xin thông báo, cấp bậc thành viên của khách hàng đã được nâng cấp với giá trị khuyến mãi lên đến 5% cho mỗi đơn hàng quý khách mua tại shop.\n\n"
                    + "Trân trọng cảm ơn Quý khách và mời quý khách đến trải nghiệm những ưu đãi thành viên của shop Console Paradise";
            String gmail = kh.getEmail();
            SendMail.send(tieuDe, noiDung, gmail);
            dao2.updateCB(2, txtSDT.getText());
            DialogHelper.alert(this, "Khách hàng đã tăng lên cấp bậc 2");
        }
        else if(CapBac==2&&TienDaMua>10000000){
            
            String tieuDe = "thông báo về việc tăng cấp bậc khách hàng";
            String noiDung ="Kính gửi Quý khách hàng,\n" +
"\n" +
"Xin trân trọng cảm ơn Quý khách hàng đã sử dụng dịch vụ của cửa hàng Console Paradise Cần Thơ.\n" +
"\n" +
"Đơn vị TRUNG TÂM FPT POLYTECHNIC CẦN THƠ xin thông báo, cấp bậc thành viên của khách hàng đã được nâng cấp với giá trị khuyến mãi lên đến 10% cho mỗi đơn hàng quý khách mua tại shop.\n\n"
                    + "Trân trọng cảm ơn Quý khách và mời quý khách đến trải nghiệm những ưu đãi thành viên của shop Console Paradise";
            String gmail = kh.getEmail();
            SendMail.send(tieuDe, noiDung, gmail);
            dao2.updateCB(3, txtSDT.getText());
            DialogHelper.alert(this, "Khách hàng đã tăng lên cấp bậc 3");
        }   
    }
    void batDau(){
        DonHang model = dao.max();
            if (model != null) {
                lblMaDH.setText(String.valueOf(model.getMaDonHang()+1));
                //this.setModel(model);
//                this.setStatus(false);
            }
    }
//    void ThemDHCT(){
//         List<DonHang> x =dao.max();
//        System.out.println(x);
//        
//    }
    void tienTT(){
        if(txtGiamGia.getText().equals("0")){
            txtTienThanhToan.setText(lblTT.getText());
            return;
        }
        if(!txtGiamGia.getText().equals("0")||txtGiamGia.getText().equals("")){
            double tongTien=this.tongTien;
        double giamGia = Double.parseDouble(txtGiamGia.getText());
        double tienTT = tongTien-(tongTien*giamGia/100);
        txtTienThanhToan.setText(String.valueOf(tienTT));
        DecimalFormat f = new DecimalFormat("###,###,###");
            String s1 = f.format(tienTT);
            String s2 = s1.replace(',', '.');
            //thay thế tất cả ký tự ',' thành '.'   
            txtTienThanhToan.setText(s2);
        }
        
    }
    void setModelCT(int maDH){
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);
         tongTien=0;
        try {            
            List<DonHangCT> list = dao4.selectByKeyword(maDH);
            //tính tổng tiền
            
            for (DonHangCT dh : list) {
                SanPham model2 = dao3.findById(dh.getMaSP());
                stt=stt+1;
                tongTien=tongTien+dh.getThanhTien();
                DecimalFormat f = new DecimalFormat("###,###,###");
            String s = f.format(tongTien);
            String ss = s.replace(',', '.');
            
                lblTT.setText(ss);
                //thêm vào giỏ hàng
                double donGia = model2.getGiaBan();
                String s1 = f.format(model2.getGiaBan());
                String s2 = s1.replace(',', '.');  
                String s3 = f.format(dh.getThanhTien());
                String s4 = s3.replace(',', '.');
                    Object[] row = {
                    
                    stt,
                    dh.getMaSP(),
                    dh.getSoLuong(),
                    s2,
                    s4
                };
                model.addRow(row);
            }
            
            //[(tongTien-tienTT)/tongTien]*100 = giamGia   
            if(Double.parseDouble(lblTT.getText().replace(".", ""))==Double.parseDouble(txtTienThanhToan.getText().replace(".", ""))){
                txtGiamGia.setText("0");
            }else{
                String tienTT = txtTienThanhToan.getText().replace(".", "");                        
            double tienDaGiam= tongTien-Double.parseDouble(tienTT);
            double x = 100*(tienDaGiam/tongTien);
            
             txtGiamGia.setText(String.valueOf(x));
            }
            
        } catch (Exception e) {
            DialogHelper.alert(this, String.valueOf(e));
        }
    }
    
//    private void XuatHoaDon(){
//            String h ="";
//            h = h + "\t      CONSOLE PARADISE       \n";
//            h = h + "Số điện thoại KH : "+txtSDT.getText()+"\n";
//            h = h + "Thu ngân : "+txtNguoiTao.getText()+"\n";
//            h = h + "Ngày tạo : "+txtNgay.getText()+"\n";
//            h = h + "Giảm giá : "+txtGiamGia.getText()+"\n";
//            h = h + "Thanh toán : "+txtTienThanhToan.getText()+"\n";
//            h = h + "Tiền nhận : "+txtTienNhan.getText()+"\n";
//            h = h + "Tiền trả lại : "+txtTienTraLai.getText()+"\n";
//            h = h + "\t Created by: Rito Software";
//            try {
//            PrintStream p = new PrintStream("E:\\Save\\files.txt");
//                p.print(h);
//                p.close();
//                DialogHelper.alert(this,"Xuất hóa đơn thành công!");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        tabs = new com.console.swing.MaterialTabbed();
        jPanel1 = new javax.swing.JPanel();
        spGioHang = new javax.swing.JScrollPane();
        tblGioHang = new com.console.swing.Table();
        spGhiChu = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        txtSDT = new com.console.swing.TextField();
        txtTienThanhToan = new com.console.swing.TextField();
        txtGiamGia = new com.console.swing.TextField();
        txtTienNhan = new com.console.swing.TextField();
        txtTienTraLai = new com.console.swing.TextField();
        btnIn = new com.console.swing.Button();
        btnLamMoi = new com.console.swing.Button();
        btnXoaAll = new com.console.swing.Button();
        btnXoaDH = new com.console.swing.Button();
        btnLuu = new com.console.swing.Button();
        btnthem = new com.console.swing.Button();
        txtNgay = new com.console.swing.TextField();
        lblTT = new javax.swing.JLabel();
        lblTongTien1 = new javax.swing.JLabel();
        txtNguoiTao = new com.console.swing.TextField();
        jLabel2 = new javax.swing.JLabel();
        lblMaDH = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSL = new com.console.swing.TextField();
        jLabel5 = new javax.swing.JLabel();
        cboTenSP = new com.console.swing.Combobox();
        lblDX = new javax.swing.JLabel();
        lblDangXem = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        tblDonHang = new com.console.swing.Table();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        spGioHang.setViewportView(tblGioHang);

        jPanel1.add(spGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 640, 200));

        txtGhiChu.setColumns(20);
        txtGhiChu.setForeground(new java.awt.Color(109, 109, 109));
        txtGhiChu.setRows(5);
        txtGhiChu.setText("Ghi chú");
        spGhiChu.setViewportView(txtGhiChu);

        jPanel1.add(spGhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 640, 110));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 153, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ĐƠN HÀNG");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 300, -1));

        txtSDT.setLabelText("Số điện thoại KH");
        txtSDT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtSDTMouseExited(evt);
            }
        });
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
        });
        jPanel1.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 270, -1));

        txtTienThanhToan.setEnabled(false);
        txtTienThanhToan.setLabelText("Tiền thanh toán");
        txtTienThanhToan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienThanhToanKeyReleased(evt);
            }
        });
        jPanel1.add(txtTienThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 170, 140, -1));

        txtGiamGia.setText("0");
        txtGiamGia.setLabelText("Giảm giá(%)");
        txtGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiamGiaActionPerformed(evt);
            }
        });
        txtGiamGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiamGiaKeyReleased(evt);
            }
        });
        jPanel1.add(txtGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 170, 120, -1));

        txtTienNhan.setLabelText("Tiền nhận");
        txtTienNhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienNhanKeyReleased(evt);
            }
        });
        jPanel1.add(txtTienNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 270, 120, -1));

        txtTienTraLai.setEnabled(false);
        txtTienTraLai.setLabelText("Tiền trả lại");
        jPanel1.add(txtTienTraLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 270, 140, -1));

        btnIn.setBackground(new java.awt.Color(255, 102, 102));
        btnIn.setForeground(new java.awt.Color(255, 255, 255));
        btnIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/console/icon/Print.png"))); // NOI18N
        btnIn.setText("IN HÓA ĐƠN");
        btnIn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });
        jPanel1.add(btnIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, 110, 40));

        btnLamMoi.setBackground(new java.awt.Color(78, 143, 227));
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/console/icon/Refresh.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel1.add(btnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 340, 90, -1));

        btnXoaAll.setBackground(new java.awt.Color(78, 143, 227));
        btnXoaAll.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/console/icon/Remove from basket.png"))); // NOI18N
        btnXoaAll.setText("Xóa tất cả sản phẩm");
        btnXoaAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaAllActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoaAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, 190, -1));

        btnXoaDH.setBackground(new java.awt.Color(78, 143, 227));
        btnXoaDH.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/console/icon/Delete.png"))); // NOI18N
        btnXoaDH.setText("Xóa");
        btnXoaDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDHActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoaDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 340, 70, -1));

        btnLuu.setBackground(new java.awt.Color(78, 143, 227));
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/console/icon/Add.png"))); // NOI18N
        btnLuu.setText("Thêm");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel1.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 340, 80, -1));

        btnthem.setBackground(new java.awt.Color(78, 143, 227));
        btnthem.setForeground(new java.awt.Color(255, 255, 255));
        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });
        jPanel1.add(btnthem, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 50, -1));

        txtNgay.setEnabled(false);
        txtNgay.setLabelText("Ngày");
        jPanel1.add(txtNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 100, 120, -1));

        lblTT.setText("0");
        jPanel1.add(lblTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 140, -1));

        lblTongTien1.setText("Tổng tiền(VND):");
        jPanel1.add(lblTongTien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 90, -1));

        txtNguoiTao.setEnabled(false);
        txtNguoiTao.setLabelText("Người tạo");
        jPanel1.add(txtNguoiTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 100, 140, -1));

        jLabel2.setForeground(new java.awt.Color(255, 153, 153));
        jLabel2.setText("----------------------------------------------------------------------");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 260, -1, -1));

        lblMaDH.setForeground(new java.awt.Color(109, 109, 109));
        jPanel1.add(lblMaDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 60, 20));

        jLabel3.setForeground(new java.awt.Color(109, 109, 109));
        jLabel3.setText("Đơn hàng tiếp theo :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, -1, 20));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/console/icon/Refresh.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        txtSL.setText("1");
        txtSL.setLabelText("Số lượng");
        jPanel1.add(txtSL, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 100, -1));

        jLabel5.setForeground(new java.awt.Color(109, 109, 109));
        jLabel5.setText("Sản phẩm");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 70, 20));

        cboTenSP.setEditable(true);
        cboTenSP.setLabeText("");
        jPanel1.add(cboTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 290, -1));
        jPanel1.add(lblDX, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, 60, 20));

        lblDangXem.setText("Đơn hàng đang xem:");
        jPanel1.add(lblDangXem, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 300, -1, -1));

        tabs.addTab("Cập nhật", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã đơn hàng", "Mã nhân viên", "Số điện thoại KH", "Tổng tiền", "Ngày tạo đơn", "Ghi chú"
            }
        ));
        tblDonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDonHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblDonHangMouseEntered(evt);
            }
        });
        spTable.setViewportView(tblDonHang);
        if (tblDonHang.getColumnModel().getColumnCount() > 0) {
            tblDonHang.getColumnModel().getColumn(0).setMinWidth(100);
            tblDonHang.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        jPanel2.add(spTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 22, 1005, 333));

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

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        themSPvaoGH();
        tienTraLai();
    }//GEN-LAST:event_btnthemActionPerformed

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        if(evt.getClickCount() == 1){
            this.index = tblGioHang.rowAtPoint(evt.getPoint());
            
            if (this.index >= 0) {
                double tienTru =  Double.parseDouble(String.valueOf(tblGioHang.getValueAt(this.index, 4)).replace(".", ""));
                tongTien=tongTien-tienTru;                
                lblTT.setText(String.valueOf(tongTien));
                DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
                int vTri = tblGioHang.getSelectedRow();
                model.removeRow(vTri);
                stt=stt-1;
               
            }
        }
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void tblDonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonHangMouseClicked
        if(evt.getClickCount() == 1){
            this.index = tblDonHang.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                stt=0;
                
                this.edit();
                tabs.setSelectedIndex(0);
                
            }
        }
        
    }//GEN-LAST:event_tblDonHangMouseClicked

    private void btnXoaAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaAllActionPerformed
       DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);
        tongTien=0;
        stt=0;
        lblTT.setText(String.valueOf(tongTien));
        txtTienThanhToan.setText("0");
    }//GEN-LAST:event_btnXoaAllActionPerformed

    private void txtGiamGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiamGiaKeyReleased
        tienTT();
        
    }//GEN-LAST:event_txtGiamGiaKeyReleased

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        
        try {
            if(txtSDT.getText().equals("")){
            txtSDT.setHelperText("Không để trống số điện thoại");
            return;
        }
        KhachHang kh = dao2.findById(txtSDT.getText());
        if(kh==null){
            txtSDT.setHelperText("Khách hàng chưa đăng kí");
            return;
        }
        txtSDT.setHelperText("");
        insert();
        insertDHCT();
        
        try {
            checkCapBac();
        } catch (SQLException ex) {
            Logger.getLogger(PanelDonHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        batDau();
        } catch (Exception e) {
            System.out.println("");
        }

    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clear();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiamGiaActionPerformed

    private void txtSDTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSDTMouseExited
        
    }//GEN-LAST:event_txtSDTMouseExited

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        
        KhachHang kh = dao2.findById(txtSDT.getText());
        if(kh!=null){
            int CapBac = kh.getSoCap();
            if(CapBac==1){
                txtGiamGia.setText("0");
            }else if(CapBac==2){
                 txtGiamGia.setText("5");
            }else if(CapBac==3){
                txtGiamGia.setText("10");
            }
            tienTT();
        }else{
            txtGiamGia.setText("0");
        }
        
    }//GEN-LAST:event_txtSDTKeyReleased

    private void txtTienNhanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienNhanKeyReleased
        try {
            Double.parseDouble(txtTienNhan.getText());
        } catch (Exception e) {
            txtTienNhan.setHelperText("Phải nhập số");
        }
        if(!txtTienThanhToan.getText().equals("")&&!txtTienNhan.getText().equals("")){
            
            
            DecimalFormat f = new DecimalFormat("###,###,###");
            String s1 = f.format(Double.parseDouble(txtTienNhan.getText())-Double.parseDouble(txtTienThanhToan.getText().replace(".", "")));
            String s2 = s1.replace(',', '.');
            txtTienTraLai.setText(s2);
        }else{
            txtTienTraLai.setText("");
        }
    }//GEN-LAST:event_txtTienNhanKeyReleased

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        fillComboTenSP();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        
        try {
            XuatHoaDonPDF();
            clear();
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (DocumentException ex) {
            Logger.getLogger(PanelDonHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnInActionPerformed

    private void btnXoaDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDHActionPerformed
        xoa();
    }//GEN-LAST:event_btnXoaDHActionPerformed

    private void tblDonHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonHangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDonHangMouseEntered

    private void txtTienThanhToanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienThanhToanKeyReleased
        tienTraLai();
    }//GEN-LAST:event_txtTienThanhToanKeyReleased
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.console.swing.Button btnIn;
    private com.console.swing.Button btnLamMoi;
    private com.console.swing.Button btnLuu;
    private com.console.swing.Button btnXoaAll;
    private com.console.swing.Button btnXoaDH;
    private com.console.swing.Button btnthem;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.console.swing.Combobox cboTenSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblDX;
    private javax.swing.JLabel lblDangXem;
    private javax.swing.JLabel lblMaDH;
    private javax.swing.JLabel lblTT;
    private javax.swing.JLabel lblTongTien1;
    private javax.swing.JScrollPane spGhiChu;
    private javax.swing.JScrollPane spGioHang;
    private javax.swing.JScrollPane spTable;
    private com.console.swing.MaterialTabbed tabs;
    private com.console.swing.Table tblDonHang;
    private com.console.swing.Table tblGioHang;
    private javax.swing.JTextArea txtGhiChu;
    private com.console.swing.TextField txtGiamGia;
    private com.console.swing.TextField txtNgay;
    private com.console.swing.TextField txtNguoiTao;
    private com.console.swing.TextField txtSDT;
    private com.console.swing.TextField txtSL;
    private com.console.swing.TextField txtTienNhan;
    private com.console.swing.TextField txtTienThanhToan;
    private com.console.swing.TextField txtTienTraLai;
    // End of variables declaration//GEN-END:variables
}
