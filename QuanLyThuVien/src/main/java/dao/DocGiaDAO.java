package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DocGia;
import util.DBConnection;

public class DocGiaDAO {

    public List<DocGia> getAll() {
        List<DocGia> list = new ArrayList<>();
        String sql = "SELECT MaDocGia, TenDocGia, NgaySinh, DiaChi, SoDienThoai FROM DocGia";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DocGia dg = new DocGia(
                    rs.getString("MaDocGia"),
                    rs.getString("TenDocGia"),
                    rs.getDate("NgaySinh"),
                    rs.getString("DiaChi"),
                    rs.getString("SoDienThoai")
                );
                list.add(dg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public DocGia getById(String maDocGia) {
        String sql = "SELECT MaDocGia, TenDocGia, NgaySinh, DiaChi, SoDienThoai FROM DocGia WHERE MaDocGia = ?";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maDocGia);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new DocGia(
                        rs.getString("MaDocGia"),
                        rs.getString("TenDocGia"),
                        rs.getDate("NgaySinh"),
                        rs.getString("DiaChi"),
                        rs.getString("SoDienThoai")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(DocGia dg) {
        String sql = "INSERT INTO DocGia(TenDocGia, NgaySinh, DiaChi, SoDienThoai) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dg.getTenDocGia());
            ps.setDate(2, new java.sql.Date(dg.getNgaySinh().getTime()));
            ps.setString(3, dg.getDiaChi());
            ps.setString(4, dg.getSoDienThoai());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String maDocGia) {
        String sql = "DELETE FROM DocGia WHERE MaDocGia = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maDocGia);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}