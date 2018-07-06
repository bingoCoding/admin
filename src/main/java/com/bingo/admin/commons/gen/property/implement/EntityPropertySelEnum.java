package com.bingo.admin.commons.gen.property.implement;

import java.lang.reflect.Field;

import com.bingo.admin.commons.gen.annotations.GeneratedSelectionInfo;
import com.bingo.admin.commons.gen.enums.PropertyType;
import com.bingo.admin.commons.gen.enums.SelectionType;
import com.bingo.admin.commons.gen.property.EntityProperty;
import com.bingo.admin.commons.gen.property.EntityPropertySelectionWrapper;
import com.bingo.admin.commons.gen.utils.StringUtils;

public class EntityPropertySelEnum extends EntityPropertySelectionWrapper {

	private static String DefaultSuffix = "Name";

	private String keyName;
	private String valueName;
	private String valueProperty;
	private Class<?> clazz;

	public EntityPropertySelEnum() {
	}

	public EntityPropertySelEnum(String name, String label, PropertyType type,
			String[] fieldLevel, SelectionType selectionType, String key,
			String value, Class<?> clazz, String valueProperty) {
		super(name, label, type, fieldLevel, selectionType);
		this.keyName = key;
		this.valueName = value;
		this.clazz = clazz;
		this.valueProperty = valueProperty;
	}

	/*
	 * 获取方法
	 */
	public String getValueProperty() {
		if ("".equals(valueProperty))
			return name + DefaultSuffix;
		return valueProperty;
	}

	public String getEnumName() {
		return clazz.getSimpleName();
	}

	public String getKeyMehtodName() {
		if ("".equals(keyName))
			return "ordinal";
		return getMethodName(keyName);
	}

	public String getValueMethodName() {
		if ("".equals(valueName))
			return "name";
		return getMethodName(valueName);
	}

	/**
	 * 
	 * @method_name getMethodName
	 * @author wangxiaoxi ( email:tcwangxiaoxi@gmail.com )
	 * @date 2012-5-11 下午3:55:49
	 * @description 
	 * @reviewed_by proName-->getProName
	 * @param @param name
	 * @param @return
	 * @throw
	 */
	private String getMethodName(String name) {
		return "get" + StringUtils.getFirstUpperStr(name);
	}

	@Override
	public EntityProperty createEntityProperty(Field field) {
		GeneratedSelectionInfo info = field
				.getAnnotation(GeneratedSelectionInfo.class);
		if (info != null && info.selectionType() == SelectionType.Enum) {
			return new EntityPropertySelEnum(field.getName(), info.label(),
					PropertyType.getType(field.getType()), info.type(),
					info.selectionType(), info.key(), info.value(),
					info.clazz(), info.valueProperty());
		}
		return null;
	}
}
