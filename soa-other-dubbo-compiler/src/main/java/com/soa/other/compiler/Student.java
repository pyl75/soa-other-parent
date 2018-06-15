package com.soa.other.compiler;

public class Student {
    private int age =10;
    private String name="Jack";

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //添加一个自定义方法
    public void printInfo(){
        System.out.println("begin!");
        System.out.println(name);
        System.out.println(age);
        System.out.println("over!");
    }
}
