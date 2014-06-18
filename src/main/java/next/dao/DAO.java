package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import next.support.db.ConnectionManager;

public class DAO<T> {
	private Connection getConnection() {
		return ConnectionManager.getConnection();
	}

	protected ArrayList<T> getDTORow(String sql, RowMapper<T> rm,
			Object... args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<T> row = new ArrayList<T>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				pstmt.setObject(i + 1, args[i]);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				row.add(rm.mapRow(rs));
			}
		} catch (SQLException e) {
			System.out.println("getDTORow error : " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}

	protected T getDTO(String sql, RowMapper<T> rm, Object... args)
			throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				pstmt.setObject(i + 1, args[i]);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rm.mapRow(rs);
			}
		} catch (SQLException e) {
			System.out.println("getString error : " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
}
