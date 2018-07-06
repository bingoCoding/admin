package com.bingo.admin.commons.gen.factorys.items;

import com.bingo.admin.commons.gen.factorys.EntityPropertyFactoryItem;
import com.bingo.admin.commons.gen.factorys.EntityPropertyStaticFactory;
import com.bingo.admin.commons.gen.property.EntityProperty;

import java.lang.reflect.Field;


public class SimpEntProFactoryItem implements EntityPropertyFactoryItem {

	@Override
	public EntityProperty createEntityProperty(Field field) {
		return EntityPropertyStaticFactory.createSimpleEntityProperty(field);
	}
}
