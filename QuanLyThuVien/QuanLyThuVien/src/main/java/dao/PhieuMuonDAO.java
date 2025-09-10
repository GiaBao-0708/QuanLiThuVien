package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.PhieuMuon;
import util.DBConnection;

public class PhieuMuonDAO {

    // Lấy tất cả phiếu mượn
    public List<PhieuMuon> getAll() {
        List<PhieuMuon> list = new ArrayList<>();
        String sql = "SELECT MaPhieu, MaDocGia, NgayMuon, NgayTra, TrangThai FROM PhieuMuon";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToPhieuMuon(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy phiếu mượn theo ID
    public PhieuMuon getById(int maPhieu) {
        String sql = "SELECT MaPhieu, MaDocGia, NgayMuon, NgayTra, TrangThai FROM PhieuMuon WHERE MaPhieu = ?";
        PhieuMuon pm = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maPhieu);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pm = mapResultSetToPhieuMuon(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pm;
    }

    // Thêm phiếu mượn
    public boolean insert(PhieuMuon pm) {
        String sql = "{CALL sp_ThemPhieuMuon(?, ?, ?, ?)}"; // dùng stored procedure
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pm.getMaDocGia());
            ps.setDate(2, new java.sql.Date(pm.getNgayMuon().getTime()));
            ps.setDate(3, new java.sql.Date(pm.getHanTra().getTime()));
            ps.setString(4, pm.getTrangThai());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật phiếu mượn
    public boolean update(PhieuMuon pm) {
        String sql = "{CALL sp_SuaPhieuMuon(?, ?, ?)}"; // dùng stored procedure
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pm.getMaPhieu());
            ps.setDate(2, new java.sql.Date(pm.getHanTra().getTime()));
            ps.setString(3, pm.getTrangThai());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa phiếu mượn
    public boolean delete(int maPhieu) {
        String sql = "{CALL sp_XoaPhieuMuon(?)}"; // dùng stored procedure
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maPhieu);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Hàm phụ map ResultSet → PhieuMuon
    private PhieuMuon mapResultSetToPhieuMuon(ResultSet rs) throws SQLException {
        int maPhieu = rs.getInt("MaPhieu");
        int maDocGia = rs.getInt("MaDocGia");
        Date ngayMuon = rs.getDate("NgayMuon");
        Date hanTra = rs.getDate("NgayTra");
        String trangThai = rs.getString("TrangThai");

        return new PhieuMuon(maPhieu, maDocGia, ngayMuon, hanTra, trangThai);
    }
}
