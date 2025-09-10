package model;

public class ChiTietMuon {
    private int maPhieu;
    private int maSach;
    private int soLuong;
    private String tinhTrang;

    public ChiTietMuon() {}

    public ChiTietMuon(int maPhieu, int maSach, int soLuong, String tinhTrang) {
        this.maPhieu = maPhieu;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.tinhTrang = tinhTrang;
    }

    public ChiTietMuon(int aInt, int aInt0, int aInt1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters và setters
    public int getMaPhieu() { return maPhieu; }
    public void setMaPhieu(int maPhieu) { this.maPhieu = maPhieu; }

    public int getMaSach() { return maSach; }
    public void setMaSach(int maSach) { this.maSach = maSach; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public String getTinhTrang() { return tinhTrang; }
    public void setTinhTrang(String tinhTrang) { this.tinhTrang = tinhTrang; }

    @Override
    public String toString() {
        return "Phiếu " + maPhieu + " - Sách " + maSach + " (" + soLuong + ") - " + tinhTrang;
    }
}
