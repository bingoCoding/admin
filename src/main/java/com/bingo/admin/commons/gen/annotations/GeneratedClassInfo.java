package com.bingo.admin.commons.gen.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GeneratedClassInfo {
	/*
	 * 列表表格的caption即标题
	 */
	String label();
}
