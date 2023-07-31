
package com.console.dao;

import com.console.entity.DonHangCT;
import com.console.entity.KhachHang;
import com.console.entity.NhanVien;
import com.console.helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonHangChiTietDAO {
    public void delete(int maDH){
        String sql = "delete from DONHANGCHITIET where MaDonHang=?";
        JdbcHelper.executeUpdate(sql, maDH);
    }
    public List<DonHangCT> select(){
        String sql="SELECT * FROM DONHANGCHITIET";
        return select(sql);
    }
    public List<DonHangCT> findById(int maDH){
        String sql="SELECT * FROM DONHANGCHITIET WHERE MaDonHang=?";
        
        return select(sql, maDH);
        
    }
    public List<DonHangCT> selectByKeyword(int keyword){
        String sql="SELECT * FROM DONHANGCHITIET WHERE MaDonHang = ?";
        return select(sql, keyword);
    }
    public void insert(DonHangCT model) {
        String sql = "INSERT INTO DonHangChiTiet (maDonHang, MaSP, SoLuong, ThanhTien) VALUES (?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaDH(),
                model.getMaSP(),
                model.getSoLuong(),
                model.getThanhTien()
                );
    }
     private List<DonHangCT> select(String sql, Object...args){
        List<DonHangCT> list=new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            try {
                while(rs.next()){
                    DonHangCT model = readFromResultSet(rs);
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
      private DonHangCT readFromResultSet(ResultSet rs) throws SQLException{
        DonHangCT model=new DonHangCT();
        model.setMaDH(rs.getInt("MaDonHang"));
        model.setMaSP(rs.getString("MaSP"));
        model.setSoLuong(rs.getInt("SoLuong"));
        model.setThanhTien(rs.getDouble("ThanhTien"));
      
        
        return model;
    }
    
}
