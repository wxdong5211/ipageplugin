
//------------------------------------------------------------------------------
//文   件  名： com.hp.util.base.PageItem     版           本：1.0
//描          述：
//版权所有：杭州瀚鹏科技有限公司 
//------------------------------------------------------------------------------
//创  建   者：Invalid          创建日期：2012-5-4
//修  改   者：                                             修改日期：
//修改说明：
//------------------------------------------------------------------------------
package com.impler.ipage;
/**
 * 
 * @author  Invalid
 * @date 2012-5-4 上午11:46:27
 */
public class PageItem {

	private Object current;
	private String key;
	private int index;
	private int count;
	private boolean first;
	private boolean last;

	public Object getCurrent() {
		return current;
	}

	public void setCurrent(Object current) {
		this.current = current;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}

