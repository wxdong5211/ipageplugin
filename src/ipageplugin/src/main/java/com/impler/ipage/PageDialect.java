
//------------------------------------------------------------------------------
//文   件  名： com.hp.util.base.PageDialect     版           本：1.0
//描          述：
//版权所有：杭州瀚鹏科技有限公司 
//------------------------------------------------------------------------------
//创  建   者：Invalid          创建日期：2012-4-13
//修  改   者：                                             修改日期：
//修改说明：
//------------------------------------------------------------------------------
package com.impler.ipage;
/**
 * 各个数据库分页语句枚举
 * @author  Invalid
 * @date 2012-4-13 上午9:50:06
 */
public enum PageDialect {

	mysql {
		@Override
		public String generatePageSql(String sql, Page page) {
			StringBuffer pageSql = new StringBuffer(sql);
			if(page!=null)
				pageSql.append(" limit "+page.getCurrentResult()+","+page.getShowCount());
			return pageSql.toString();
		}
	},
	oracle {
		@Override
		public String generatePageSql(String sql, Page page) {
			StringBuffer pageSql = new StringBuffer(sql);
			if(page!=null){
				pageSql.insert(0, "select * from (select tmp_tb.*,ROWNUM row_id from (")
					.append(") tmp_tb where ROWNUM<=")
                	.append(page.getCurrentResult()+page.getShowCount())
                	.append(") where row_id>")
                	.append(page.getCurrentResult());
			}
			return pageSql.toString();
		}
	},
	sqlServer {
		@Override
		public String generatePageSql(String sql, Page page) {
			StringBuffer pageSql = new StringBuffer(sql);
			if(page!=null){
				pageSql.insert(6, " top "+(page.getCurrentResult()+page.getShowCount())+" tempColumn=0, ");
				pageSql.insert(0, "select * from ( select row_number()over(order by tempColumn)tempRowNumber,* from (");
				pageSql.append(")t )tt where tempRowNumber>"+page.getCurrentResult()+"");
			}
			return pageSql.toString();
		}
	};
	
	public abstract String generatePageSql(String sql,Page page);
	
}

