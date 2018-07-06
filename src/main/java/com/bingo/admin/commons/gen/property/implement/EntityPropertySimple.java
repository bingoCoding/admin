package com.bingo.admin.commons.gen.property.implement;

import com.bingo.admin.commons.gen.annotations.GeneratedInfo;
import com.bingo.admin.commons.gen.enums.PropertyType;
import com.bingo.admin.commons.gen.property.EntityProperty;
import com.bingo.admin.commons.gen.property.EntityPropertyWrapper;

import java.lang.reflect.Field;


public class EntityPropertySimple extends EntityPropertyWrapper {

	private static String DATE_CSS_VALUE = "datepicker";
	private static String LONG_CSS_VALUE = "valiNumber";
	private static String DOUBLE_CSS_VALUE = "valiNumber";
	private static String INTEGER_CSS_VALUE = "valiNumber";
	private static String BOOLEAN_CSS_VALUE = "";

	public EntityPropertySimple() {
	}

	public EntityPropertySimple(String name, String label, PropertyType type,
			String[] fieldLevel) {
		super(name, label, type, fieldLevel);
	}

	/**
	 * 
	 * @description 根据字段类型返回指定样式
	 * @reviewed_by
	 * @param @return
	 * @throw
	 */
	public String getStyleByType() {
		switch (type) {
		case D:
			return DATE_CSS_VALUE;
		case L:
			return LONG_CSS_VALUE;
		case I:
			return INTEGER_CSS_VALUE;
		case N:
			return DOUBLE_CSS_VALUE;
		case B:
			return BOOLEAN_CSS_VALUE;
		default:
			return "";
		}
	}

	@Override
	public EntityProperty createEntityProperty(Field field) {
		GeneratedInfo info = field.getAnnotation(GeneratedInfo.class);
		if (info != null) {
			PropertyType propertyType = PropertyType.getType(field.getType());
			if (propertyType == null)
				throw new RuntimeException("@GeneratedInfo注解的默认类型为简单类型，该实体的"
						+ field.getName() + "属性的类型不属于简单类型，无法对应生成代码！");
			return new EntityPropertySimple(field.getName(), info.label(),
					propertyType, info.type());
		}
		return null;
	}
}
