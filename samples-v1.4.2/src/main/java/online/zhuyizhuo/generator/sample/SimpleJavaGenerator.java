package online.zhuyizhuo.generator.sample;

import com.github.zhuyizhuo.generator.mybatis.enums.DbTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;

/**
 * 纯 java 配置 生成器
 *
 * @author zhuo
 */
public class SimpleJavaGenerator {

    public static void main(String[] args) {
        /** [必选]数据库配置 可选值参考 DbTypeEnums */
        DbTypeEnums type= DbTypeEnums.MYSQL;
        /** [必选]驱动包路径 */
        String driver= "com.mysql.cj.jdbc.Driver";
        /** [必选]数据库链接 需改为你的数据库链接 */
        String url= "jdbc:mysql://localhost:3306/management?useUnicode=true&serverTimezone=Asia/Shanghai";
        /** [必选]表空间 如 sql 为 select * from management.demo; 则 management 为表空间 */
        String tableSchema= "你的数据库表空间";
        /** [必选]数据库用户名  */
        String username= "你的数据库用户名";
        /** [必选]数据库密码  */
        String password= "你的数据库密码";

        /** [建议]要生成的表名 多个可用英文逗号隔开; 缺省为当前表空间下所有表; mysql 数据库表名大小写敏感 */
        String tableName = "";
        /** [建议]生成类所在的基础包路径 */
        String basePackage = "com.generator";
        /** [建议]生成的代码注释 作者 缺省值为 TODO */
        String author = "作者";

        /** [可选]可设置基础生成路径，默认生成至系统变量 user.dir 路径下，可修改为指定路径，例如多模块项目可将该项设置为子模块的根路径 */
        String outPath = System.getProperty("user.dir") + "/samples-v1.4.2";
        Generator generator = new GeneratorBuilder()
                .properties("db.type=" + type.name())
                .properties("db.driver=" + driver)
                .properties("db.url=" + url)
                .properties("db.table-schema=" + tableSchema)
                .properties("db.username=" + username)
                .properties("db.password=" + password)
                .properties("generate.table-names=" + tableName)
                .properties("generate.base.package=" + basePackage)
                .properties("generate.java.comment.author=" + author)
                .properties("generate.base.out-put-path=" + outPath)
                .build();
        generator.generate();
    }

}
