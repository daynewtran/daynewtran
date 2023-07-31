
package com.console.entity;


public class SanPham {
    String MaSP;
    String TenSP;
    double GiaBan;
    int SoLuong;
    String HangSanXuat;
    String MaLoai;
    String GhiChu;
    String img;

    public SanPham() {
    }
    @Override
    public String toString(){
        return this.MaSP+"("+this.TenSP+")";
    }

    public SanPham(String MaSP, String TenSP, Double GiaBan, int SoLuong, String HangSanXuat, String MaLoai, String GhiChu, String img) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.GiaBan = GiaBan;
        this.SoLuong = SoLuong;
        this.HangSanXuat = HangSanXuat;
        this.MaLoai = MaLoai;
        this.GhiChu = GhiChu;
        this.img = img;
    }
    
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public double getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(Double GiaBan) {
        this.GiaBan = GiaBan;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getHangSanXuat() {
        return HangSanXuat;
    }

    public void setHangSanXuat(String HangSanXuat) {
        this.HangSanXuat = HangSanXuat;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
    
}
