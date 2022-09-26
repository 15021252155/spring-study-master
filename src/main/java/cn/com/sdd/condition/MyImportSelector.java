package cn.com.sdd.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @ClassName MyImportSelector
 * @Author suidd
 * @Description 自定义ImportSelector
 * @Date 23:08 2020/11/1
 * @Version 1.0
 **/
public class MyImportSelector implements ImportSelector {
    /**
     * 返回值，就是要导入到容器中的组件全类名
     *
     * @param annotationMetadata 当前标注@ImportSelector注解类的所有注解信息
     * @return
     */
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"cn.com.sdd.bean.Red","cn.com.sdd.bean.Blue"};
        // return new String[0];
    }
}
