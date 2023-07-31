
package com.console.dao;
import com.console.helper.DateHelper;
import com.console.helper.JdbcHelper;
import com.console.main.ModelData;
import com.console.main.ModelData1;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThongKeDAO {
    public List<Object[]> getTKTN (){
        List<Object[]> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql="{call sp_ThongKeTheoNgay}";
                rs = JdbcHelper.executeQuery(sql);
                while(rs.next()){
                    DecimalFormat f = new DecimalFormat("###,###,###");
                    
                    Object[] model={
                        rs.getString("TenSp"),
                        rs.getInt("SoLuong"),
                        f.format(rs.getDouble("TongTien")).replace(',', '.'),
                        rs.getString("NgayTaoDon"),
                    };
                    list.add(model);
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public List<ModelData> bieuDo (){
        List<ModelData> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql="{call sp_BieuDo}";
                rs = JdbcHelper.executeQuery(sql);
                while(rs.next()){
                    String month = DateHelper.toString(DateHelper.toDate(DateHelper.toString(rs.getDate("ngayTaoDon"))));
                    double count = Double.parseDouble(String.valueOf(rs.getInt("tongTien")));
                    double tongTien = rs.getDouble("tongTien");
                    list.add(new ModelData(month, count, tongTien));
                    
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public List<ModelData1> bieuDoThang (){
        List<ModelData1> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql="{call sp_BieuDoThang}";
                rs = JdbcHelper.executeQuery(sql);
                while(rs.next()){
                    String month = String.valueOf(rs.getInt("thang"))+"-"+String.valueOf(rs.getInt("nam"));
                    double count = Double.parseDouble(String.valueOf(rs.getInt("tongTien")));
                    double tongTien = rs.getDouble("tongTien");
                    list.add(new ModelData1(month, count, tongTien));
                    
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public List<Object[]> getTKTT(String thang){
        List<Object[]> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql="{call sp_ThongKeTheoThang (?)}";
                rs = JdbcHelper.executeQuery(sql, thang);
                while(rs.next()){
                    DecimalFormat f = new DecimalFormat("###,###,###");
                    String x = rs.getInt("Thang")+"-"+rs.getInt("Nam");
                    Object[] model={
                        
                        rs.getString("TenSP"),
                        rs.getInt("SoLuong"),
                        f.format(rs.getInt("TongTien")).replace(',', '.'),
                        x,
                    };
                    list.add(model);
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public List<Object[]> getTKNV (){
        List<Object[]> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql="{call sp_ThongKeTheoNhanVien}";
                rs = JdbcHelper.executeQuery(sql);
                while(rs.next()){
                    
                    Object[] model={
                        rs.getString("HoTen"),
                        rs.getString("TenSP"),
                        rs.getInt("SoLuong"),
                        rs.getString("NgayTaoDon"),
                    };
                    list.add(model);
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
