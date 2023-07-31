
package com.console.dao;

import com.console.entity.NhanVien;
import com.console.entity.SanPham;
import com.console.helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class SanPhamDAO {
    public List<SanPham> selectByKeyword(String keyword){
        String sql="SELECT * FROM SanPham WHERE TenSP LIKE ?";
        return select(sql, "%"+keyword+"%");
    }

    public void insert(SanPham model) {
        String sql = "INSERT INTO SanPham (MaSP, TenSP, GiaBan, SoLuong, HangSanXuat, maLoai, GhiChu, Img) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaSP(),
                model.getTenSP(),
                model.getGiaBan(),
                model.getSoLuong(),
                model.getHangSanXuat(),
                model.getMaLoai(),
                model.getGhiChu(),
                model.getImg());
    }

    public void update(SanPham model) {
        String sql = "UPDATE SanPham SET TenSP=?, GiaBan=?, SoLuong=?, HangSanXuat=?, maLoai=?, GhiChu=?, Img=?  WHERE MaSP=?";
        JdbcHelper.executeUpdate(sql,
                model.getTenSP(),
                model.getGiaBan(),
                model.getSoLuong(),
                model.getHangSanXuat(),
                model.getMaLoai(),
                model.getGhiChu(),
                model.getImg(),
                model.getMaSP());
    }
    public void update2(String maSP){
        String sql ="UPDATE SanPham SET  GhiChu=N'Ngưng kinh doanh'  WHERE MaSP=?";
        JdbcHelper.executeUpdate(sql,
                maSP);
    }
    public void update3(int SoLuong, String maSP){
        String sql ="UPDATE SanPham SET  SoLuong=?  WHERE MaSP=?";
        JdbcHelper.executeUpdate(sql,
                SoLuong,
                maSP);
    }

    public void delete(String MaSP) {
        String sql = "DELETE FROM SanPham WHERE MaSP=?";
        JdbcHelper.executeUpdate(sql, MaSP);
    }
    public List<SanPham> select(){
        String sql="SELECT * FROM SanPham";
        return select(sql);
    }
    public List<SanPham> select2(){
        String sql="SELECT * FROM sanPham WHERE GhiChu NOT IN ( N'Ngưng kinh doanh' );";
        return select(sql);
    }
     private List<SanPham> select(String sql, Object...args){
        List<SanPham> list=new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            try {
                while(rs.next()){
                    SanPham model = readFromResultSet(rs);
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
     private SanPham readFromResultSet(ResultSet rs) throws SQLException{
        SanPham model=new SanPham();
        model.setMaSP(rs.getString("MaSP"));
        model.setTenSP(rs.getString("TenSP"));
        model.setGiaBan(rs.getDouble("GiaBan"));
        model.setSoLuong(rs.getInt("SoLuong"));
        model.setHangSanXuat(rs.getString("HangSanXuat"));
        model.setMaLoai(rs.getString("MaLoai"));
        model.setGhiChu(rs.getString("GhiChu"));
        model.setImg(rs.getString("Img"));
        return model;
    }
     public SanPham findById(String masp){
        String sql="SELECT * FROM SanPham WHERE MaSP=?";
        List<SanPham> list = select(sql, masp);
        return list.size() > 0 ? list.get(0) : null;
    }
}
