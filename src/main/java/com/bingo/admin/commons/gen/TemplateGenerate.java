package com.bingo.admin.commons.gen;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.bingo.admin.commons.gen.annotations.GeneratedClassInfo;
import com.bingo.admin.commons.gen.factorys.EntityPropertyFactory;
import com.bingo.admin.commons.gen.property.EntityProperty;
import com.bingo.admin.commons.gen.utils.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Component;

@Component
public class TemplateGenerate {

	private String entityName;// 实体权限类名
	private String packageName;// 实体权限类名
	private String projectPath;// 实体权限类名
	private String moduleName;// 模块名称
	private String templateRootPath;// 模板的根节点路径
	private Map<String, String> preferences = new HashMap<String, String>();// 配置项
	private List<TemplateItem> items = new ArrayList<TemplateItem>();// 模板实体列表

	public TemplateGenerate() {
	}

	public TemplateGenerate(String entityName, String moduleName,
			List<TemplateItem> items, Properties prop) {
		this.entityName = entityName;
		this.moduleName = moduleName;
		if (items != null)
			this.items = items;
	}

	public TemplateGenerate(String entityName, String moduleName) {
		this.entityName = entityName;
		this.moduleName = moduleName;
	}

	public void initTemplate(){
		this.entityName="com.bingo.admin.entity.gen.Gen";
		this.projectPath="/Users/lidechen/Desktop/java/ideaWorkspace/admin";
		this.packageName="com.bingo.admin";
		this.moduleName="gen";
		this.templateRootPath="/Users/lidechen/Desktop/java/ideaWorkspace/admin/src/main/resources/templates/vm/";
		preferences.put("securityModuleName","自动生成");
		preferences.put("enableSecurity","true");

		items.add(new exportFileTemplate("html/list.html.vm","/src/main/resources/templates/view/{moduleName}/{varEntityName}-list.html"));
		items.add(new exportFileTemplate("html/add.html.vm","/src/main/resources/templates/view/{moduleName}/{varEntityName}-add.html"));
		items.add(new exportFileTemplate("html/edit.html.vm","/src/main/resources/templates/view/{moduleName}/{varEntityName}-edit.html"));
		items.add(new exportFileTemplate("js/list.js.vm","/src/main/resources/static/itemjs/{moduleName}/{varEntityName}-list.js"));
		items.add(new exportFileTemplate("js/add.js.vm","/src/main/resources/static/itemjs/{moduleName}/{varEntityName}-add.js"));
		items.add(new exportFileTemplate("js/edit.js.vm","/src/main/resources/static/itemjs/{moduleName}/{varEntityName}-edit.js"));
		items.add(new exportFileTemplate("java/Controller.java.vm","/src/main/java/com/bingo/admin/controller/{moduleName}/{entityName}Controller.java"));
		items.add(new exportFileTemplate("java/Service.java.vm","/src/main/java/com/bingo/admin/service/{moduleName}/I{entityName}Service.java"));
		items.add(new exportFileTemplate("java/ServiceImpl.java.vm","/src/main/java/com/bingo/admin/service/impl/{moduleName}/{entityName}Service.java"));
		items.add(new exportFileTemplate("java/Dao.java.vm","/src/main/java/com/bingo/admin/dao/{moduleName}/{entityName}Dao.java"));
	}

	/**
	 * 
	 * @description 初始化配置
	 * @reviewed_by
	 * @param
	 * @throw
	 */
	private void initVelocity() {
		Properties prop = new Properties();
		prop.put("file.resource.loader.path", templateRootPath);// 指定模版文件加载位置
		prop.put("input.encoding", "UTF-8");// 指定输入编码格式
		prop.put("output.encoding", "UTF-8");// 指定输出编码格式
		try {
			Velocity.init(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @description 代码生成(主要方法)
	 * @reviewed_by
	 * @param
	 * @throw
	 */
	public void generateCode() {
		initTemplate();
		if (items == null) {
			throw new RuntimeException("没有需要生成的模板项的类");
		}

		initVelocity();
		for (TemplateItem item : items) {
			VelocityContext context = new VelocityContext();
			InitContext(context);// 模板初始化
			item.SetItemContext(context, getEntityClass());// 模板项初始化
			StringWriter stringWriter = null;
			try {
				// 获取模板
				Template template = null;
				template = Velocity.getTemplate(item
						.curTemplatePath(getEntityClass()));

				// 处理结果
				stringWriter = new StringWriter();
				template.merge(context, stringWriter);
				stringWriter.flush();
				item.doResultIO(stringWriter, this);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (stringWriter != null) {
					try {
						stringWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 
	 * @description 初始化模板变量
	 * @reviewed_by
	 * @param @param context
	 * @throw
	 */
	private void InitContext(VelocityContext context) {
		Class<?> clazz = getEntityClass();
		context.put("entity", clazz);// 实体对象moduleName
		context.put("moduleName", moduleName);// 模块名称
		context.put("entityLabel", getEntityLabel(clazz));// 获取实体表名
		context.put("curTime",
				DateFormatUtils.format(new Date(), "yyyy年MM月dd日 hh:mm:ss"));// 当前时间
		context.put("propertyList", getPropertyList(clazz));// 属性列表
		// 实体类的变量名DeviceInfo-->deviceInfo
		context.put("varEntityName", getVarEntityName());
		// DeviceInfo-->device_info
		context.put("underEntityName",
				StringUtils.underscores(clazz.getSimpleName()));
		// DeviceInfo-->device-info
		context.put("urlEntityName",
				StringUtils.underscores(clazz.getSimpleName())
						.replace('_', '-'));
		context.put("packageName",packageName);
		context.put("author","bingo");
		context.put("datetime",new Date());
		// 初始化配置数据
		for (Entry<String, String> entry : preferences.entrySet()) {
			context.put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * 
	 * @description // 获取实体表名
	 * @reviewed_by
	 * @param @return
	 * @throw
	 */
	private String getEntityLabel(Class<?> clazz) {
		GeneratedClassInfo info = getGeneratedClassInfo(clazz);
		if (info != null)
			return info.label();
		return "";
	}

	/**
	 * 
	 * @description 获取对应的实体属性
	 * @reviewed_by
	 * @param @param typeClass
	 * @param @return
	 * @throw
	 */
	private List<EntityProperty> getPropertyList(Class<?> typeClass) {
		List<EntityProperty> list = new ArrayList<EntityProperty>();
		Field[] fields = typeClass.getDeclaredFields();
		for (Field field : fields) {
			EntityProperty item = EntityPropertyFactory
					.getEntityPropertyInstance().createEntityProperty(field);
			if (item != null)
				list.add(item);
		}
		return list;
	}

	/**
	 * 
	 * @description 获取注解信息
	 * @reviewed_by
	 * @param @param clazz 类或借口
	 * @param @return
	 * @throw
	 */
	private GeneratedClassInfo getGeneratedClassInfo(Class<?> clazz) {
		GeneratedClassInfo generatedClassInfo = clazz
				.getAnnotation(GeneratedClassInfo.class);
		if (generatedClassInfo == null) {
			return null;
		}
		return generatedClassInfo;
	}

	/**
	 * 
	 * @description 获取实体类的变量名
	 * @reviewed_by
	 * @param @return
	 * @throw
	 */
	public String getVarEntityName() {
		String simpleName = getEntityClass().getSimpleName();
		return StringUtils.getFirstLowerStr(simpleName);
	}

	/**
	 * 
	 * @description 通过权限类名获取实体对象
	 * @reviewed_by
	 * @param @return
	 * @throw
	 */
	public Class<?> getEntityClass() {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(entityName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clazz;
	}

	/**
	 * get、set
	 */
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getTemplateRootPath() {
		return templateRootPath;
	}

	public void setTemplateRootPath(String templateRootPath) {
		this.templateRootPath = templateRootPath;
	}

	public List<TemplateItem> getItems() {
		return items;
	}

	public void setItems(List<TemplateItem> items) {
		this.items = items;
	}

	public Map<String, String> getPreferences() {
		return preferences;
	}

	public void setPreferences(Map<String, String> preferences) {
		this.preferences = preferences;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
}
