package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import core.jdbc.JdbcTemplate;
import core.jdbc.SelectJdbcTemplate;
import next.model.User;

public class UserDao {
    public void insert(User user) throws SQLException {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());				
			}
		};
		
		
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql);
    }

    public void update(User user) throws SQLException {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
	   			pstmt.setString(1, user.getPassword());
    			pstmt.setString(2, user.getName());
    			pstmt.setString(3, user.getEmail());
    			pstmt.setString(4, user.getUserId());	
			}
		};
    	
		String sql = "UPDATE USERS SET PASSWORD=?, NAME=?, EMAIL=? WHERE USERID=?";
		jdbcTemplate.update(sql);
    
    }


    @SuppressWarnings("unchecked")
	public List<User> findAll() throws SQLException {
    	SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
			}
			
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"), 
						rs.getString("password"), 
						rs.getString("name"),
                        rs.getString("email"));
			}
		};
		
		String sql = "SELECT userId, password, name, email FROM USERS";
		return (List<User>)jdbcTemplate.query(sql);
    }

    public User findByUserId(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
            }

            return user;
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

}
