package com.example.qlbanhang;

import java.util.Date;

public class BanHang {
    private Date ngayLapPhieu;
    private int soLuong;
    private String khachHang;
    private String nhanVien;
    private String sanPham;

    public Date getNgayLapPhieu() {
        return ngayLapPhieu;
    }

    public void setNgayLapPhieu(Date ngayLapPhieu) {
        this.ngayLapPhieu = ngayLapPhieu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }

    public String getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(String nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getSanPham() {
        return sanPham;
    }

    public void setSanPham(String sanPham) {
        this.sanPham = sanPham;
    }

    public BanHang(Date ngayLapPhieu, int soLuong, String khachHang, String nhanVien, String sanPham) {
        this.ngayLapPhieu = ngayLapPhieu;
        this.soLuong = soLuong;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.sanPham = sanPham;
    }

    public BanHang() {
    }
}
