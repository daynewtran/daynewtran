
package com.console.dao;

import com.console.entity.CapBac;
import com.console.entity.LoaiSanPham;
import com.console.helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CapBacDAO {
    public List<CapBac> select(){
        String sql = "select * from CapBac";
        return select(sql);
    }
    
    public List<CapBac> select(String sql, Object...args){
        List<CapBac> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            try {
                while (rs.next()) {
                    CapBac model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally{
                rs.getStatement().getConnection().close();
            }
            
        } catch (Exception e) {
        }
        return list;
        
    }
    private CapBac readFromResultSet(ResultSet rs) throws SQLException{
        CapBac model=new CapBac();
        
        model.setSoCapBac(rs.getInt("SoCap"));
        model.setGhiChu(rs.getString("GhiChu"));
        
        return model;
    }
    
}
