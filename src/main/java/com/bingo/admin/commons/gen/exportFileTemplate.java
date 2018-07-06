package com.bingo.admin.commons.gen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

import com.bingo.admin.commons.gen.TemplateGenerate;
import com.bingo.admin.commons.gen.TemplateItemWrapper;
import com.bingo.admin.commons.gen.utils.StringUtils;

public class exportFileTemplate extends TemplateItemWrapper {

	private String templatePath;
	private String outputPath;

	public exportFileTemplate() {
	}

	public exportFileTemplate(String templatePath, String outputPath) {
		this.templatePath = templatePath;
		this.outputPath = outputPath;
	}

	@Override
	public String curTemplatePath(Class<?> entityClass) {
		return templatePath;
	}

	@Override
	public void doResultIO(StringWriter stringWriter,
			TemplateGenerate templateGenerate) {
		String filePath = changePath(templateGenerate);
		File file = new File(filePath);

		// 若文件夹不存在，则创建文件夹
		File dir = file.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}

		try {
			FileWriter fw = new FileWriter(filePath);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(stringWriter.toString());
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String changePath(TemplateGenerate templateGenerate) {
		String entityName = templateGenerate.getEntityClass().getSimpleName();
		String moduleName = templateGenerate.getModuleName();
		String urlEntityName = StringUtils.underscores(entityName).replace('_',
				'-');

		outputPath = templateGenerate.getProjectPath() + outputPath;

		return outputPath
				.replaceAll("\\{entityName\\}", entityName)
				.replaceAll("\\{varEntityName\\}",
						templateGenerate.getVarEntityName())
				.replaceAll("\\{moduleName\\}", moduleName)
				.replaceAll("\\{urlEntityName\\}", urlEntityName);
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

}
