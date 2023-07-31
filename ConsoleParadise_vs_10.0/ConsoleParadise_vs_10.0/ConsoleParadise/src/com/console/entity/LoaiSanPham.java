/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.console.entity;

/**
 *
 * @author ASUS-PC
 */
public class LoaiSanPham {
    String maLoai ; 
    String TenLoai ; 

    public LoaiSanPham() {
    }
    public String toString(){
        return this.maLoai;
    }
    public LoaiSanPham(String maLoai, String TenLoai) {
        this.maLoai = maLoai;
        this.TenLoai = TenLoai;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }
    
    
}
