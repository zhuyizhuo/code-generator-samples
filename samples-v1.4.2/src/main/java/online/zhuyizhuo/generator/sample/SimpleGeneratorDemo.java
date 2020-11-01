package online.zhuyizhuo.generator.sample;

import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;

/**
 * 简单的生成器使用 sample
 *
 * 数据库配置在 simple_config.properties 配置文件中
 *
 * @author zhuo
 */
public class SimpleGeneratorDemo {

    public static void main(String[] args) {
        /** 生成的表 */
        String tableName = "sys_dict";
        Generator generator = new GeneratorBuilder()
                .properties("generate.table-names=" + tableName)
                .build("simple_config.properties");
        generator.generate();
    }
}
