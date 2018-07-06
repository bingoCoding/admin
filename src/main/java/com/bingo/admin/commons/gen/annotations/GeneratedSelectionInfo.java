package com.bingo.admin.commons.gen.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bingo.admin.commons.gen.enums.SelectionType;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GeneratedSelectionInfo {

	/**
	 * 普通属性字段
	 */
	/*
	 * 列表的表头描述
	 */
	String label();

	/*
	 * 用于区别不同的属性
	 */
	String[] type() default { "" };

	/**
	 * selection属性字段
	 */
	/*
	 * 选择属性的类型
	 */
	SelectionType selectionType() default SelectionType.Enum;

	/*
	 * 选择为键的属性(map类型时用","分隔)
	 */
	String key() default "";

	/*
	 * 选择为值的属性(数组或map类型时用","分隔)
	 */
	String value() default "";

	/*
	 * 选择的對象類型
	 */
	Class<?> clazz() default Object.class;

	/*
	 * 获取该选择类型名称的方法(对于object来说是设计的关联属性（用“,”分隔）)
	 */
	String valueProperty() default "";

	/*
	 * 获取该选择类型所属的模块(仅限于对象数组形式)
	 */
	String moduleName() default "";
}
