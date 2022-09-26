package cn.com.sdd.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @ClassName MyFilterType
 * @Author suidd
 * @Description TypeFilter实现类
 * 用来实现FilterType.CUSTOM 使用自定义规则
 * @Date 10:44 2020/6/6
 * @Version 1.0
 **/
public class MyTypeFilter implements TypeFilter {
    /**
     * @param metadataReader        读取到的当前正在扫描的类信息
     * @param metadataReaderFactory 可以获取到其他任何类信息
     * @return
     * @throws IOException
     */
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取当前类注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取当前正在扫描的类的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        //获取类资源信息（类的路径）
        Resource resource = metadataReader.getResource();

        //输出类名
        String className = classMetadata.getClassName();
        System.out.println("扫描的类---->" + className);

        //自定义一个规则，只要类名里包含字符串 “er”就认为匹配成功
        if (className.contains("er")) {
            return true;
        }

        return false;
    }
}
