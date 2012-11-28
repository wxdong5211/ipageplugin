//------------------------------------------------------------------------------
//文   件  名：         ColumnTag.java                                   版           本：下午03:47:26
//描          述：
//版权所有：杭州瀚鹏科技有限公司 
//------------------------------------------------------------------------------
//创  建   者：lvwenyong          创建日期：2011-8-1
//修  改   者：                                             修改日期：
//修改说明：
//------------------------------------------------------------------------------

package com.impler.ipage;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class PageItemTag extends BodyTagSupport {
	
	private static final long serialVersionUID = 410440690137352400L;
	
	
	private String title;
	private String property;
	private String type;
	private String ckeckboxName;
	private String format;
	private Integer maxWord;
	private Integer width;
	


	// ----标签开始时调用此方法-------
	public int doStartTag() {
		try {
			JspWriter out = pageContext.getOut();
			PageTag parent = (PageTag) getParent();
			out.print(htmlString(parent.getEntity(), parent.getIndex(),parent.getiId(),parent.getCol()));
			if (parent.getIndex() == 0) {
				// 表头
				return SKIP_BODY;
			} else {
				return EVAL_BODY_INCLUDE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return EVAL_BODY_INCLUDE;
		}
	}

	// ----标签结束时调用此方法-------
	public int doEndTag() {
		try {
			pageContext.getOut().print("</div>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	private String htmlString(PageItem entity, Integer rn, String iId, int col)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, NoSuchFieldException {
		String html = "";
		String style= "";
		if(width!=null&&width>0)
		    style = "style='width:"+width+"px'";
		if (rn == 0) {
			// 表头
			html = "<div class='"+PageUtil.DEFAULTPAGECSS+PageUtil.DEFAULTCOLSPE+col+"' title='"+title+"' "+style+">";
			
			if ("checkbox".equals(type)) {
				html += "<input type=\"checkbox\" id=\"checkall\" "
						+ "onclick=\"var cks = document.getElementsByName('"
						+ ckeckboxName
						+ "'); "
						+ "for(var i=0;i<cks.length;i++){cks[i].checked = this.checked;}\" />";
			} else {
				html += title;
			}
			return html;
		}
		
		if(entity==null)return "";
		if (property != null && !"".equals(property)) {
			String myFmt;
			Object value = ReflectHelper.getValueByFieldName(entity.getCurrent(), property);
			if (value != null) {
				
				if ("checkbox".equals(type)) {
					html = " <input type=\"checkbox\" " +
						   " name=\"" + ckeckboxName + "\" " +
						   " value=\"" + value + "\" " + 
						   " onclick=\"document.getElementById('checkall').checked=false\" />";
				} else if ("num".equals(type)) {
					myFmt = format == null || "".equals(format) ? PageUtil.DEFAULTNUMFMT
							: format;
					html = String.format(myFmt, value);
				} else if ("date".equals(type)) {
					myFmt = format == null || "".equals(format) ? PageUtil.DEFAULTDATEFMT
							: format;
					html = PageUtil.getDateString(myFmt, (Date) value);
				} else if(maxWord!=null){
					String s = PageUtil.Html2Text(value.toString());
					html = s.length()>maxWord?s.substring(0, maxWord):s;
				} else {
					html = PageUtil.Html2Text(value.toString());
				}
			} 
		}
		return "<div class='"+PageUtil.DEFAULTPAGECSS+PageUtil.DEFAULTCOLSPE+col+"' title='"+html+"' "+style+">" + html;
	}

	// getter,setter
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCkeckboxName() {
		return ckeckboxName;
	}

	public void setCkeckboxName(String ckeckboxName) {
		this.ckeckboxName = ckeckboxName;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Integer getMaxWord() {
		return maxWord;
	}

	public void setMaxWord(Integer maxWord) {
		this.maxWord = maxWord;
	}

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

}
