package com.sda.carrental.hello;

public class Main {

    private Integer number;

    public static void main(String[] args) {
        Main obj = null;
        doSomething(obj);
    }

    private static void doSomething(Main obj) {
        System.out.println("obj.number = " + obj.number);
    }


}
