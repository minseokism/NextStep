package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import next.model.User;

public class UserDao {
    public void insert(User user) throws SQLException {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
    		@Override
    		public void setValues(PreparedStatement pstmt) throws SQLException {
    			pstmt.setString(1, user.getUserId());
    			pstmt.setString(2, user.getPassword());
    			pstmt.setString(3, user.getName());
    			pstmt.setString(4, user.getEmail());				
    			
    		}
    	};
    	
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, pss);
    }

    public void update(User user) throws SQLException {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getUserId());	
				
			}
		};
		String sql = "UPDATE USERS SET PASSWORD=?, NAME=?, EMAIL=? WHERE USERID=?";
		jdbcTemplate.update(sql, pss);
    
    }


    @SuppressWarnings("unchecked")
	public List<User> findAll() throws SQLException {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				
			}
		};

		RowMapper rowMapper = new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"), 
						rs.getString("password"), 
						rs.getString("name"),
						rs.getString("email"));
			}
		};
		
		String sql = "SELECT userId, password, name, email FROM USERS";
		return (List<User>)jdbcTemplate.query(sql, pss, rowMapper);
    }

    public User findByUserId(String userId) throws SQLException {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
		};
		
		RowMapper rowMapper = new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"), 
						rs.getString("password"), 
						rs.getString("name"),
						rs.getString("email"));
			}
		};
    	
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        return (User)jdbcTemplate.queryForObject(sql, pss, rowMapper);
    }

}
