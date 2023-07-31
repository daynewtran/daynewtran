/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.console.dao;

import com.console.entity.ChamCong;
import com.console.helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ChamCongDAO {
    public List<ChamCong> findByKeyword(String key){
        String sql="select b.MaNv, SoGioLam, Luong, LuongThuong, LuongPhat, TongLuong,thang,GhiChu  from chamCong a\n" +
"join nhanVien b on a.MaNv=b.MaNv\n" +
"where b.HoTen like ?";
        return select(sql, "%"+key+"%");
        
    }
    public void insert(ChamCong model){
        String sql ="Insert into ChamCong (MaNV , SoGioLam ,Luong,LuongThuong, LuongPhat, TongLuong, thang , GhiChu) Values (?,?,?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql, 
                model.getMaNv(),
                model.getSoGioLam(),
                model.getLuong(),
                model.getLuongThuong(),
                model.getLuongPhat(),
                model.getTongLuong(),
                model.getThang(),
                model.getGhiChu()
                );
    }
    public void update(ChamCong model) {
        String sql = "UPDATE ChamCong SET SoGioLam =?,Luong=?,LuongThuong=?, LuongPhat=?, TongLuong=?, thang=?, GhiChu=?  WHERE MaNv=?";
        JdbcHelper.executeUpdate(sql,
                
                 model.getSoGioLam(),
               model.getLuong(),              
               model.getLuongThuong(),
               model.getLuongPhat(),
               model.getTongLuong(),
               model.getThang(),
               model.getGhiChu(),
               model.getMaNv());
    }
    public List<ChamCong> select(){
        String sql="SELECT * FROM ChamCong";
        return select(sql);
    }
    public List<ChamCong> select2(){
        String sql ="select * from chamCong\n" +
"where MONTH(GETDATE())=SUBSTRING(thang, 1, len(thang)-5) and year(GETDATE())=SUBSTRING(thang, LEN(thang)-3, 4) ;";
        return select(sql);
    }
    public ChamCong select3(String maNV){
        String sql ="select * from chamCong\n" +
"where MONTH(GETDATE())=SUBSTRING(thang, 1, len(thang)-5) and year(GETDATE())=SUBSTRING(thang, LEN(thang)-3, 4) and maNV= ? ;";
        List<ChamCong> list = select(sql, maNV);
        return list.size() > 0 ? list.get(0) : null;
    }
    
     private List<ChamCong> select(String sql, Object...args){
        List<ChamCong> list=new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            try {
                while(rs.next()){
                    ChamCong model = readFromResultSet(rs);
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
     private ChamCong readFromResultSet(ResultSet rs) throws SQLException{
        ChamCong model=new ChamCong();
        model.setMaNv(rs.getString("MaNv"));
        model.setSoGioLam(rs.getInt("SoGioLam"));
        model.setLuong(rs.getDouble("Luong"));
        model.setLuongThuong(rs.getDouble("LuongThuong"));
        model.setLuongPhat(rs.getDouble("LuongPhat"));
        model.setTongLuong(rs.getDouble("TongLuong"));
        model.setThang(rs.getString("thang"));
        model.setGhiChu(rs.getString("GhiChu"));
        return model;
    }
     public ChamCong findById(String manv){
        String sql="SELECT * FROM ChamCong WHERE MaNv=?";
        List<ChamCong> list = select(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }

}
