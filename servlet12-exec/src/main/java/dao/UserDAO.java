package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import util.DBUtils;

/**
 * 	DAO�ࣺ
 *     ��װ�����ݷ����߼���
 *
 */
public class UserDAO {
	
	/**
	 * �����û�����ѯ���û���������Ϣ(��������),
	 * ����Ҳ���������null��
	 */
	public User find(String username)
			throws Exception{
		User user = null;
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement(
				"SELECT * FROM t_user WHERE "
				+ "username=?");
			stat.setString(1, username);
			rs = stat.executeQuery();
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUname(username);
				user.setPwd(
						rs.getString("password"));
				user.setEmail(
						rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally{
			DBUtils.close(rs, stat, conn);
		}
		return user;
	}
	
	
	public void delete(int id) throws Exception{
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement(
					"DELETE FROM t_user WHERE id=?");
			stat.setInt(1, id);
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally{
			DBUtils.close(null, stat, conn);
		}
		
	}
	
	
	/**
	 * ���û�����Ϣ���浽���ݿ�
	 */
	public void save(User user) 
			throws Exception{
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement(
				"INSERT INTO t_user "
				+ "VALUES(null,?,?,?)");
			stat.setString(1, user.getUname());
			stat.setString(2, user.getPwd());
			stat.setString(3, user.getEmail());
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally{
			DBUtils.close(null, stat, conn);
		}
	}
	

	/**
	 * �������û���Ϣ��ѯ������
	 */
	public List<User> findAll() 
			throws Exception{
		List<User> users = 
				new ArrayList<User>();
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement(
				"SELECT * FROM t_user");
			rs = stat.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUname(
						rs.getString("username"));
				user.setPwd(
						rs.getString("password"));
				user.setEmail(
						rs.getString("email"));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally{
			DBUtils.close(rs, stat, conn);
		}
		return users;
	}
}



