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

		// ���������Դ·��
		String uri = request.getRequestURI();
		// ��ȡ������Դ·����һ���֣�����Ƚ�
		String path = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		System.out.println("path:" + path);

		//��������·��������Ӧ�Ĵ���
		if ("/login".equals(path)) {
			// �����¼����
			processLogin(request, response);
		} else if ("/list".equals(path)) {
			// �����û��б�����
			processList(request, response);
		} else if ("/add".equals(path)) {
			// ��������û�����
			processAdd(request, response);
		} else if ("/del".equals(path)) {
			// ����ɾ���û�����
			processDel(request, response);
		}
	}

	private void processDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ��ȡҪɾ�����û���id
		int id = Integer.parseInt(request.getParameter("id"));
		// ɾ��ָ��id���û�
		UserDAO dao = new UserDAO();
		try {
			dao.delete(id);
			// �ض����û��б�
			response.sendRedirect("list.do");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("ϵͳ��æ���Ժ�����");
		}
	}

	private void processAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ��ȡ�û���Ϣ
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		
		
		
		
		UserDAO dao = new UserDAO();
		try {
			//�Ȳ鿴�û����Ƿ���ڣ�������ڣ�����ʾ�û�
			User user = dao.find(username);
			if(user != null){
				//�û����Ѿ����ڣ���ʾ�û�
				request.setAttribute("msg", 
						"�û����Ѿ�����");
				request.getRequestDispatcher(
						"addUser.jsp")
				.forward(request, response);
			}else{
				// ���û���Ϣ���뵽���ݿ�
				user = new User();
				user.setUname(username);
				user.setPwd(pwd);
				user.setEmail(email);
				dao.save(user);
				// �ض����û��б�
				response.sendRedirect("list.do");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("ϵͳ��æ���Ժ�����");
		}
	}

	private void processList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 * ����session��֤�����û���û�е�¼�� ���û�е�¼�����ض��򵽵�¼ҳ�档
		 */
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if (obj == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		// �������û���Ϣ��ѯ����
		UserDAO dao = new UserDAO();
		try {
			List<User> users = dao.findAll();
			// �����ݰ󶩵�request
			request.setAttribute("users", users);
			// ���ת����
			RequestDispatcher rd = request.getRequestDispatcher("listUser.jsp");
			// ת��
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("ϵͳ��æ���Ժ�����");
		}
	}

	private void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ��ȡ�û���������
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		// ��ѯ���ݿ⣬����û��ƥ��ļ�¼
		UserDAO dao = new UserDAO();
		try {
			User user = dao.find(username);
			if (user != null && user.getPwd().equals(pwd)) {
				// ��¼�ɹ�,�ض����û��б�
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.sendRedirect("list.do");
			} else {
				// ��¼ʧ�ܣ�ת������¼ҳ��
				request.setAttribute("login_failed", "�û������������");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("ϵͳ��æ���Ժ�����");
		}

	}

}
