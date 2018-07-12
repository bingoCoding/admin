package com.bingo.admin.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Annotation 工具类
 */
public class AnnotationUtil {
	
	/**
	 * 查找方法上的注解（不包括继承下来的）
	 * 
	 * @param method			调用方法
	 * @param annotationType	注解类型
	 */
	public static <T extends Annotation> T findAnnotation(Method method, Class<?> annotationType) {
		return findAnnotation(method, annotationType, EInheritStatus.NO);
	}
	
	/**
	 * 查找方法上的注解
	 * 
	 * @param method			调用方法
	 * @param annotationType	注解类型
	 * @param inherit			是否包含继承的信息
	 */
	public static <T extends Annotation> T findAnnotation(Method method, Class<?> annotationType, EInheritStatus inherit) {
		
		// 参数检测
		if (method == null) return null;
		
		// 查找注解
		switch (inherit) {
		case YES :
			return findAnnotation(method.getAnnotations(), annotationType);
		case NO :
			return findAnnotation(method.getDeclaredAnnotations(), annotationType);
		default :
			return null;
		}
	}
	
	/**
	 * 查找类上的注解，包括父类
	 * 
	 * @param clazz				类
	 * @param annotationType	注解类型
	 */
	public static <T extends Annotation> T findAnnotation(Class<?> clazz, Class<?> annotationType) {
		return findAnnotation(clazz, annotationType, EInheritStatus.NO);
	}
	
	/**
	 * 查找类上的注解，包括父类
	 * 
	 * @param clazz				类
	 * @param annotationType	注解类型
	 * @param inherit			是否包含继承的信息
	 */
	public static <T extends Annotation> T findAnnotation(Class<?> clazz, Class<?> annotationType, EInheritStatus inherit) {
		
		// 参数检测
		if (clazz == null) return null;
		
		// 查找注解
		switch (inherit) {
		case YES :
			return findAnnotation(clazz.getAnnotations(), annotationType);
		case NO :
			return findAnnotation(clazz.getDeclaredAnnotations(), annotationType);
		default :
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends Annotation> T findAnnotation(Annotation[] arrAnnotation, Class<?> annotationType) {
		
		// 查找注解
		for (Annotation annotation : arrAnnotation) {
			if (annotation.annotationType() == annotationType) {
				return (T) annotation;
			}
		}
		
		return null;
	}
	
	/**
	 * 查找参数注解
	 * 
	 * @param method			方法
	 * @param annotationType	注解类型
	 */
	public static <T extends Annotation> T findParameterAnnotation(Method method, Class<?> annotationType) {
		return findParameterAnnotation(method.getParameterAnnotations(), annotationType);
	}
	
	/**
	 * 查找参数注解
	 * 
	 * @param annotationType	注解类型
	 */
	public static <T extends Annotation> T findParameterAnnotation(Annotation[][] arrAnnotaions, Class<?> annotationType) {
		
		System.out.println("");
		
		return null;
	}
}
