package ${javaClassDefinition.vo.fullPackage};

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
<#list tableInfo.importPackages as import>
	<#if import??>
import ${import};
	</#if>
</#list>

/**
 * table	: ${tableInfo.tableName} <br/>
 * description : ${tableInfo.tableComment} <br/>
 * time:    ${classCommentInfo.createTime} <br/>
 * @author  ${classCommentInfo.author} <br/>
 */
@Data
public class ${javaClassDefinition.vo.className} implements Serializable {
    private static final long serialVersionUID=1L;

<#list tableInfo.columnLists as colm>
	<#if colm??>
    @ApiModelProperty(value = "${colm.columnComment}")
	private ${colm.javaDataType} ${colm.javaColumnName};
	</#if>
</#list>

}