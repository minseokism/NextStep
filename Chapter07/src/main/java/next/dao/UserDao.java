package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.model.User;

public class UserDao {
    public void insert(User user) throws SQLException {
    	InsertJdbcTemplate jdbcTemplate = new InsertJdbcTemplate(); 
		jdbcTemplate.insert(user, this);
    }

	void setValuesForInsert(User user, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, user.getUserId());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
	}

	String createQueryForInsert() {
		return "INSERT INTO USERS VALUES (?, ?, ?, ?)";
	}

    public void update(User user) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = createQueryForUpdate();
            pstmt = con.prepareStatement(sql);
            setValuesForUpdate(user, pstmt);

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

	private void setValuesForUpdate(User user, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, user.getPassword());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getEmail());
		pstmt.setString(4, user.getUserId());
	}

	private String createQueryForUpdate() {
		String sql = "UPDATE USERS SET PASSWORD=?, NAME=?, EMAIL=? WHERE USERID=?";
		return sql;
	}

    public List<User> findAll() throws SQLException {
    	List<User> users = new ArrayList<>();
    	Connection con = null;
    	Statement stmt = null;
    	ResultSet rs = null;
    	
    	try {
    		con = ConnectionManager.getConnection();
    		String sql = "SELECT userId, password, name, email FROM USERS";
    		stmt = con.createStatement();
    		rs = stmt.executeQuery(sql);

    		if (rs.next()) {
    			users.add( new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email")));
    		}
    		
    	} finally {
    	    if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
		}
    	
    	return users;
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
