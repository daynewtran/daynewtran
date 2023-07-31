/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.console.dao;

import com.console.entity.LuuThongTin;
import com.console.helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class LuuThongTinDAO {
    public void XoaTT(){
        String sql="delete from LuuThongTin";
        JdbcHelper.executeUpdate(sql);
    }
    public void LuuTT(String maNV, String mk){
        String sql="Insert into LuuThongTin(MaNV, MatKhau) VALUES(?, ?)";
        JdbcHelper.executeUpdate(sql,maNV, mk);
    }
    public List<LuuThongTin> select(){
        String sql="select * from LuuThongTin";
        return select(sql);
    }
    public LuuThongTin findById(){
        String sql="SELECT * FROM LuuThongTin ";
        List<LuuThongTin> list = select(sql);
        return list.size() > 0 ? list.get(0) : null;
    }
    private List<LuuThongTin> select(String sql, Object...args){
        List<LuuThongTin> list=new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            try {
                while(rs.next()){
                    LuuThongTin model = readFromResultSet(rs);
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
     
     private LuuThongTin readFromResultSet(ResultSet rs) throws SQLException{
        LuuThongTin model=new LuuThongTin();
        model.setMaNV(rs.getString("MaNV"));
        model.setMatKhau(rs.getString("MatKhau"));
//        model.setMaNV(rs.getString("MaNV"));
//        model.setHoTen(rs.getString("HoTen"));
//        model.setGioiTinh(rs.getBoolean("GioiTinh"));
//        model.setSoDT(rs.getString("SoDT"));
//        model.setMatKhau(rs.getString("MatKhau"));
//        model.setNgaySinh(rs.getDate("NgaySinh"));
//        model.setDiaChi(rs.getString("DiaChi"));
//        model.setVaiTro(rs.getBoolean("VaiTro"));
//        model.setImg(rs.getString("Img"));
        
        return model;
    }
}
