package com.bingo.admin.commons.gen.property.implement;

import com.bingo.admin.commons.gen.enums.PropertyType;
import com.bingo.admin.commons.gen.enums.SelectionType;
import com.bingo.admin.commons.gen.property.EntityProperty;
import com.bingo.admin.commons.gen.property.EntityPropertySelectionWrapper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class EntityPropertySelArray extends EntityPropertySelectionWrapper {

	private Map<String, String> map = new HashMap<String, String>();

	public EntityPropertySelArray() {
	}

	public EntityPropertySelArray(String name, String label, PropertyType type,
								  String[] fieldLevel, SelectionType selectionType, String value) {
		super(name, label, type, fieldLevel, selectionType);

		// 初始化，把value解析为数组
		String[] values = value.split(",");
		for (int i = 0; i < values.length; i++) {
			map.put(String.valueOf(i), values[i]);
		}
	}

	@Override
	public EntityProperty createEntityProperty(Field field) {
		// TODO Auto-generated method stub
		return null;
	}
}
