package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class SelectJdbcTemplate {
	@SuppressWarnings("rawtypes")
	public List query(String sql) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			setValues(pstmt);

			rs = pstmt.executeQuery();
			
			List<Object> result = new ArrayList<Object>();
			while (rs.next()) {
				result.add(mapRow(rs));
			}

			return result;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Object queryForObject(String sql) throws SQLException {
		List result = query(sql);
		if (result.isEmpty()) {
			return null;
		} 
		
		return result.get(0);
	}
	
	public abstract void setValues(PreparedStatement pstmt) throws SQLException;
	
	public abstract Object mapRow(ResultSet rs) throws SQLException; 
}
