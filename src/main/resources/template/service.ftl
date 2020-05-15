package ${javaClassDefinition.service.fullPackage};

<#list tableInfo.importPackages as import>
    <#if import??>
import ${import};
    </#if>
</#list>
import ${javaClassDefinition.POJO.fullPackage}.${javaClassDefinition.POJO.className};

/**
 * description : ${tableInfo.tableNameCamelCase}Service <br/>
 * author : yizhuo
 */
public interface ${javaClassDefinition.service.className}{

	/**
	 * method: ${methodDescription.INSERT.methodName} <br>
	 * description: ${methodDescription.INSERT.comment} <br>
	 * time: ${classCommentInfo.createTime}
	 * @param ${javaClassDefinition.POJO.className?uncap_first}
	 * @return
	 */
	int ${methodDescription.INSERT.methodName}(${javaClassDefinition.POJO.className} ${javaClassDefinition.POJO.className?uncap_first});

	/**
	 * method: ${methodDescription.QUERY_BY_WHERE.methodName} <br>
	 */
    ${javaClassDefinition.POJO.className} ${methodDescription.QUERY_BY_WHERE.methodName} (${javaClassDefinition.POJO.className} ${javaClassDefinition.POJO.className?uncap_first});

}