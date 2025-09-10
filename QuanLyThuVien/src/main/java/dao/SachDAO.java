package dao;

import model.Sach;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private Connection conn;

    public SachDAO() {
        this.conn = DBConnection.getConnection();
    }

    public List<Sach> getAll() throws SQLException {
        List<Sach> list = new ArrayList<>();
        String sql = "EXEC sp_XemSach";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Sach s = new Sach(
                        rs.getInt("MaSach"),
                        rs.getString("TenSach"),
                        rs.getString("TacGia"),
                        rs.getInt("NamXuatBan"),
                        rs.getString("TheLoai"),
                        rs.getInt("SoLuong"),
                        rs.getBytes("AnhBia")
                );
                list.add(s);
            }
        }
        return list;
    }

    public boolean insert(Sach s) throws SQLException {
        String sql = "EXEC sp_ThemSach ?,?,?,?,?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getTenSach());
            ps.setString(2, s.getTacGia());
            ps.setInt(3, s.getNamXuatBan());
            ps.setString(4, s.getTheLoai());
            ps.setInt(5, s.getSoLuong());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean update(Sach s) throws SQLException {
        String sql = "EXEC sp_SuaSach ?,?,?,?,?,?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, s.getMaSach());
            ps.setString(2, s.getTenSach());
            ps.setString(3, s.getTacGia());
            ps.setInt(4, s.getNamXuatBan());
            ps.setString(5, s.getTheLoai());
            ps.setInt(6, s.getSoLuong());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int maSach) throws SQLException {
        String sql = "EXEC sp_XoaSach ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSach);
            return ps.executeUpdate() > 0;
        }
    }
}
