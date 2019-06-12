package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;

public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 获得请求资源路径
		String uri = request.getRequestURI();
		// 截取请求资源路径的一部分，方便比较
		String path = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		System.out.println("path:" + path);

		//依据请求路径进行相应的处理
		if ("/login".equals(path)) {
			// 处理登录请求
			processLogin(request, response);
		} else if ("/list".equals(path)) {
			// 处理用户列表请求
			processList(request, response);
		} else if ("/add".equals(path)) {
			// 处理添加用户请求
			processAdd(request, response);
		} else if ("/del".equals(path)) {
			// 处理删除用户请求
			processDel(request, response);
		}
	}

	private void processDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 读取要删除的用户的id
		int id = Integer.parseInt(request.getParameter("id"));
		// 删除指定id的用户
		UserDAO dao = new UserDAO();
		try {
			dao.delete(id);
			// 重定向到用户列表
			response.sendRedirect("list.do");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("系统繁忙，稍后重试");
		}
	}

	private void processAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 读取用户信息
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		
		
		
		
		UserDAO dao = new UserDAO();
		try {
			//先查看用户名是否存在，如果存在，则提示用户
			User user = dao.find(username);
			if(user != null){
				//用户名已经存在，提示用户
				request.setAttribute("msg", 
						"用户名已经存在");
				request.getRequestDispatcher(
						"addUser.jsp")
				.forward(request, response);
			}else{
				// 将用户信息插入到数据库
				user = new User();
				user.setUname(username);
				user.setPwd(pwd);
				user.setEmail(email);
				dao.save(user);
				// 重定向到用户列表
				response.sendRedirect("list.do");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("系统繁忙，稍后重试");
		}
	}

	private void processList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 * 进行session验证，看用户有没有登录， 如果没有登录，则重定向到登录页面。
		 */
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if (obj == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		// 将所有用户信息查询出来
		UserDAO dao = new UserDAO();
		try {
			List<User> users = dao.findAll();
			// 将数据绑订到request
			request.setAttribute("users", users);
			// 获得转发器
			RequestDispatcher rd = request.getRequestDispatcher("listUser.jsp");
			// 转发
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("系统繁忙，稍后重试");
		}
	}

	private void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 读取用户名和密码
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		// 查询数据库，看有没有匹配的记录
		UserDAO dao = new UserDAO();
		try {
			User user = dao.find(username);
			if (user != null && user.getPwd().equals(pwd)) {
				// 登录成功,重定向到用户列表
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.sendRedirect("list.do");
			} else {
				// 登录失败，转发到登录页面
				request.setAttribute("login_failed", "用户名或密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("系统繁忙，稍后重试");
		}

	}

}
