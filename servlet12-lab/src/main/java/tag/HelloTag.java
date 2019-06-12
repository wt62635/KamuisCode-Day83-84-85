package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * ��ǩ��:
 * 	a.Ҫ�̳�SimpleTagSupport�ࡣ
 *  b.override doTag�������ڸ÷������棬
 *  �����Ӧ�Ĵ����߼���
 *  c.��ǩ����Щ���ԣ���ǩ��ҲҪ����Ӧ�����ԡ�
 *  	ע��������Ҫ��ͬ������Ҫƥ�䣬����
 *  	�ṩ��Ӧ��set������
 *
 */
public class HelloTag extends SimpleTagSupport{
	
	private String info;
	private int qty;
	
	public HelloTag(){
		System.out.println(
				"HelloTag's constructor");
	}
	
	
	public void setInfo(String info) {
		System.out.println(
				"HelloTag's setInfo..." + info);
		this.info = info;
	}

	public void setQty(int qty) {
		System.out.println(
				"HelloTag's setQty..." + qty);
		this.qty = qty;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		System.out.println(
				"HelloTag's doTag...");
		
		/*
		 * ͨ���̳���SimpleTagSupport���ṩ��
		 * ���������PageContext��
		 * PageContext�ṩ�˻������������������
		 * �ķ�����
		 */
		PageContext pctx = 
				(PageContext)getJspContext();
		JspWriter out = pctx.getOut();
				
		for(int i = 0; i < qty; i++){
			out.println(info+"<br/>");
		}
	}
	
	
	
	
	
	
}






