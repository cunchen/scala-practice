package com.example.mypkg

object NewTest {

    def main(args: Array[String]): Unit = {

        implicit val b = new B("yuyayun")


        A.f1()

        A.f1()(new B("yangchunhe"))
    }


}


class B(val b: String)

object A {


    def f1 ()(implicit b: B) = println(b.b)

}

