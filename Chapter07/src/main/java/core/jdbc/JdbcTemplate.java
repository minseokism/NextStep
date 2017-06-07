package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public void update(String sql, PreparedStatementSetter pss) throws DataAccessException {
        try (Connection con = ConnectionManager.getConnection();
        	PreparedStatement pstmt = con.prepareStatement(sql)){
            pss.setValues(pstmt);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
        	throw new DataAccessException();
        }
    }
    
	public <T> List<T> query(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper) throws DataAccessException  {
		ResultSet rs = null;

		try (Connection con = ConnectionManager.getConnection();
            	PreparedStatement pstmt = con.prepareStatement(sql)){

			pss.setValues(pstmt);

			rs = pstmt.executeQuery();
			
			List<T> result = new ArrayList<T>();

			while (rs.next()) {
				result.add(rowMapper.mapRow(rs));
			}

			return result;
		} catch (SQLException e) {
			throw new DataAccessException();
		} 
	}
	
	public <T> T queryForObject(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper) throws SQLException {
		List<T> result = query(sql, pss, rowMapper);
		if (result.isEmpty()) {
			return null;
		} 
		
		return result.get(0);
	}
	


    
}
