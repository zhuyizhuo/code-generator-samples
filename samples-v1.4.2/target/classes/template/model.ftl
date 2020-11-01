package ${javaClassDefinition.MODEL.fullPackage};

<#list tableInfo.importPackages as import>
	<#if import??>
import ${import};
	</#if>
</#list>

/**
 * 自定义替换系统 MODEL 模板 修改注释信息
 */
public class ${javaClassDefinition.MODEL.className} {

<#list tableInfo.columnLists as colm>
	<#if colm??>
	/** ${colm.columnComment} */
	private ${colm.javaDataType} ${colm.javaColumnName};
	</#if>
</#list>

<#list tableInfo.columnLists as colm>
	<#if colm??>
	public ${colm.javaDataType} get${colm.javaColumnName?cap_first}() {
		return ${colm.javaColumnName};
	}

	public void set${colm.javaColumnName?cap_first}(${colm.javaDataType} ${colm.javaColumnName}) {
		this.${colm.javaColumnName} = ${colm.javaColumnName};
	}
	
	</#if>
</#list>
}