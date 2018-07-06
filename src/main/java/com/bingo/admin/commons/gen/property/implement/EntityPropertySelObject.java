package com.bingo.admin.commons.gen.property.implement;

import com.bingo.admin.commons.gen.annotations.GeneratedSelectionInfo;
import com.bingo.admin.commons.gen.enums.PropertyType;
import com.bingo.admin.commons.gen.enums.SelectionType;
import com.bingo.admin.commons.gen.property.EntityProperty;
import com.bingo.admin.commons.gen.property.EntityPropertySelectionWrapper;
import com.bingo.admin.commons.gen.utils.StringUtils;

import java.lang.reflect.Field;


public class EntityPropertySelObject extends EntityPropertySelectionWrapper {

	private Class<?> clazz;
	private String moduleName;
	private String valueProperty;

	public EntityPropertySelObject() {
	}

	public EntityPropertySelObject(String name, String label,
								   PropertyType type, String[] fieldLevel,
								   SelectionType selectionType, Class<?> clazz, String moduleName,
								   String valueProperty) {
		super(name, label, type, fieldLevel, selectionType);
		this.clazz = clazz;
		this.moduleName = moduleName;
		this.valueProperty = valueProperty;
	}

	/*
	 * 获取方法
	 */

	public String getVarEntityName() {
		return StringUtils.getFirstLowerStr(clazz.getSimpleName());
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public String getModuleName() {
		return moduleName;
	}

	public String[] getRelativePropertys() {
		String afterItems = StringUtils.substringAfter(valueProperty, ",");// 去除第一个
		return afterItems.split(",");
	}

	public String getMajorProperty() {
		return StringUtils.substringBefore(valueProperty, ",");
	}

	public String getVarMajorProperty() {
		return StringUtils.getFirstLowerStr(getMajorProperty());
	}

	public String getUrlName() {
		return StringUtils.underscores(clazz.getSimpleName()).replace('_', '-');
	}

	@Override
	public EntityProperty createEntityProperty(Field field) {
		GeneratedSelectionInfo info = field
				.getAnnotation(GeneratedSelectionInfo.class);
		if (info != null && info.selectionType() == SelectionType.ObjectList) {
			return new EntityPropertySelObject(field.getName(), info.label(),
					PropertyType.getType(field.getType()), info.type(),
					info.selectionType(), info.clazz(), info.moduleName(),
					info.valueProperty());
		}
		return null;
	}
}
