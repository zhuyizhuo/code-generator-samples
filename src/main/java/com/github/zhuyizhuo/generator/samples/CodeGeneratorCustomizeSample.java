package com.github.zhuyizhuo.generator.samples;

import com.github.zhuyizhuo.generator.mybatis.enums.MethodEnums;
import com.github.zhuyizhuo.generator.mybatis.enums.ModuleEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;

/**
 * jdk version : 1.8 +
 * class: CodeGeneratorCustomizeSample <br>
 * description: 生成器扩展 <br>
 *     自定义模块名格式化
 *     自定义方法名格式化
 *
 * 本项目使用 mysql 数据库示例
 * 如使用 oracle 需添加 oracle 数据库驱动依赖
 * @author zhuo <br>
 */
public class CodeGeneratorCustomizeSample {

    public static void main(String[] args) {
        customizeGenerate();
    }

    private static void customizeGenerate() {
        Generator generator = new GeneratorBuilder()
                // 自定义模块生成文件名称  传入 ModuleEnums 即模块类型
                // 自定义模块名称优先级高于 配置 模块名称格式化
                .addModuleNameFormat(ModuleEnums.MAPPER, name ->
                        getRealName(name) +"Dao")
                // 自定义方法名格式化 传入 MethodEnums.ALL_METHOD 则适用 所有方法名格式化  需要配合配置使用
                .addMethodNameFormat(MethodEnums.ALL_METHOD, name -> {
                    return getRealName(name);
                })
                // 如果单独指定了某方法名格式化  则优先级最高
                .addMethodNameFormat(MethodEnums.COUNT_BY_WHERE, name -> "countByWhere")
                .build("config.properties");
        generator.generate();
    }

    private static String getRealName(String name) {
        return GeneratorStringUtils.changeTableName2CamelFirstUpper(name, "_")
                .replaceAll("Sample", "");
    }
}
