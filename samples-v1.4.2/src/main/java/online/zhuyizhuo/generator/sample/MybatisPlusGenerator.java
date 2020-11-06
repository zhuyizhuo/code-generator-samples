package online.zhuyizhuo.generator.sample;

import com.github.zhuyizhuo.generator.mybatis.enums.DbTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.enums.ModuleEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.CustomizeModuleInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.JavaModuleInfo;

/**
 * 生成 mybatis plus + lombok + swagger 的代码样式,数据库使用 mysql 为例
 *
 * 1. 修改数据库名 database 及用户名密码
 * 2. 修改需要生成的表名 tableName 及需要生成到的包路径 basePackage
 * 3. 执行 main 方法
 *
 * 生成器 1.5.0 版本已内置 mybatis plus 相关模板
 * @author zhuo
 */
public class MybatisPlusGenerator {

    public static void main(String[] args) {

        /** [必选]数据库配置 可选值参考 DbTypeEnums */
        String dbType= DbTypeEnums.MYSQL.name();
        /** [必选]驱动包路径 */
        String dbDriver= "com.mysql.cj.jdbc.Driver";
        /** [必选]数据库链接 需改为你的数据库链接 */
        String dbUrl= "jdbc:mysql://localhost:3306/management?useUnicode=true&serverTimezone=Asia/Shanghai";
        /** [必选]表空间 如 sql 为 select * from management.demo; 则 management 为表空间 */
        String tableSchema= "你的数据库表空间";
        /** [必选]数据库用户名  */
        String dbUserName= "你的数据库用户名";
        /** [必选]数据库密码  */
        String dbPassword= "你的数据库密码";

        /** [建议]要生成的表名 多个可用英文逗号隔开; 缺省为当前表空间下所有表; mysql 数据库表名大小写敏感 */
        String tableNames = "";
        /** [建议]生成类所在的基础包路径 */
        String basePackage = "com.generator.mybatis.plus";
        /** [建议]生成的代码注释 作者 缺省值为 TODO */
        String author = "作者";
        /** [建议]输出路径 1.4.2 版本默认生成至项目根目录 可配置为 /src/main/java/  1.5.0 版本默认生成至 /src/main/java/ */
        String outputPath = "/src/main/java/";

        Generator generator = new GeneratorBuilder()
                .properties("db.type=" + dbType,
                        "db.driver=" + dbDriver,
                        "db.url=" + dbUrl,
                        "db.table-schema=" + tableSchema,
                        "generate.table-names=" + tableNames,
                        "db.username=" + dbUserName,
                        "db.password=" + dbPassword,
                        "generate.java.comment.author=" + author)
                //替换系统 POJO 模板  替换系统模板  moduleType 与枚举 ModuleEnums 中模块名一致即可替换该模块
                .addJavaTemplate(new JavaModuleInfo(ModuleEnums.POJO.toString(),
                        "/template/mybatisplus/LombokMybatisPlusModel.ftl",
                        basePackage + ".entity",
                        outputPath,
                        "{0}"))
                //替换系统 MAPPER 模板  替换系统模板 moduleType 与枚举 ModuleEnums 中模块名一致即可替换该模块
                .addJavaTemplate(new JavaModuleInfo(ModuleEnums.MAPPER.toString(),
                        "/template/mybatisplus/MybatisPlusMapper.ftl",
                        basePackage+ ".repository",
                        outputPath,
                        "{0}Mapper"))
                //替换系统 XML 模板 注意 xml 模板使用 CustomizeModuleInfo
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
