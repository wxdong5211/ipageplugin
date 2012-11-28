
package com.impler.ipage;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class PageLinkTag extends TagSupport {
	
	private static final long serialVersionUID = -6944531154825553588L;
	
	private static final String PAGE_NO = ".currentPage";
	private static final String PAGE_SIZE = ".showCount";
	private static final String name = "";

	private Object param;
	private String action;
	private Boolean sizable = false;
	private String iId;
	private Boolean jump = true;
	private Boolean debug = false;
	private String sizegroup;
	private int display;

	@Override
	public int doStartTag() throws JspException {
		if(param==null)return SKIP_BODY;
		Page page = null;  
		String fieldname = null;
		if(param instanceof Page){    //参数就是Page实体  
            page = (Page) param;  
		}else{  //参数为某个实体，该实体拥有Page属性  
	       	fieldname = PageUtil.
	       			getFieldNameImplPage(param.getClass());
	    	if(fieldname==null)return SKIP_BODY;
       		try {
				page = (Page) ReflectHelper.getValueByFieldName(param,fieldname);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}  
	        if(page==null)return SKIP_BODY;
       }
		
		HttpServletRequest request = (HttpServletRequest) this.pageContext
				.getRequest();
		action = request.getContextPath() + "/" + action.split("\\?")[0] +"?";
//				+ (url.lastIndexOf("?") == -1 ? "?" : "&amp;");
		StringBuilder sb = new StringBuilder(action);
		
		@SuppressWarnings("unchecked")
        Map<String, String[]> map = (Map<String, String[]>) request
				.getParameterMap();
		for (Entry<String, String[]> entry : map.entrySet()) {
			String key = entry.getKey();
			if (!(name + fieldname + PAGE_NO).equals(key) 
					&& !(name + fieldname +  PAGE_SIZE).equals(key)) {
				String[] valueArray = entry.getValue();
				for (String value : valueArray) {
					sb.append(key).append("=").append(value).append("&");
				}
			}
		}

		String queryUrl = sb.toString();
		String tagId = UUID.randomUUID().toString();
		tagId = tagId.replaceAll("-", "");
		
		StringBuilder strHtml = new StringBuilder("<div id='ilink");
		strHtml.append(tagId)
			.append("' class='ilink'><ilink value='{")
			.append("\"jump\":").append(jump).append(",")
			.append("\"debug\":").append(debug).append(",")
			.append("\"display\":").append(display<1?PageUtil.DEFAULTDISPLAY:display).append(",")
			.append("\"sizable\":").append(sizable).append(",")
			.append("\"pSize\":").append(page.getShowCount()).append(",")
			.append("\"pTotaSize\":").append(page.getTotalResult()).append(",")
			.append("\"pTotaPage\":").append(page.getTotalPage()).append(",")
			.append("\"pCurrPage\":").append(page.getCurrentPage()).append(",")
			.append("\"ilinkId\":\"ilink").append(tagId).append("\",");
		if(iId!=null&&iId.length()!=0)
		    strHtml.append("\"iId\":\"").append(iId).append("\",");
		strHtml.append("\"action\":\"").append(queryUrl).append("\",")
			.append("\"baseName\":\"").append(fieldname).append(".\",")
			.append("\"sizegroup\":").append(
					(sizegroup==null||sizegroup.equals("")) ? 
							PageUtil.DEFAULTSIZEGROUP : sizegroup)
			.append("}'/></div>");
		try {
			this.pageContext.getOut().print(strHtml);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	// ----------------
	public void setParam(Object obj) {
		if(obj==null)return;
		if(obj instanceof String)
			param = pageContext.getRequest().
				getAttribute((String)obj);
		else param = obj;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getiId() {
		return iId;
	}

	public void setiId(String iId) {
		this.iId = iId;
	}

	public Boolean getJump() {
		return jump;
	}

	public void setJump(Boolean jump) {
		this.jump = jump == null ? true : jump;
	}

	public Boolean getSizable() {
		return sizable;
	}

	public void setSizable(Boolean sizable) {
		this.sizable = sizable == null ? false : sizable;
	}

	public String getSizegroup() {
		return sizegroup;
	}

	public void setSizegroup(String sizegroup) {
		this.sizegroup = sizegroup;
	}

	public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}

	public Boolean getDebug() {
		return debug;
	}

	public void setDebug(Boolean debug) {
		this.debug = debug == null ? false : debug;
	}

}
