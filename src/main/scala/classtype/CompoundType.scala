package classtype

import classtype.demo2.Subject

private class CompoundType {


    trait T1
    trait T2
    class C
    //复合类型
    val c = new C with T1 with T2
}



//类型细化是复合类型的附加部分
/**
  * List<C> listOfC = ...
  *     java.util.Collections.sort(listOfC, new Comparator<C>() {
  *     public int compare(C c1, C c2) {...}
  *     public boolean equals(Object obj) {...}
  * });
  *
  * 我们“细化”类型 Comparator 用于创建一个新的类型。JVM 会在字节码中为它合成一个唯一的名字。
  */
private class TypeRefinement {

    //Scala 更进一步,它会合成新类型,并利用新的类型反映我们添加的东西。例如:回顾上一节的结构化类型,我们注意 REPL 返回的类型(对输出做了排版)
    val subject = new demo2.Subject {
        type State = Int
        protected  var count = 0
        def increment(): Unit = {
            count += 1
            notifyyObservers(count)
        }
    }


    trait Logging{
        def log(message: String): Unit = println(s"Log: $message")
    }

    //为了从外部访问细化后的特性或成员,你需要使用反射
    val subject2 = new Subject with Logging {
        override type State = this.type
    }

}