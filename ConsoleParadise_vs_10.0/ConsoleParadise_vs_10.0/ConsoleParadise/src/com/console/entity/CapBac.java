/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.console.entity;

/**
 *
 * @author ASUS-PC
 */
public class CapBac {
    int SoCapBac;
    String GhiChu;

    public CapBac() {
    }

    public CapBac(int SoCapBac, String GhiChu) {
        this.SoCapBac = SoCapBac;
        this.GhiChu = GhiChu;
    }

    public int getSoCapBac() {
        return SoCapBac;
    }

    public void setSoCapBac(int SoCapBac) {
        this.SoCapBac = SoCapBac;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    public String toString(){
        return String.valueOf(this.SoCapBac);
    }
    
    
}
