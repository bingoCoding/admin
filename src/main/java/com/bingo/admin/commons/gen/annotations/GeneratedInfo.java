package com.bingo.admin.commons.gen.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GeneratedInfo {
	/*
	 * 列表的表头描述
	 */
	String label();

	/*
	 * 用于区别不同的属性
	 */
	String[] type() default { "" };
}
