package online.zhuyizhuo.generator.sample;

import com.github.zhuyizhuo.generator.mybatis.enums.MethodEnums;
import com.github.zhuyizhuo.generator.mybatis.enums.ModuleEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import org.apache.ibatis.type.JdbcType;

/**
 * jdk version : 1.8 +
 *  生成器扩展 <br>
 *     自定义模块名格式化
 *     自定义方法名格式化
 *
 * 本项目使用 mysql 数据库示例
 * 如使用 oracle 需添加 oracle 数据库驱动依赖
 * @author zhuo <br>
 */
public class CustomizeGeneratorSample {

    public static void main(String[] args) {
        customizeGenerate();
    }

    private static void customizeGenerate() {
        Generator generator = new GeneratorBuilder()
//                自定义类型映射
                .fieldType2JavaType("bit", Integer.class)
                .fieldType2JdbcType("bit", JdbcType.NUMERIC)
                // 自定义模块生成文件名称  传入 ModuleEnums 即模块类型
                // 自定义模块名称优先级高于配置文件中配置的模块名称格式化
                .addModuleNameFormat(ModuleEnums.MAPPER, name ->
                        getRealName(name) +"Dao")
                // 自定义方法名格式化 传入 MethodEnums.ALL_METHOD 则适用所有方法名格式化
                .addMethodNameFormat(MethodEnums.ALL_METHOD, name ->
                    getRealName(name))
                // 如果单独指定了某方法名格式化,则该方法名以单独指定的格式化方式处理
                .addMethodNameFormat(MethodEnums.COUNT_BY_WHERE, name -> "countByWhere")
                .build("simple_config.properties");
        generator.generate();
    }

    private static String getRealName(String name) {
        // 该方法为下划线转驼峰
        return GeneratorStringUtils.changeTableName2CamelFirstUpper(name, "_")
                .replaceAll("Sample", "");
    }
}
