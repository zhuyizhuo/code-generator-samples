package online.zhuyizhuo.generator.sample;

import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;

/**
 * 代码生成器 基础使用
 *
 * 数据库配置在 {@link /samples-v1.4.2/src/main/resources/simple_config.properties} 配置文件中
 *
 * @author zhuo
 */
public class SimpleGenerator {

    public static void main(String[] args) {
        /** 生成的表 */
        String tableName = "";
        Generator generator = new GeneratorBuilder()
                .properties("generate.table-names=" + tableName)
//                .build("simple_config.properties");
                .build("simple_config_oracle.properties");
        generator.generate();
    }
}
