package com.impler.ipage;
/**
 * 
 * @author  Invalid
 * @date 2012-4-12 下午3:16:12
 */
public class DefaultPageImpl implements Page {
	
	private int showCount = PageUtil.DEFAULTSIZE; //每页显示记录数  
    private int totalPage;      //总页数  
    private int totalResult;    //总记录数  
    private Integer currentPage;    //当前页  
    private int currentResult;  //当前记录起始索引  
    private boolean entityOrField;  //true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性  

    /* (non-Javadoc)
	 * @see com.hp.util.base.Page#getTotalPage()
	 * @author  Invalid
	 * @date 2012-4-13 上午9:42:23
	 */
    @Override
	public int getTotalPage() {  
        if(totalResult%showCount==0)  
            totalPage = totalResult/showCount;  
        else  
            totalPage = totalResult/showCount+1;  
        return totalPage;  
    }  
    /* (non-Javadoc)
	 * @see com.hp.util.base.Page#setTotalPage(int)
	 * @author  Invalid
	 * @date 2012-4-13 上午9:42:23
	 */
    @Override
	public void setTotalPage(int totalPage) {  
        this.totalPage = totalPage;  
    }  
    /* (non-Javadoc)
	 * @see com.hp.util.base.Page#getTotalResult()
	 * @author  Invalid
	 * @date 2012-4-13 上午9:42:23
	 */
    @Override
	public int getTotalResult() {  
        return totalResult;  
    }  
    /* (non-Javadoc)
	 * @see com.hp.util.base.Page#setTotalResult(int)
	 * @author  Invalid
	 * @date 2012-4-13 上午9:42:23
	 */
    @Override
	public void setTotalResult(int totalResult) {  
        this.totalResult = totalResult;  
    }  
    /* (non-Javadoc)
	 * @see com.hp.util.base.Page#getCurrentPage()
	 * @author  Invalid
	 * @date 2012-4-13 上午9:42:23
	 */
    @Override
	public Integer getCurrentPage() {  
        if(currentPage==null||currentPage<=0)  
            currentPage = 1;  
        else if(currentPage>getTotalPage())  
            currentPage = getTotalPage();  
        return currentPage;  
    }  
    /* (non-Javadoc)
	 * @see com.hp.util.base.Page#setCurrentPage(int)
	 * @author  Invalid
	 * @date 2012-4-13 上午9:42:23
	 */
    @Override
	public void setCurrentPage(Integer currentPage) {  
        this.currentPage = currentPage;  
    }  
    
    /* (non-Javadoc)
	 * @see com.hp.util.base.Page#getShowCount()
	 * @author  Invalid
	 * @date 2012-4-13 上午9:42:23
	 */
    @Override
	public int getShowCount() {  
        return showCount;  
    }  
    /* (non-Javadoc)
	 * @see com.hp.util.base.Page#setShowCount(int)
	 * @author  Invalid
	 * @date 2012-4-13 上午9:42:23
	 */
    @Override
	public void setShowCount(int showCount) {  
        this.showCount = showCount > 0 ? showCount : 1;  
    }  
    /* (non-Javadoc)
	 * @see com.hp.util.base.Page#getCurrentResult()
	 * @author  Invalid
	 * @date 2012-4-13 上午9:42:23
	 */
    @Override
	public int getCurrentResult() {  
        currentResult = (getCurrentPage()-1)*getShowCount();  
        if(currentResult<0)  
            currentResult = 0;  
        return currentResult;  
    }  
    /* (non-Javadoc)
	 * @see com.hp.util.base.Page#setCurrentResult(int)
	 * @author  Invalid
	 * @date 2012-4-13 上午9:42:23
	 */
    @Override
	public void setCurrentResult(int currentResult) {  
        this.currentResult = currentResult;  
    }  
    /* (non-Javadoc)
	 * @see com.hp.util.base.Page#isEntityOrField()
	 * @author  Invalid
	 * @date 2012-4-13 上午9:42:23
	 */
    @Override
	public boolean isEntityOrField() {  
        return entityOrField;  
    }  
    /* (non-Javadoc)
	 * @see com.hp.util.base.Page#setEntityOrField(boolean)
	 * @author  Invalid
	 * @date 2012-4-13 上午9:42:23
	 */
    @Override
	public void setEntityOrField(boolean entityOrField) {  
        this.entityOrField = entityOrField;  
    }  
}

