package com.ssafy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.model.UserDto;
import com.ssafy.util.DBUtil;


public class UserDao {

	private DBUtil db;
	
	public UserDao() {
		db = DBUtil.getInstance();
	}
	
	public int idCheck(String id) {
		int cnt = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select count(id) \n");
			loginMember.append("from user \n");
			loginMember.append("where id = ?");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			cnt = 1;
		} finally {
			db.close(rs, pstmt, conn);
		}
		return cnt;
	}
	public void register(UserDto user) {
		int count = 0;
		String sql = " insert into user(id,password,name,email,address) values(?,?,?,?,?) ";
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = db.getConnection();
			System.out.println("addUser 2/6 Successed");
			psmt = conn.prepareStatement(sql);
			int i = 1;
			psmt.setString(i++, user.getId());
			psmt.setString(i++, user.getPassword());
			psmt.setString(i++, user.getName());
			psmt.setString(i++, user.getEmail());
			psmt.setString(i++, user.getAddress());
			System.out.println("addUser 3/6 Successed");
			count = psmt.executeUpdate();
			System.out.println("addUser 4/6 Successed");
		} catch (SQLException e) {
			System.out.println("addUser failed : "+e);
		} finally {
			db.close(psmt, conn);
			System.out.println("addUser 6/6 Successed");
		}
	}
	
	public List<UserDto> userList() {
		int count = 0;
		String sql = " select * from user order by id ";
		List<UserDto> users = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
			System.out.println("UserList 2/6 Successed");
			psmt = conn.prepareStatement(sql);
			System.out.println("UserList 3/6 Successed");
			rs = psmt.executeQuery();
			System.out.println("UserList 4/6 Successed");
			
			while(rs.next()) {
				int i = 1;
				UserDto dto = new UserDto(rs.getString(i++),
										  rs.getString(i++),
										  rs.getString(i++),
										  rs.getString(i++),
										  rs.getString(i++));
				users.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("UserList failed : "+e);
		} finally {
			db.close(rs,psmt, conn);
			System.out.println("UserList 6/6 Successed");
		}
		
		
		return users;
	}
	
	public UserDto getUser(String id) {
		int count = 0;
		String sql = " select id,password,name,email,address "
				+ " from user where id = ? ";
		UserDto user = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
			System.out.println("getUser 2/6 Successed");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			System.out.println("getUser 3/6 Successed");
			rs = psmt.executeQuery();
			System.out.println("getUser 4/6 Successed");
			
			while(rs.next()) {
				int i = 1;
				user = new UserDto(rs.getString(i++),
										  rs.getString(i++),
										  rs.getString(i++),
										  rs.getString(i++),
										  rs.getString(i++));
			}
		} catch (SQLException e) {
			System.out.println("getUser failed : "+e);
		} finally {
			db.close(rs,psmt, conn);
			System.out.println("getUser 6/6 Successed");
		}
		
		
		return user;
	}
	public UserDto login(String id, String password) {
		String sql = " select id,password,name,email,address "
				+ " from user where id = ? and password=? ";
		UserDto user = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
			System.out.println("login 2/6 Successed");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id.trim());
			psmt.setString(2, password);
			System.out.println("login 3/6 Successed");
			rs = psmt.executeQuery();
			System.out.println("login 4/6 Successed");
			
			while(rs.next()) {
				int j = 1;
				user = new UserDto(rs.getString(j++),
						rs.getString(j++),
						rs.getString(j++),
						rs.getString(j++),
						rs.getString(j++));
			}
			System.out.println("login 5/6 Successed");
		} catch (SQLException e) {
			System.out.println("login failed : "+e);
		} finally {
			db.close(rs,psmt, conn);
			System.out.println("login 6/6 Successed");
		}
		return user;
	}
	
	public void updateUser(UserDto user) {
		int count = 0;
		String sql = " update user "
				+ "set name=?,password=?,address=?,email=? "
				+ "where id=? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = db.getConnection();
			System.out.println("updateUser 2/6 Successed");
			psmt = conn.prepareStatement(sql);
			int i = 1;
			psmt.setString(i++, user.getName());
			psmt.setString(i++, user.getPassword());
			psmt.setString(i++, user.getAddress());
			psmt.setString(i++, user.getEmail());
			psmt.setString(i++, user.getId());
			System.out.println("updateUser 3/6 Successed");
			count = psmt.executeUpdate();
			System.out.println("updateUser 4/6 Successed");
		} catch (SQLException e) {
			System.out.println("updateUser failed : "+e);
		} finally {
			db.close(psmt, conn);
			System.out.println("updateUser 6/6 Successed");
		}
		
	}
	
	public boolean deleteUser(String id) {
		int count = 0;
		String sql = " delete from user where id=? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = db.getConnection();
			System.out.println("deleteUser 2/6 Successed");
			psmt = conn.prepareStatement(sql);
			int i = 1;
			psmt.setString(1, id.trim());
			System.out.println("deleteUser 3/6 Successed");
			count = psmt.executeUpdate();
			System.out.println("deleteUser 4/6 Successed");
		} catch (SQLException e) {
			System.out.println("deleteUser failed : "+e);
		} finally {
			db.close(psmt, conn);
			System.out.println("deleteUser 6/6 Successed");
		}
		return count>0?true:false;
	}

}
