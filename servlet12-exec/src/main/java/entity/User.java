package entity;
/**
 * ʵ����:
 *    ��������������ֶζ�Ӧ������Ҫƥ�䡣
 *
 */
public class User {
	private int id;
	private String uname;
	private String pwd;
	private String email;
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", uname=" + uname + ", pwd=" + pwd + ", email=" + email + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
