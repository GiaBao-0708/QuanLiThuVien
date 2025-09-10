package model;

import java.util.Date;

public class PhieuMuon {
    private int maPhieu;      // MaPhieu trong DB
    private int maDocGia;     // MaDocGia trong DB
    private Date ngayMuon;    // Ngày mượn
    private Date hanTra;      // Hạn trả
    private String trangThai; // Trạng thái

    // Constructor không tham số
    public PhieuMuon() {}

    // Constructor đầy đủ
    public PhieuMuon(int maPhieu, int maDocGia, Date ngayMuon, Date hanTra, String trangThai) {
        this.maPhieu = maPhieu;
        this.maDocGia = maDocGia;
        this.ngayMuon = ngayMuon;
        this.hanTra = hanTra;
        this.trangThai = trangThai;
    }

    // Constructor thêm mới (không có maPhieu)
    public PhieuMuon(int maDocGia, Date ngayMuon, Date hanTra, String trangThai) {
        this.maDocGia = maDocGia;
        this.ngayMuon = ngayMuon;
        this.hanTra = hanTra;
        this.trangThai = trangThai;
    }

    // Getters & Setters
    public int getMaPhieu() { return maPhieu; }
    public void setMaPhieu(int maPhieu) { this.maPhieu = maPhieu; }

    public int getMaDocGia() { return maDocGia; }
    public void setMaDocGia(int maDocGia) { this.maDocGia = maDocGia; }

    public Date getNgayMuon() { return ngayMuon; }
    public void setNgayMuon(Date ngayMuon) { this.ngayMuon = ngayMuon; }

    public Date getHanTra() { return hanTra; }
    public void setHanTra(Date hanTra) { this.hanTra = hanTra; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    @Override
    public String toString() {
        return "Phiếu: " + maPhieu + " - Độc giả: " + maDocGia + " - Trạng thái: " + trangThai;
    }
}
