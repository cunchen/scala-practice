package com.example.mypkg

import java.lang.reflect.AnnotatedElement

object AnnotationTest {

    def main(args: Array[String]): Unit = {

        val cl = new AnnotatedClass()
        val ae = cl.getClass.asInstanceOf[AnnotatedElement]

        ae.getAnnotations.foreach(println)
        ae.getDeclaredAnnotations.foreach(println)


        println(s"ae.getAnnotation(classOf[ParentInterface]) [${ae.getAnnotation(classOf[ParentInterface])}]")
        println(s"ae.getDeclaredAnnotation(classOf[ParentInterface]) [${ae.getDeclaredAnnotation(classOf[ParentInterface])}]")



    }
}


@SubInterface
private class AnnotatedClass(){}


