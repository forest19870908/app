package com.cs.app.mybatis;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MyCommentGenerator extends DefaultCommentGenerator {

	private boolean suppressChineseComments = false;

	@Override
	public void addModelClassComment(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {

		topLevelClass.addAnnotation("import com.cs.core.Annotation.Column;");

	}

	@Override
	public void addFieldComment(Field field,
			IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (suppressChineseComments || introspectedColumn.getRemarks() == null) {
			return;
		}
		String comment = introspectedColumn.getRemarks();
		String required = introspectedColumn.isNullable() ? "false" : "true";
		String type = introspectedColumn.getJdbcTypeName();
		type = getType(type);
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("name", comment == null ? "" : comment);
		map.put("required", required);
		String value = "@Column(";
		Iterator<Entry<String, String>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Entry<String, String> entry = entries.next();
			if (entries.hasNext()) {
				value = value + entry.getKey() + "=\"" + entry.getValue()
						+ "\",";
			} else {
				value = value + entry.getKey() + "=\"" + entry.getValue()
						+ "\")";
			}
		}
		field.addAnnotation(value);
		field.addJavaDocLine("/** " + comment + " */");
	}

	private String getType(String JDBCType) {
		if (JDBCType.equals("CHAR") || JDBCType.equals("VARCHAR")
				|| JDBCType.equals("LONGVARCHAR")) {
			return "textbox";
		}
		if (JDBCType.equals("DATE")) {
			return "datebox";
		}
		if (JDBCType.equals("TIME")) {
			return "timespinner";
		}
		if (JDBCType.equals("DATETIME")) {
			return "datetimebox";
		}
		if (JDBCType.equals("TIMESTAMP")) {
			return "datetimespinner";
		}
		return null;
	}

}
