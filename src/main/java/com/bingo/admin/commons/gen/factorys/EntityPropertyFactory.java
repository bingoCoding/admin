package com.bingo.admin.commons.gen.factorys;

import com.bingo.admin.commons.gen.property.EntityProperty;
import com.bingo.admin.commons.gen.property.implement.EntityPropertySelEnum;
import com.bingo.admin.commons.gen.property.implement.EntityPropertySelObject;
import com.bingo.admin.commons.gen.property.implement.EntityPropertySimple;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @description EntityProperty工厂类，用于动态生成EntityProperty实体
 * @reviewed_by
 * 
 */
public class EntityPropertyFactory {

	private List<EntityPropertyFactoryItem> factoryItems = new ArrayList<EntityPropertyFactoryItem>();

	protected EntityPropertyFactory() {
		// 已不用这个类添加，使用配置文件注入实体属性类型
		// factoryItems.add(new SimpEntProFactoryItem());
		// factoryItems.add(new SelectionEntProFactoryItem());
		factoryItems.add(new EntityPropertySimple());
		factoryItems.add(new EntityPropertySelEnum());
		factoryItems.add(new EntityPropertySelObject());
	}

	private static EntityPropertyFactory entityPropertyFactory = new EntityPropertyFactory();

	public static EntityPropertyFactory getEntityPropertyInstance() {
		return entityPropertyFactory;
	}

	/**
	 * 
	 * @description 根据指定的字段的注解获取信息类
	 * @reviewed_by
	 * @param @param field
	 * @param @return
	 * @throw
	 */
	public EntityProperty createEntityProperty(Field field) {
		EntityProperty entityProperty = null;
		for (EntityPropertyFactoryItem entityPropertyFactoryItem : factoryItems) {
			entityProperty = entityPropertyFactoryItem
					.createEntityProperty(field);
			if (entityProperty != null) {
				return entityProperty;
			}
		}
		return null;
	}
}
