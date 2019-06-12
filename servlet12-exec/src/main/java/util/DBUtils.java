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
		//创建数据源对象
		ds = new BasicDataSource();
		//读取配置文件
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
			//设置连接信息
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
			ds.setInitialSize(3);//设置初始连接数量
			ds.setMaxActive(5);//设置最大连接数量
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConn() throws Exception{
		//获取连接对象  注意导包别导错   异常抛出
		Connection conn = ds.getConnection();
		System.out.println(conn);
		return conn;
	}
	
	/**
	 * 按照jdbc规范，连接关闭的时候，对应的
	 * Statement以及ResultSet应该自动关闭。
	 * 但是，有一些连接池的实现，没有完全符合
	 * 规范，导致关闭连接时，对应的Statement
	 * 及ResultSet并没有关闭。所以，建议在
	 * 关闭连接时，最好手动关闭Statement及
	 * ResultSet。
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
