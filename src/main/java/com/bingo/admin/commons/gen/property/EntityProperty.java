package com.bingo.admin.commons.gen.property;


import com.bingo.admin.commons.gen.enums.PropertyType;

public interface EntityProperty {

	/**
	 * 
	 * @description 属性名
	 * @reviewed_by
	 * @param @return
	 * @throw
	 */
	public String getName();

	/**
	 * 
	 * @description 属性标题
	 * @reviewed_by
	 * @param @return
	 * @throw
	 */
	public String getLabel();

	/**
	 * 
	 * @description 属性类型
	 * @reviewed_by
	 * @param @return
	 * @throw
	 */
	public PropertyType getType();

	/**
	 * 
	 * @description 属性分类
	 * @reviewed_by
	 * @param @return
	 * @throw
	 */
	public String[] getFieldLevel();

	/**
	 * 
	 * @description proName-->pro_name
	 * @reviewed_by
	 * @param @return
	 * @throw
	 */
	public String getDbName();

	/**
	 * @description proName-->ProName
	 * @reviewed_by
	 * @param @return
	 * @throw
	 */
	public String getUpName();
}
