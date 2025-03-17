package com.edu.poly.lab1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.edu.poly.lab1.bean.Student;

public class Lambdal {

    // của demo 3
    static List<Student> list = Arrays.asList(
            new Student("John", true, 8.0),
            new Student("Marry", false, 9.0),
            new Student("Tom", true, 7.0),
            new Student("Jerry", true, 6.0),
            new Student("Lily", false, 5.0));

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
    }
    @FunctionalInterface
    interface Demo4Inter {
        void m1(int x);
        default void m2(){}
        public static void m3(){}

    }
    private static void demo4() {
       Demo4Inter o = x -> System.out.println(x);
         o.m1(100);
    }

    private static void demo3() {
        Collections.sort(list, (s1, s2) -> {
            return -s1.getMarks().compareTo(s2.getMarks());
        });
        list.forEach(sv -> {
            System.out.println("++++++++++++++++++++++++");
            System.out.println(" >> Name :" + sv.getName());
            System.out.println(" >> Marks :" + sv.getMarks());
            System.out.println(" >> Giới Tính :" + (sv.isGender() ? "Nam" : "Nữ"));
        });
    }

    private static void demo2() {
        List<Student> list = Arrays.asList(
                new Student("John", true, 8.0),
                new Student("Marry", false, 9.0),
                new Student("Tom", true, 7.0),
                new Student("Jerry", true, 6.0),
                new Student("Lily", false, 5.0));
        list.forEach(s -> {
            System.out.println(" >> Name :" + s.getName());
            System.out.println(" >> Marks :" + s.getMarks());
            System.out.println(" >> Giới Tính :" + (s.isGender() ? "Nam" : "Nữ"));
        });
    }

    private static void demo1() {
        List<String> list = Arrays.asList("Java", "C#", "C++", "PHP", "Python");
        list.forEach(s -> System.out.println(s));

    }
}
