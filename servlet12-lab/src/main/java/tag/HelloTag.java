package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * 标签类:
 * 	a.要继承SimpleTagSupport类。
 *  b.override doTag方法，在该方法里面，
 *  添加相应的处理逻辑。
 *  c.标签有哪些属性，标签类也要有相应的属性。
 *  	注：属性名要相同，类型要匹配，并且
 *  	提供相应的set方法。
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
		 * 通过继承自SimpleTagSupport类提供的
		 * 方法来获得PageContext。
		 * PageContext提供了获得其它所有隐含对象
		 * 的方法。
		 */
		PageContext pctx = 
				(PageContext)getJspContext();
		JspWriter out = pctx.getOut();
				
		for(int i = 0; i < qty; i++){
			out.println(info+"<br/>");
		}
	}
	
	
	
	
	
	
}






