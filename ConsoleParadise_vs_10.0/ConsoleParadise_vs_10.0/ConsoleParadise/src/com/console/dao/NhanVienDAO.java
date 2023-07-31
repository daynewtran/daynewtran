
package com.console.dao;

import com.console.entity.NhanVien;
import com.console.helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class NhanVienDAO {
    public void DoiMK(String user, String pass){
        String sql="UPDATE NhanVien SET MatKhau=?  WHERE MaNV=?";
        JdbcHelper.executeUpdate(sql, pass, user);  
    }
    public String findByID(String hoTen) throws SQLException{
        String sql = "select HoTen from NHANVIEN where MaNV=?";
        ResultSet x = JdbcHelper.executeQuery(sql, hoTen);
        if(x.next()){
            return x.getString(1);
        }else{
        return null;
        }
    }
    public void insert(NhanVien model){
        String sql="INSERT INTO NhanVien (MaNV, HoTen, GioiTinh, soDT, MatKhau, NgaySinh, DiaChi, VaiTro, Img) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                
                model.getMaNV(),
                model.getHoTen(),
                model.isGioiTinh(),
                model.getSoDT(),
                model.getMatKhau(),
                model.getNgaySinh(),
                model.getDiaChi(),
                model.isVaiTro(),
                model.getImg()
                
                );
    }
    public void update(NhanVien model){
        String sql="UPDATE NhanVien SET HoTen=?, GioiTinh=?, SoDT=?, MatKhau=?, NgaySinh=?, DiaChi=?, VaiTro=?, Img=?  WHERE MaNV=?";
        JdbcHelper.executeUpdate(sql,                 
                model.getHoTen(),
                model.isGioiTinh(),
                model.getSoDT(),
                model.getMatKhau(),
                model.getNgaySinh(),
                model.getDiaChi(),
                model.isVaiTro(),
                model.getImg(),
                model.getMaNV());
    }
    public void delete(String MaNV){
        String sql="DELETE FROM NhanVien WHERE MaNV=?";
        JdbcHelper.executeUpdate(sql, MaNV);
    }
     public List<NhanVien> select(){
        String sql="SELECT * FROM NhanVien";
        return select(sql);
    }
     public List<NhanVien> selectByKeyword(String keyword){
        String sql="SELECT * FROM NhanVien WHERE HoTen LIKE ?";
        return select(sql, "%"+keyword+"%");
    }
     private List<NhanVien> select(String sql, Object...args){
        List<NhanVien> list=new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            try {
                while(rs.next()){
                    NhanVien model = readFromResultSet(rs);
                    list.add(model);
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
     
     private NhanVien readFromResultSet(ResultSet rs) throws SQLException{
        NhanVien model=new NhanVien();
        
        model.setMaNV(rs.getString("MaNV"));
        model.setHoTen(rs.getString("HoTen"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        model.setSoDT(rs.getString("SoDT"));
        model.setMatKhau(rs.getString("MatKhau"));
        model.setNgaySinh(rs.getDate("NgaySinh"));
        model.setDiaChi(rs.getString("DiaChi"));
        model.setVaiTro(rs.getBoolean("VaiTro"));
        model.setImg(rs.getString("Img"));
        
        return model;
    }
     public NhanVien findById(String manv){
        String sql="SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> list = select(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }
     
}
