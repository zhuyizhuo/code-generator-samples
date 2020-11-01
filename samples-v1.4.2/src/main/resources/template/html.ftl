<html>

表名: <input type="text" value="${tableInfo.tableName}">
字段:
<table>
    <tr>
        <td>字段 java 类型</td>
        <td>字段名</td>
        <td>字段描述</td>
    </tr>
    <#list tableInfo.columnLists as colm>
        <#if colm??>
    <tr>
        <td>${colm.javaDataType}</td>
        <td>${colm.javaColumnName}</td>
        <td>${colm.columnComment}</td>
    </tr>
        </#if>
    </#list>
</table>


</html>