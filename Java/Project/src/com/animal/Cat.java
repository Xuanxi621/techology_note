package com.animal;

public class Cat extends Animal{

    public Cat(String name, Integer age) {
        super(name, age);
    }

    public Cat() {
    }

    @Override
    public void eat() {
//        super.eat();
        System.out.println("吃鱼");
    }
}
