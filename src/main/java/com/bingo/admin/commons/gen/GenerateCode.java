package com.bingo.admin.commons.gen;

import com.bingo.admin.commons.config.SpringContextHolder;
import com.bingo.admin.service.impl.UserService;
import com.bingo.admin.utils.SpringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bingo.admin.commons.gen.TemplateGenerate;
import org.springframework.stereotype.Component;

@DependsOn("springContextHolder")
public class GenerateCode {
	public static void main(String[] args) {
		TemplateGenerate templateGenerate=new TemplateGenerate();
		templateGenerate.generateCode();
	}
}
