//------------------------------------------------------------------------------
//文   件  名：         TableTag.java                                   版           本：下午03:47:07
//描          述：
//版权所有：杭州瀚鹏科技有限公司 
//------------------------------------------------------------------------------
//创  建   者：lvwenyong          创建日期：2011-8-1
//修  改   者：                                             修改日期：
//修改说明：
//------------------------------------------------------------------------------

package com.impler.ipage;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class PageTag extends BodyTagSupport {

	private static final long serialVersionUID = 418509287044576086L;
	
	private String var;// 在pageContext中标识的一个属性名
	private String varStatus;
	private Iterator<?> item;// 要迭代的对象
	private int index;
	private int col;
	private int size;
	private PageItem entity;
	private String iId;
	
	public PageTag(){
		iId="ipage"+UUID.randomUUID().toString();;
	}
	
	public void setItems(Object items) {
	    item = null;
		if(items==null)return;
		Collection<?> list = null;
		if(items instanceof String)
		    items = pageContext.getRequest().
				getAttribute((String)items);
		if(items instanceof Collection)
			list = (Collection<?>) items;
		else if(items instanceof Map)
            list = ((Map<?,?>) items).entrySet();
		if (list != null && !list.isEmpty()) {
			item = list.iterator();
			size = list.size();
		}
	}

	// ----标签开始时调用此方法-------
	public int doStartTag() {
		try {
			if (item == null) {
				pageContext.getOut().write(PageUtil.getNodatafound());
				return SKIP_BODY;
			} else {
				index = 0;
				pageContext.getOut().write("<div id='"+iId+"' class='"+
						PageUtil.DEFAULTPAGECSS+"'>\n<div class='"+
						PageUtil.DEFAULTPAGECSS+"title'><ul><li>");
				return EVAL_BODY_AGAIN;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return SKIP_BODY;
		}
	}

	// ----标签体执行完后调用此方法----
	public int doAfterBody() {
		return continueNext(item);
	}

	// ----标签结束时调用此方法-------
	public int doEndTag() {
		try {
			if (item != null && bodyContent != null) {
				bodyContent.writeOut(bodyContent.getEnclosingWriter());
				pageContext.getOut().write("</li>\n</ul></div></div>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	// ----迭代----
	protected int continueNext(Iterator<?> it) {
		if (it.hasNext()) {
			try {
				if(index==0)
					pageContext.getOut().write("</li></ul></div>\n<div class='"+
						PageUtil.DEFAULTPAGECSS+"list'><ul><li class='"+
						PageUtil.DEFAULTPAGECSS+PageUtil.DEFAULTROWSPE+index+"'>");
				else
					pageContext.getOut().write("</li>\n<li class='"+
						PageUtil.DEFAULTPAGECSS+PageUtil.DEFAULTROWSPE+index+"'>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			Object temp = it.next();
			String key = null;
			if(temp instanceof Entry){
			    Entry<?,?> ee = (Entry<?,?>)temp;
			    key = (String) ee.getKey();
			    temp = ee.getValue();
			}
			key = key == null ? index+"" : key;
			entity = new PageItem();
			entity.setCurrent(temp);
			entity.setKey(key);
			entity.setIndex(index);
			entity.setCount(index+1);
			entity.setFirst(index==0);
			entity.setLast((index+1)==size);
			if(varStatus!=null&&!varStatus.equals(""))
				pageContext.setAttribute(varStatus, entity, PageContext.PAGE_SCOPE);
			pageContext.setAttribute(var, temp, PageContext.PAGE_SCOPE);
			index++;
			col = 0;
			return EVAL_BODY_AGAIN;
		} else {
			return SKIP_BODY;
		}
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public PageItem getEntity() {
		return entity;
	}

	public void setEntity(PageItem entity) {
		this.entity = entity;
	}

	public String getVarStatus() {
		return varStatus;
	}

	public void setVarStatus(String varStatus) {
		this.varStatus = varStatus;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getiId() {
		return iId;
	}

	public void setiId(String iId) {
		this.iId = iId;
	}

	public int getCol() {
		return col++;
	}
	
}
