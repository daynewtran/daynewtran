
package com.console.dao;

import com.console.entity.KhachHang;
import com.console.helper.JdbcHelper;
import com.console.swing.TextField2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class KhachHangDAO {
    public KhachHang findById(String SoDT){
        String sql="SELECT * FROM KhachHang WHERE SoDT=?";
        List<KhachHang> list = select(sql, SoDT);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    
    public String findByID(String SoDT) throws SQLException{
        String sql = "select HoTen from KhachHang where SoDT=?";
        ResultSet x = JdbcHelper.executeQuery(sql, SoDT);
        if(x.next()){
            return x.getString(1);
        }else{
        return null;
        }        
    }
     public void insert(KhachHang model) {
        String sql = "INSERT INTO KhachHang (SoDT, HoTen, GioiTinh, Email, DiaChi, SoCap, GhiChu) VALUES (?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getSDT(),
                model.getTenKH(),
                model.isGioiTinh(),
                model.getEmail(),
                model.getDiaChi(),
                model.getSoCap(),
                model.getGhiChu());
    }

    public void update(KhachHang model) {
        String sql = "UPDATE KhachHang SET HoTen=?, GioiTinh=?, Email=?, DiaChi=?, SoCap=?, GhiChu=?  WHERE SoDT=?";
        JdbcHelper.executeUpdate(sql,
                model.getTenKH(),
                model.isGioiTinh(),
                model.getEmail(),
                model.getDiaChi(),
                model.getSoCap(),
                model.getGhiChu(),
                model.getSDT());
    }
    public void updateCB(int CapBac, String SoDT) {
        String sql = "UPDATE KhachHang SET SoCap=? WHERE SoDT=?";
        JdbcHelper.executeUpdate(sql,
                CapBac,
                SoDT
                );
    }

    public void delete(String SDT) {
        String sql = "DELETE FROM KhachHang WHERE SoDT=?";
        JdbcHelper.executeUpdate(sql, SDT);
    }
    public List<KhachHang> select(){
        String sql="SELECT * FROM KhachHang";
        return select(sql);
    }
    public List<KhachHang> findByKeyword(String key){
        String sql="select * from KhachHang where HoTen like ?";
        return select(sql, "%"+key+"%");
        
    }
     private List<KhachHang> select(String sql, Object...args){
        List<KhachHang> list=new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            try {
                while(rs.next()){
                    KhachHang model = readFromResultSet(rs);
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
     private KhachHang readFromResultSet(ResultSet rs) throws SQLException{
        KhachHang model=new KhachHang();
        
        model.setTenKH(rs.getString("HoTen"));
        model.setSDT(rs.getString("SoDT"));        
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        model.setEmail(rs.getString("Email"));
        model.setDiaChi(rs.getString("DiaChi"));
        model.setSoCap(rs.getInt("SoCap"));
        model.setGhiChu(rs.getString("GhiChu"));
        
        return model;
    }

    

    
}
