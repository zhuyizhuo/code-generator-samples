package online.zhuyizhuo.generator.sample;

import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;

public class SimpleGenerator {

    public static void main(String[] args) {
        /** [建议]要生成的表名 多个可用英文逗号隔开; 缺省为当前表空间下所有表; mysql 数据库表名大小写敏感 */
        String tableName = "sys_dict";
        Generator generator = new GeneratorBuilder()
                .properties("generate.table-names=" + tableName)
                .build("simple_config.properties");
        generator.generate();
    }

}
