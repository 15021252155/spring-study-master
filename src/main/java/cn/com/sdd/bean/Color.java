package cn.com.sdd.bean;

/**
 * @ClassName Color
 * @Author suidd
 * @Description TODO
 * @Date 22:59 2020/11/1
 * @Version 1.0
 **/
public class Color {
    private Car car;

    public Color(Car car) {
        this.car = car;
    }

    public Color() {
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Color{" +
                "car=" + car +
                '}';
    }
}
