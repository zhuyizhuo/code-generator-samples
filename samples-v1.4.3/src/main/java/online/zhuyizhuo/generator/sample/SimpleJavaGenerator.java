package online.zhuyizhuo.generator.sample;

import com.github.zhuyizhuo.generator.enums.DbTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * 无配置文件 代码生成器使用
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
        String tableSchema= "management";
        /** [必选]数据库用户名  */
        String username= "root";
        /** [必选]数据库密码  */
        String password= "123456";

        /** [建议]要生成的表名 多个可用英文逗号隔开; 缺省为当前表空间下所有表; mysql 数据库表名大小写敏感 */
        String tableName = "";
        /** [建议]生成类所在的基础包路径 */
        String basePackage = "com.generator";
        /** [建议]生成的代码注释 作者 缺省值为 TODO */
        String author = "作者";

        /** [可选]可设置基础生成路径，默认生成至当前项目的根路径，例如多模块项目可将该项设置为子模块的根路径 */
        String outPath = System.getProperty("user.dir") + "/samples-v1.4.3";
        Map<String, String> properties = new HashMap<>();
        properties.put("db.type", type.name());
        properties.put("db.driver", driver);
        properties.put("db.url", url);
        properties.put("db.table-schema", tableSchema);
        properties.put("db.username", username);
        properties.put("db.password", password);

        properties.put("generate.table-names", tableName);
        properties.put("generate.base.package", basePackage);
        properties.put("generate.java.comment.author", author);
        properties.put("generate.base.out-put-path", outPath);
        Generator generator = new GeneratorBuilder()
                .properties(properties)
                .build();
        generator.generate();
    }

}
