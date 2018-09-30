package com.example.mypkg;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;

public class AnnotationTestJava {

    public static void main(String[] args) {

        Class<InnerTest> cl = InnerTest.class;
        Annotation[] annotations = cl.getAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            System.out.println(annotations[i].toString());
        }

        AnnotatedType annotations2 = cl.getAnnotatedSuperclass();
        System.out.println(annotations2.toString());

        AnnotatedType[] annotations3 = cl.getAnnotatedInterfaces();
        for (int i = 0; i < annotations3.length; i++) {
            System.out.println(annotations3.toString());
        }



    }


    @SubInterface
    class InnerTest {

    }
}
