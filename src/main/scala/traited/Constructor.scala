//package traited
//
///**
//  * 构造方式 从左到右
//  */
//object Constructor {
//
//    def main(args: Array[String]): Unit = {
//        println(s"Creating C12:")
//        new C12
//        println(s"After Creating C12")
//    }
//
//    trait T1 {
//        println(s" in T1 : x = $x")
//        val x = 1
//        println(s" in T1 : x = $x")
//    }
//
//    trait T2  extends Base12{
//        println(s" in T2 : x = $y")
//        val y = "T2"
//        println(s" in T2 : x = $y")
//    }
//
//    class Base12 {
//        println(s" in Base12: b = $b")
//        val b = "Base12"
//        println(s" in Base12: b = $b")
//    }
//
//
//    //字段不可以冲突
////    trait T3  {
////        println(s" in T3 : x = $b")
////        val b = "T3"
////        println(s" in T3 : x = $b")
////    }
//
//    trait T4 extends C1{
//        println(s" in T4 : x = $z")
//        val z = "T4"
//        println(s" in T4 : x = $z")
//    }
//
//
//    class C1 {
//        println(s" in C1: b = $a")
//        val a = "C1"
//        println(s" in C1: b = $a")
//    }
//
//    class C12 extends C1 with T1 with T2 {
//        println(s" in C12: c = $c")
//        val c = "C12"
//        println(s" in C12: c = $c")
//    }
//
//
//    class C22 extends C1 with T1 with T4 {
//        println(s" in C22: c = $c")
//        val c = "C22"
//        println(s" in C22: c = $c")
//    }
//}
//
