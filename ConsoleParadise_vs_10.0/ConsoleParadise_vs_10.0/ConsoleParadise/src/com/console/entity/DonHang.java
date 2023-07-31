
package com.console.entity;

import java.util.Date;

/**
 *
 * @author ASUS-PC
 */
public class DonHang {
    int MaDonHang ; 
    double TongTien ;
    String MaNV;
    Date NgayTaoDon;
    String SDT ; 
    String GhiChu;

    public DonHang() {
    }

    public DonHang(int MaDonHang, double TongTien, String MaNV, Date NgayTaoDon, String SDT, String GhiChu) {
        this.MaDonHang = MaDonHang;
        this.TongTien = TongTien;
        this.MaNV = MaNV;
        this.NgayTaoDon = NgayTaoDon;
        this.SDT = SDT;
        this.GhiChu = GhiChu;
    }

    public int getMaDonHang() {
        return MaDonHang;
    }

    public void setMaDonHang(int MaDonHang) {
        this.MaDonHang = MaDonHang;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public Date getNgayTaoDon() {
        return NgayTaoDon;
    }

    public void setNgayTaoDon(Date NgayTaoDon) {
        this.NgayTaoDon = NgayTaoDon;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
}
