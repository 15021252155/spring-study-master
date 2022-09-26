package cn.com.sdd.aop;

/**
 * @ClassName MathCalculator
 * @Author suidd
 * @Description 计算器业务逻辑类，用来测试AOP
 * @Date 21:15 2020/11/5
 * @Version 1.0
 **/
public class MathCalculator {
    public int div(int i, int j) {
        System.out.println("MathCalculator div...");
        return i / j;
    }
}
