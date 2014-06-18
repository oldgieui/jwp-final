package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import next.model.Question;
import next.support.db.ConnectionManager;

public class QuestionDao extends DAO<Question> {

	public void insert(Question question) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfComment) VALUES (?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, question.getWriter());
			pstmt.setString(2, question.getTitle());
			pstmt.setString(3, question.getContents());
			pstmt.setTimestamp(4,
					new Timestamp(question.getTimeFromCreateDate()));
			pstmt.setInt(5, question.getCountOfComment());

			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (con != null) {
				con.close();
			}
		}
	}
	
	public void updateCountOfComment(long questionId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "UPDATE QUESTIONS SET countOfComment = countOfComment+1 WHERE questionId =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, questionId);
			
			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (con != null) {
				con.close();
			}
		}
	}

	public List<Question> findAll() throws SQLException {

		String sql = "SELECT questionId, writer, title, createdDate, countOfComment FROM QUESTIONS "
				+ "order by questionId desc";
		RowMapper<Question> rm = new RowMapper<Question>() {

			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"),
						rs.getString("writer"), rs.getString("title"), null,
						rs.getTimestamp("createdDate"),
						rs.getInt("countOfComment"));
			}
		};

		return getDTORow(sql, rm);
	}

	public Question findById(long questionId) throws SQLException {
		String sql = "SELECT questionId, writer, title, contents, createdDate, countOfComment FROM QUESTIONS "
				+ "WHERE questionId = ?";
		RowMapper<Question> rm = new RowMapper<Question>() {

			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"),
						rs.getString("writer"), rs.getString("title"),
						rs.getString("contents"),
						rs.getTimestamp("createdDate"),
						rs.getInt("countOfComment"));
			}
		};

		return getDTO(sql, rm, questionId);
	}
}
