package com.bingo.admin.commons.gen.enums;

import java.util.Date;

/**
 * 
 * @description 属性数据类型
 * @reviewed_by
 * 
 */
public enum PropertyType {
	S(String.class), I(Integer.class), L(Long.class), N(Double.class), D(
			Date.class), B(Boolean.class), F(Float.class);

	private Class<?> clazz;

	PropertyType(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getValue() {
		return clazz;
	}

	public static PropertyType getType(Class<?> clazz) {
		for (PropertyType itemType : PropertyType.values()) {
			if (itemType.getValue() == clazz) {
				return itemType;
			}
		}
		if ("int".equals(clazz.getName()))
			return I;
		else if ("long".equals(clazz.getName()))
			return L;
		else if ("double".equals(clazz.getName()))
			return N;
		else if ("float".equals(clazz.getName()))
			return F;
		else if ("boolean".equals(clazz.getName()))
			return B;
		else
			return null;

	}
}
