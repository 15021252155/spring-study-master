package cn.com.sdd.bean;

/**
 * @ClassName Student
 * @Author suidd
 * @Description 学生实体类
 * @Date 21:07 2020/11/1
 * @Version 1.0
 **/
public class Student {
    private String name;
    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
