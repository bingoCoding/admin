package com.bingo.admin.commons.gen.factorys;

import java.lang.reflect.Field;

import com.bingo.admin.commons.gen.annotations.GeneratedInfo;
import com.bingo.admin.commons.gen.annotations.GeneratedSelectionInfo;
import com.bingo.admin.commons.gen.enums.PropertyType;
import com.bingo.admin.commons.gen.enums.SelectionType;
import com.bingo.admin.commons.gen.property.EntityProperty;
import com.bingo.admin.commons.gen.property.implement.EntityPropertySelArray;
import com.bingo.admin.commons.gen.property.implement.EntityPropertySelEnum;
import com.bingo.admin.commons.gen.property.implement.EntityPropertySelMap;
import com.bingo.admin.commons.gen.property.implement.EntityPropertySelObject;
import com.bingo.admin.commons.gen.property.implement.EntityPropertySimple;

/**
 * 
 * @description EntityProperty的静态工厂，用于将所有生成实现EntityProperty接口的实体统一存 放在该工厂类里管理
 * @reviewed_by
 * 
 */
public abstract class EntityPropertyStaticFactory {

	public static EntityProperty createSimpleEntityProperty(Field field) {
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

	public static EntityProperty createSelectionEntityProperty(Field field) {
		GeneratedSelectionInfo info = field
				.getAnnotation(GeneratedSelectionInfo.class);
		if (info != null) {
			SelectionType selectionType = info.selectionType();

			switch (selectionType) {
			// case ArrayList:
			// return new EntityPropertySelArray(field.getName(),
			// info.label(), PropertyType.getType(field.getType()),
			// info.type(), info.selectionType(), info.value());
			// case Map:
			// return new EntityPropertySelMap(field.getName(), info.label(),
			// PropertyType.getType(field.getType()), info.type(),
			// info.selectionType(), info.key(), info.value());
			case Enum:
				return new EntityPropertySelEnum(field.getName(), info.label(),
						PropertyType.getType(field.getType()), info.type(),
						info.selectionType(), info.key(), info.value(),
						info.clazz(), info.valueProperty());
			case ObjectList:
				return new EntityPropertySelObject(field.getName(),
						info.label(), PropertyType.getType(field.getType()),
						info.type(), info.selectionType(), info.clazz(),
						info.moduleName(), info.valueProperty());
			default:
				break;
			}

		}
		return null;
	}
}
