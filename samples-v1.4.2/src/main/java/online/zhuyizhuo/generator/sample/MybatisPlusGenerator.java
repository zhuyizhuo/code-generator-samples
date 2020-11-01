package online.zhuyizhuo.generator.sample;

import com.github.zhuyizhuo.generator.mybatis.enums.ModuleEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.CustomizeModuleInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.JavaModuleInfo;

/**
 * 生成 mybatis plus + lombok + swagger 的代码样式,数据库使用 mysql 为例
 * 1. 修改数据库名 database 及用户名密码
 * 2. 修改需要生成的表名 tableName 及需要生成到的包路径 basePackage
 * 3. 执行 main 方法
 * @author zhuo
 */
public class MybatisPlusGenerator {

    public static void main(String[] args) {
        /** 输出路径  */
        String outputPath = "/src/main/java/";
        /** 库名  */
        String database = "你的数据库名";
        /** 数据库链接  */
        String dbUrl = "jdbc:mysql://localhost:3306/"+database+"?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai";
        /** 数据库用户名  */
        String dbUserName = "你的数据库用户名";
        /** 数据库密码  */
        String dbPassword = "你的数据库密码";
        /** 要生成的表名 多个可用英文逗号隔开 */
        String tableName = "你要生成的表名";
        /** 生成类所在的基础包路径 */
        String basePackage = "com.generator.mybatis.plus";
        /** [推荐] 代码注释 作者 */
        String author = "zhuo";
        Generator generator = new GeneratorBuilder()
                .properties("db.url=" + dbUrl,
                        "db.table-schema=" + database,
                        "generate.table-names=" + tableName,
                        "db.username=" + dbUserName,
                        "db.password=" + dbPassword,
                        "generate.java.comment.author=" + author)
                //替换系统 POJO 模板
                .addJavaTemplate(new JavaModuleInfo(ModuleEnums.POJO.toString(),
                        "/template/mybatisplus/LombokMybatisPlusModel.ftl",
                        basePackage + ".entity",
                        outputPath,
                        "{0}"))
                //替换系统 MAPPER 模板
                .addJavaTemplate(new JavaModuleInfo(ModuleEnums.MAPPER.toString(),
                        "/template/mybatisplus/MybatisPlusMapper.ftl",
                        basePackage+ ".repository",
                        outputPath,
                        "{0}Mapper"))
                //替换系统 XML 模板
                .addCustomizeModuleTemplate(new CustomizeModuleInfo(ModuleEnums.XML.toString(),
                        "/template/mybatisplus/mybatis_xml.ftl",
                        System.getProperty("user.dir")+"/src/main/resources/customize/xml/{0}.xml",
                        "{0}"))
                // 新增名为 service 的 java 模板
                .addJavaTemplate(new JavaModuleInfo("service",
                        "/template/mybatisplus/MybatisPlusService.ftl",
                        basePackage+ ".service",
                        outputPath,
                        "{0}Service"))
                // 新增名为 serviceImpl 的 java 模板
                .addJavaTemplate(new JavaModuleInfo("serviceImpl",
                        "/template/mybatisplus/MybatisPlusServiceImpl.ftl",
                        basePackage+ ".service.impl",
                        outputPath,
                        "{0}ServiceImpl"))
                // 新增名为 vo 的 java 模板
                .addJavaTemplate(new JavaModuleInfo("vo",
                        "/template/LombokSwaggerVO.ftl",
                        basePackage + ".vo",
                        outputPath,
                        "{0}VO"))
                .build();
        generator.generate();
    }
}
