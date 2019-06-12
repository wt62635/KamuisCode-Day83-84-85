package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtils {
	private static BasicDataSource ds;
	static{
		//��������Դ����
		ds = new BasicDataSource();
		//��ȡ�����ļ�
		Properties p = new Properties();
		InputStream ips = 
				DBUtils.class.getClassLoader()
				.getResourceAsStream("jdbc.properties");
		try {
			p.load(ips);
			String driver = p.getProperty("driver");
			String url = p.getProperty("url");
			String username = p.getProperty("username");
			String password = p.getProperty("password");
			//����������Ϣ
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
			ds.setInitialSize(3);//���ó�ʼ��������
			ds.setMaxActive(5);//���������������
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConn() throws Exception{
		//��ȡ���Ӷ���  ע�⵼���𵼴�   �쳣�׳�
		Connection conn = ds.getConnection();
		System.out.println(conn);
		return conn;
	}
	
	/**
	 * ����jdbc�淶�����ӹرյ�ʱ�򣬶�Ӧ��
	 * Statement�Լ�ResultSetӦ���Զ��رա�
	 * ���ǣ���һЩ���ӳص�ʵ�֣�û����ȫ����
	 * �淶�����¹ر�����ʱ����Ӧ��Statement
	 * ��ResultSet��û�йرա����ԣ�������
	 * �ر�����ʱ������ֶ��ر�Statement��
	 * ResultSet��
	 * 
	 */
	public static void close(ResultSet rs,
			Statement stat,Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		
		if(stat != null){
			try {
				stat.close();
			} catch (SQLException e) {
			}
		}
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
	
	
	
	
	
}
