
package com.console.dao;

import com.console.entity.DonHang;
import com.console.entity.DonHangCT;
import com.console.helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DonHangDAO {
   public void delete(int MaDonHang) {
        String sql = "DELETE FROM DONHANG WHERE MaDonHang=?";
        JdbcHelper.executeUpdate(sql, MaDonHang);
    }
    public DonHang max(){
        String sql="select * from DONHANG\n" +
        "where MaDonHang>=(select Max(MaDonHang) from DONHANG)\n";
        List<DonHang> list = select(sql);
        return list.size() > 0 ? list.get(0) : null;
    }
    public double TongTien(String SoDT) throws SQLException{
        String sql = "select sum(TongTien) from DONHANG where SoDt=?";
        ResultSet x = JdbcHelper.executeQuery(sql, SoDT);
        if(x.next()){
            return x.getDouble(1);
        }  else{
            return 0;
        }        
    }
    
    public void insert(DonHang model) {
        String sql = "INSERT INTO DONHANG ( TongTien, MaNV, NgayTaoDon, SoDT, GhiChu) VALUES ( ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getTongTien(),
                model.getMaNV(),
                model.getNgayTaoDon(),
                model.getSDT(),
                model.getGhiChu());
    }
    
    public void delete(String MaDonHang) {
        String sql = "DELETE FROM DONHANG WHERE MaDonHang=?";
        JdbcHelper.executeUpdate(sql, MaDonHang);
    }
    public List<DonHang> select(){
        String sql="SELECT * FROM DONHANG";
        return select(sql);
    }
     private List<DonHang> select(String sql, Object...args){
        List<DonHang> list=new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            try {
                while(rs.next()){
                    DonHang model = readFromResultSet(rs);
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
     private DonHang readFromResultSet(ResultSet rs) throws SQLException{
        DonHang model=new DonHang();
        model.setMaDonHang(rs.getInt("MaDonHang"));
        model.setTongTien(rs.getDouble("TongTien"));
        model.setMaNV(rs.getString("MaNV"));
        model.setNgayTaoDon(rs.getDate("NgayTaoDon"));
        model.setSDT(rs.getString("SoDT"));
        model.setGhiChu(rs.getString("GhiChu"));
        return model;
    }
     public DonHang findById(int maDH){
        String sql="SELECT * FROM DONHANG WHERE MaDonHang=?";
        List<DonHang> list = select(sql, maDH);
        return list.size() > 0 ? list.get(0) : null;
    }
}
