package test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.UserDAO;
import entity.User;

public class TestCase2 {
	
	private UserDAO dao;
	
	@Before
	/*
	 * @Before修饰的方法会在其它测试方法运行
	 * 之前先执行。
	 */
	public void init(){
		dao = new UserDAO();
	}
	
	@Test
	public void test1() throws Exception{
		List<User> users = 
				 dao.findAll();
		System.out.println(users);
	}
	
	@Test
	public void test2() throws Exception{
		User user = new User();
		user.setUname("Eric");
		user.setPwd("123");
		user.setEmail("Sally@test.com");
		dao.save(user);
	}
	
	@Test
	public void test3() throws Exception{
		dao.delete(13);
	}
	
	@Test
	public void test4() throws Exception{
		System.out.println(dao.find("小月月"));
	}

}


