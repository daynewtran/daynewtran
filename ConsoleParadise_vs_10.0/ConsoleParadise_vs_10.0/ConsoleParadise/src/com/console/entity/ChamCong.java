
package com.console.entity;

import java.util.Date;

/**
 *
 * @author ASUS-PC
 */
public class ChamCong {
        String MaNv ;
        int SoGioLam;
	double Luong ;
	double LuongThuong;
	double LuongPhat ;
	double TongLuong;
        String thang;
	String GhiChu ;

    public ChamCong() {
    }

    public ChamCong(String MaNv, int SoGioLam, double Luong, double LuongThuong, double LuongPhat, double TongLuong, String thang, String GhiChu) {
        this.MaNv = MaNv;
        this.SoGioLam = SoGioLam;
        this.Luong = Luong;
        this.LuongThuong = LuongThuong;
        this.LuongPhat = LuongPhat;
        this.TongLuong = TongLuong;
        this.thang = thang;
        this.GhiChu = GhiChu;
    }

    public String getMaNv() {
        return MaNv;
    }

    public void setMaNv(String MaNv) {
        this.MaNv = MaNv;
    }

    public int getSoGioLam() {
        return SoGioLam;
    }

    public void setSoGioLam(int SoGioLam) {
        this.SoGioLam = SoGioLam;
    }

    public double getLuong() {
        return Luong;
    }

    public void setLuong(double Luong) {
        this.Luong = Luong;
    }

    public double getLuongThuong() {
        return LuongThuong;
    }

    public void setLuongThuong(double LuongThuong) {
        this.LuongThuong = LuongThuong;
    }

    public double getLuongPhat() {
        return LuongPhat;
    }

    public void setLuongPhat(double LuongPhat) {
        this.LuongPhat = LuongPhat;
    }

    public double getTongLuong() {
        return TongLuong;
    }

    public void setTongLuong(double TongLuong) {
        this.TongLuong = TongLuong;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    
    


}
