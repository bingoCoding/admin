package com.bingo.admin.commons.gen.property.implement;

import com.bingo.admin.commons.gen.enums.PropertyType;
import com.bingo.admin.commons.gen.enums.SelectionType;
import com.bingo.admin.commons.gen.property.EntityProperty;
import com.bingo.admin.commons.gen.property.EntityPropertySelectionWrapper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class EntityPropertySelMap extends EntityPropertySelectionWrapper {

	private Map<String, String> map = new HashMap<String, String>();

	public EntityPropertySelMap() {
	}

	public EntityPropertySelMap(String name, String label, PropertyType type,
								String[] fieldLevel, SelectionType selectionType, String key,
								String value) {
		super(name, label, type, fieldLevel, selectionType);

		// 初始化
		String[] keys = key.split(",");
		String[] values = value.split(",");
		if (keys.length != values.length)
			throw new RuntimeException("配置错误，字段的key与value长度不同！");
		for (int i = 0; i < keys.length; i++) {
			map.put(keys[i], values[i]);
		}
	}

	/*
	 * 获取方法
	 */

	/*
	 * 属性的 get 方法
	 */

	public Map<String, String> getMap() {
		return map;
	}

	@Override
	public EntityProperty createEntityProperty(Field field) {
		return null;
	}

}
