package com.bingo.admin.commons.gen;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;

public interface TemplateItem {
	/**
	 * 
	 * @description 设置模板参数
	 * @reviewed_by
	 * @param @param context 模板上下文
	 * @param @param entityClass 实体类型
	 * @throw
	 */
	void SetItemContext(VelocityContext context, Class<?> entityClass);

	/**
	 * 
	 * @description 获取模板地址
	 * @reviewed_by
	 * @param @param entityClass 实体类型
	 * @param @return
	 * @throw
	 */
	String curTemplatePath(Class<?> entityClass);

	/**
	 * 
	 * @description 对模板生成的流数据做相应处理
	 * @reviewed_by
	 * @param @param stringWriter 字符输出流
	 * @param @param templateGenerate 模板实体
	 * @throw
	 */
	void doResultIO(StringWriter stringWriter, TemplateGenerate templateGenerate);
}
