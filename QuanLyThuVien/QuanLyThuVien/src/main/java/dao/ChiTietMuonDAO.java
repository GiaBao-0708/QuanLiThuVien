package dao;

import model.ChiTietMuon;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTietMuonDAO {
    private Connection conn;

    public ChiTietMuonDAO() {
        this.conn = DBConnection.getConnection();
    }

    // Lấy toàn bộ chi tiết mượn
    public List<ChiTietMuon> getAll() throws SQLException {
        List<ChiTietMuon> list = new ArrayList<>();
        String sql = "EXEC sp_XemChiTietMuon";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ChiTietMuon ct = new ChiTietMuon(
                        rs.getInt("MaPhieu"),
                        rs.getInt("MaSach"),
                        rs.getInt("SoLuong"),
                        rs.getString("TinhTrang")
                );
                list.add(ct);
            }
        }
        return list;
    }

    // Thêm chi tiết mượn
    public boolean insert(ChiTietMuon ct) throws SQLException {
        String sql = "EXEC sp_ThemChiTietMuon ?,?,?,?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ct.getMaPhieu());
            ps.setInt(2, ct.getMaSach());
            ps.setInt(3, ct.getSoLuong());
            ps.setString(4, ct.getTinhTrang());
            return ps.executeUpdate() > 0;
        }
    }

    // Xóa chi tiết mượn
    public boolean delete(int maPhieu, int maSach) throws SQLException {
        String sql = "DELETE FROM ChiTietMuon WHERE MaPhieu=? AND MaSach=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPhieu);
            ps.setInt(2, maSach);
            return ps.executeUpdate() > 0;
        }
    }
}
