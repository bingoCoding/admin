package com.bingo.admin.commons.gen.property;


import com.bingo.admin.commons.gen.enums.PropertyType;
import com.bingo.admin.commons.gen.factorys.EntityPropertyFactoryItem;
import com.bingo.admin.commons.gen.utils.StringUtils;

public abstract class EntityPropertyWrapper implements EntityProperty, EntityPropertyFactoryItem {

	protected String name;
	protected String label;
	protected PropertyType type;
	protected String[] fieldLevel;

	public EntityPropertyWrapper() {
	}

	public EntityPropertyWrapper(String name, String label, PropertyType type,
			String[] fieldLevel) {
		super();
		this.name = name;
		this.label = label;
		this.type = type;
		this.fieldLevel = fieldLevel;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public PropertyType getType() {
		return type;
	}

	@Override
	public String[] getFieldLevel() {
		return fieldLevel;
	}

	@Override
	public String getDbName() {
		return StringUtils.underscores(this.name);
	}

	@Override
	public String getUpName() {
		return StringUtils.getFirstUpperStr(name);
	}

}
