
//------------------------------------------------------------------------------
//文   件  名： com.hp.util.base.ReflectHelper     版           本：1.0
//描          述：
//版权所有：杭州瀚鹏科技有限公司 
//------------------------------------------------------------------------------
//创  建   者：Invalid          创建日期：2012-4-12
//修  改   者：                                             修改日期：
//修改说明：
//------------------------------------------------------------------------------
package com.impler.ipage;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 反射工具
 * @author  Invalid
 * @date 2012-4-12 下午3:16:51
 */
public class ReflectHelper {

	/** 
     * 获取obj对象fieldName的Field 
     * @param obj 
     * @param fieldName 
     * @return 
     */  
    public static Field getFieldByFieldName(Object obj, String fieldName) {  
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass  
                .getSuperclass()) {  
            try {  
                return superClass.getDeclaredField(fieldName);  
            } catch (NoSuchFieldException e) {  
            }  
        }  
        return null;  
    }
    
    public static String getFieldNameByType(Class<?> superClass, Class<?> type) { 
    	if(superClass == Object.class)
    		return null;
    	Field[] fields = superClass.getDeclaredFields();
    	for(Field field : fields){
    		if(isTypeImplType(field.getType(),type))
    			return field.getName();
    	}
        return getFieldNameByType(superClass.getSuperclass(),type);  
    }
    
    public static boolean isTypeImplType(Class<?> clazz, Class<?> type) { 
    	Class<?>[] types = clazz.getInterfaces();
    	for(Class<?> item : types){
			if(item==type||isTypeImplType(item,type))
				return true;
		}
    	return false;
    }
  
    /** 
     * 获取obj对象fieldName的属性值 
     * @param obj 
     * @param fieldName 
     * @return 
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     */  
    public static Object getValueByFieldName(Object obj, String fieldName)  
            throws SecurityException, NoSuchFieldException,  
            IllegalArgumentException, IllegalAccessException { 
    	if(obj==null)return null;
    	if(obj instanceof Map)return ((Map<?,?>)obj).get(fieldName);
        Field field = getFieldByFieldName(obj, fieldName);  
        Object value = null;  
        if(field!=null){  
            if (field.isAccessible()) {  
                value = field.get(obj);  
            } else {  
                field.setAccessible(true);  
                value = field.get(obj);  
                field.setAccessible(false);  
            }  
        }  
        return value;  
    }  
  
    /** 
     * 设置obj对象fieldName的属性值 
     * @param obj 
     * @param fieldName 
     * @param value 
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     */  
    @SuppressWarnings("unchecked")
	public static void setValueByFieldName(Object obj, String fieldName,  
            Object value) throws SecurityException, NoSuchFieldException,  
            IllegalArgumentException, IllegalAccessException {  
    	if(obj==null)return ;
    	if(obj instanceof Map){
    		((Map<Object,Object>)obj).put(fieldName,value);
    		return ;
    	}
        //Field field = obj.getClass().getDeclaredField(fieldName);  
        Field field = getFieldByFieldName(obj, fieldName); 
        if (field.isAccessible()) {  
            field.set(obj, value);  
        } else {  
            field.setAccessible(true);  
            field.set(obj, value);  
            field.setAccessible(false);  
        }  
    }
    
}

