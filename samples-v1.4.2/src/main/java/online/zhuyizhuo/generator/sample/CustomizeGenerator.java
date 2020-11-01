package online.zhuyizhuo.generator.sample;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zhuyizhuo.generator.mybatis.enums.DbTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.enums.ModuleEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.CustomizeModuleInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.JavaModuleInfo;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.LogUtils;

/**
 * jdk version : 1.8 +
 * 生成器扩展 <br>
 *     替换系统模板
 *     自定义模块模板
 *     无配置文件生成
 *     打印生成对象信息
 *
 * 本项目使用 mysql 数据库示例
 * 如使用 oracle 需添加 oracle 数据库驱动依赖
 * @author zhuo <br>
 */
public class CustomizeGenerator {

    public static void main(String[] args) {
        customizeGenerate();
    }

    private static void customizeGenerate() {
        // 打印生成对象信息 可根据日志编写模板 模板使用 freemarker 编写, 使用 freemarker 语法 取值、循环、判断即可
        LogUtils.setLogService(object ->
            System.out.println("生成元信息:\n" + JSON.toJSONString(object))
        );
        /** 输出路径  */
        String outputPath = "/src/main/java/";
        /** 数据库类型 */
        String dbType = DbTypeEnums.MYSQL.name();
        /** 数据库驱动 */
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        /** 库名 */
        String tableSchema = "management";
        /** 数据库链接 */
        String dbUrl = "jdbc:mysql://localhost:3306/"+tableSchema+"?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai";
        /** 数据库用户名  */
        String dbUserName = "root";
        /** 数据库密码  */
        String dbPassword = "123456";
        /** 要生成的表名 多个可用英文逗号隔开 */
        String tableNames = "sys_dict";
        /** 生成类所在的基础包路径 */
        String basePackage = "com.generator.mybatis.plus";
        /** [推荐] 代码注释 作者 */
        String author = "zhuo";

        // 自定义模块类型
        String customizeModuleType = "page";
        new GeneratorBuilder()
                .properties("db.type=" + dbType,
                        "db.driver=" + dbDriver,
                        "db.url=" + dbUrl,
                        "db.table-schema="+ tableSchema,
                        "db.username=" + dbUserName,
                        "db.password=" + dbPassword)
                .properties("generate.table-names=" + tableNames,
                        // 自定义属性  使用 #{属性名} 可动态获取
                        "basePackage=" + basePackage,
                        "base-out-put-path=" + outputPath,
                        "generate.java.module.mapper.package=#{basePackage}.mapper",
                        "generate.java.module.mapper.out-put-path=#{base-out-put-path}",
                        "generate.java.module.model.package=#{basePackage}.model",
                        "generate.java.module.model.out-put-path=#{base-out-put-path}",
                        "generate.resources.xml.out-put-path=#{base-out-put-path}/"+
                                basePackage.replaceAll("\\.","/")+"/xml/")
                // 自定义模板生成 新增 service 模块
                .addJavaTemplate(new JavaModuleInfo("service",
                        "/template/service.ftl",
                        basePackage + ".service",
                        // java 类实际生成全路径为 {generate.base.out-put-path}/{outputPath}/{classPackage}/{className}.java
                        outputPath,
                        "{0}Service"))
                // 使用自定义模板
                .addJavaTemplate(new JavaModuleInfo("MODEL",
                        "/template/model.ftl",
                        basePackage + ".model",
                        // java 类实际生成全路径为 {generate.base.out-put-path}/{outputPath}/{classPackage}/{className}.java
                        outputPath,
                        "{0}"))
                .addJavaTemplate(new JavaModuleInfo("vo",
                        "/template/LombokSwaggerVO.ftl",
                        basePackage + ".vo",
                        outputPath,
                        "{0}VO"))
                // 自定义生成页面
                .addCustomizeModuleTemplate(new CustomizeModuleInfo(customizeModuleType,
                "template/html.ftl",
                System.getProperty("user.dir")+"/src/main/resources/customize/page/{0}.html",
                "{0}"))
                // 自定义的模块 也可以自定义模块名格式化  moduleType 需一致
                .addModuleNameFormat(customizeModuleType, name -> GeneratorStringUtils.changeTableName2CamelFirstUpper(name,"_") + "Page")
                .build()
                .generate();
    }

}