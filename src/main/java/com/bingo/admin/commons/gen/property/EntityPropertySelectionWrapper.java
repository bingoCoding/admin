package com.bingo.admin.commons.gen.property;


import com.bingo.admin.commons.gen.enums.PropertyType;
import com.bingo.admin.commons.gen.enums.SelectionType;

public abstract class EntityPropertySelectionWrapper extends
		EntityPropertyWrapper {

	private SelectionType selectionType;

	public EntityPropertySelectionWrapper() {
	}

	public EntityPropertySelectionWrapper(String name, String label,
										  PropertyType type, String[] fieldLevel, SelectionType selectionType) {
		super(name, label, type, fieldLevel);
		this.selectionType = selectionType;
	}

	/*
	 * 获取方法
	 */

	public boolean isSelection() {
		return true;
	}

	/*
	 * 属性的 get 方法
	 */

	public SelectionType getSelectionType() {
		return selectionType;
	}

}
