package online.zhuyizhuo.generator.sample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zhuyizhuo.generator.mybatis.enums.ModuleEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.CustomizeModuleInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.JavaModuleInfo;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.LogUtils;

/**
 * jdk version : 1.8 +
 * class: online.zhuyizhuo.generator.sample.CodeGeneratorCustomizeTempleteSample <br>
 * description: 生成器扩展 <br>
 *     替换系统模板
 *     自定义模块模板
 *      无配置文件生成
 *      打印生成对象信息
 * 本项目使用 mysql 数据库示例
 * 如使用 oracle 需添加 oracle 数据库驱动依赖
 * @author zhuo <br>
 */
public class CodeGeneratorCustomizeTempleteSample {

    public static void main(String[] args) {
        customizeGenerate();
    }

    private static void customizeGenerate() {
        // 打印生成对象信息  可根据日志编写模板 模板使用 freemarker 编写, 使用 freemarker 语法 取值、循环、判断即可
        LogUtils.setLogService(object -> {
            try {
                System.out.println("生成元信息:\n" + new ObjectMapper().writeValueAsString(object));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        // 自定义模块类型
        String customizeModuleType = "page";
        String basePackage = "com.github.generator.template";
        String database = "你的数据库名";
        String dbUserName = "你的数据库用户名";
        String dbPassword = "你的数据库密码";
        String outputPath = "/src/main/java/";
        new GeneratorBuilder()
                .properties("db.type=MYSQL",
                        "db.driver=com.mysql.cj.jdbc.Driver",
                        "db.url=jdbc:mysql://localhost:3306/"+database+"?useUnicode=true&characterEncoding=utf-8" +
                                "&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false" +
                                "&serverTimezone=Asia/Shanghai",
                        "db.table-schema="+database,
                        "db.username=" + dbUserName,
                        "db.password=" + dbPassword)
                .properties("generate.table-names=sample_order,sample_user",
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
                // 使用自定义模板 替换系统模板  moduleType 与枚举 ModuleEnums 中模块名一致即可替换该模块
                .addJavaTemplate(new JavaModuleInfo(ModuleEnums.POJO.toString(),
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
