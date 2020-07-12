package ${javaClassDefinition.service.fullPackage};

<#list tableInfo.importPackages as import>
    <#if import??>
import ${import};
    </#if>
</#list>
import ${javaClassDefinition.MODEL.fullPackage}.${javaClassDefinition.MODEL.className};

/**
 * description : ${tableInfo.tableNameCamelCase}Service <br/>
 * author : yizhuo
 */
public interface ${javaClassDefinition.service.className}{

	/**
	 * method: ${methodDescription.INSERT.methodName} <br>
	 * description: ${methodDescription.INSERT.comment} <br>
	 * time: ${classCommentInfo.createTime}
	 * @param ${javaClassDefinition.MODEL.className?uncap_first}
	 * @return
	 */
	int ${methodDescription.INSERT.methodName}(${javaClassDefinition.MODEL.className} ${javaClassDefinition.MODEL.className?uncap_first});

	/**
	 * method: ${methodDescription.QUERY_BY_WHERE.methodName} <br>
	 */
    ${javaClassDefinition.MODEL.className} ${methodDescription.QUERY_BY_WHERE.methodName} (${javaClassDefinition.MODEL.className} ${javaClassDefinition.MODEL.className?uncap_first});

}