//------------------------------------------------------------------------------
//文   件  名： com.hp.util.base.Page     版           本：1.0
//描          述：
//版权所有：杭州瀚鹏科技有限公司 
//------------------------------------------------------------------------------
//创  建   者：Invalid          创建日期：2012-4-13
//修  改   者：                                             修改日期：
//修改说明：
//------------------------------------------------------------------------------
package com.impler.ipage;

/**
 * 
 * @author  Invalid
 * @date 2012-4-13 上午9:42:23
 */
public interface Page {

	int getTotalPage();

	void setTotalPage(int totalPage);

	int getTotalResult();

	void setTotalResult(int totalResult);

	Integer getCurrentPage();

	void setCurrentPage(Integer currentPage);

	int getShowCount();

	void setShowCount(int showCount);

	int getCurrentResult();

	void setCurrentResult(int currentResult);

	boolean isEntityOrField();

	void setEntityOrField(boolean entityOrField);

}
