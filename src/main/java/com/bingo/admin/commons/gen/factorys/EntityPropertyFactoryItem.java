package com.bingo.admin.commons.gen.factorys;

import com.bingo.admin.commons.gen.property.EntityProperty;

import java.lang.reflect.Field;


/**
 * @description EntityProperty工厂的子项，用于根据该接口生成EntityProperty实体
 * @reviewed_by
 * 
 */
public interface EntityPropertyFactoryItem {
	EntityProperty createEntityProperty(Field field);
}
