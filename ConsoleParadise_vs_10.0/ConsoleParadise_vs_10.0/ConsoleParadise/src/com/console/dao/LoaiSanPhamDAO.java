
package com.console.dao;

import com.console.entity.LoaiSanPham;
import com.console.entity.NhanVien;
import com.console.helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoaiSanPhamDAO {
    
    public LoaiSanPham findByID(String maLoai) throws SQLException{
        String sql = "select * from LoaiSanPham where maLoai=?";
        List<LoaiSanPham> list = select(sql, maLoai);
        return list.size() > 0 ? list.get(0) : null;
    }
    public List<LoaiSanPham> select(){
        String sql = "select * from LoaiSanPham";
        return select(sql);
    }
    public List<LoaiSanPham> selectTenLoai(){
        String sql = "select MaLoai from LoaiSanPham";
        return select(sql);
    }
    
    public List<LoaiSanPham> select(String sql, Object...args){
        List<LoaiSanPham> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            try {
                while (rs.next()) {
                    LoaiSanPham model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally{
                rs.getStatement().getConnection().close();
            }
            
        } catch (Exception e) {
        }
        return list;
        
    }
    private LoaiSanPham readFromResultSet(ResultSet rs) throws SQLException{
        LoaiSanPham model=new LoaiSanPham();
        
        model.setMaLoai(rs.getString("MaLoai"));
        model.setTenLoai(rs.getString("TenLoai"));
        
        return model;
    }
}
