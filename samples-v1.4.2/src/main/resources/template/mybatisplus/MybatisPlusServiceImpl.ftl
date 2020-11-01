package ${javaClassDefinition.serviceImpl.fullPackage};

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import ${javaClassDefinition.MAPPER.fullPackage}.${javaClassDefinition.MAPPER.className};
import ${javaClassDefinition.POJO.fullPackage}.${javaClassDefinition.POJO.className};
import ${javaClassDefinition.service.fullPackage}.${javaClassDefinition.service.className};

/**
 * ${tableInfo.tableComment} Service 实现类
 * time:    ${classCommentInfo.createTime} <br/>
 * @author  ${classCommentInfo.author} <br/>
 * @since   ${classCommentInfo.sinceVersion} <br/>
 */
@Service("${javaClassDefinition.serviceImpl.className?uncap_first}")
public class ${javaClassDefinition.serviceImpl.className} extends ServiceImpl<${javaClassDefinition.MAPPER.className}, ${javaClassDefinition.POJO.className}> implements ${javaClassDefinition.service.className} {

}