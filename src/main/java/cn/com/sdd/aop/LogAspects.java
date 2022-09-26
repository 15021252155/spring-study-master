package cn.com.sdd.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @ClassName LogAspects
 * @Author suidd
 * @Description 日志切面类
 * @Aspect：告诉Spring，当前类是一个切面类
 * @Date 21:18 2020/11/5
 * @Version 1.0
 **/
@Aspect
public class LogAspects {

    //抽取公共的切入点表达式
    //1、在本类中引用的话，直接写方法名
    //2、在其他类中使用，全路径名称：cn.com.sdd.aop.LogAspects.pointCut()
    @Pointcut("execution(public int cn.com.sdd.aop.MathCalculator.*(..))")
    public void pointCut() {
    }

    //@Before("public int cn.com.sdd.aop.MathCalculator.div(int,int)")
    //@Before("public int cn.com.sdd.aop.MathCalculator.*(..)")
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" + joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));

        Object[] args = joinPoint.getArgs();
        System.out.println("运行开始...参数是{" + Arrays.asList(args) + "}");
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());


    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "运行结束...");
    }

    //JoinPoint joinPoint,必须在参数表的第一位
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println(joinPoint.getSignature().getName() + "正常返回...返回结果是{" + result + "}");
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logError(JoinPoint joinPoint, Exception exception) {
        System.out.println(joinPoint.getSignature().getName() + "异常返回...异常信息{" + exception + "}");
    }
}
