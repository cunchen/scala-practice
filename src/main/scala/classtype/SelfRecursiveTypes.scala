package classtype

/**
  * 递归类型也被称为 F-bounded 多态类型
  *
  *
  * Java 中的实现是
  * public abstract class Enum<E extends Enum<E>>
  * extends Object
  * implements Comparable<E>, Serializable
  */
object SelfRecursiveTypes {


    trait Parent[T <: Parent[T]] {
        def make: T
    }

    case class Child1(s: String) extends Parent[Child1] {
        def make: Child1 = Child1(s"Child1: make $s")
    }

    case class Child2(s: String) extends Parent[Child2] {
        def make: Child2 = Child2(s"Child2: make $s")
    }

    def main(args: Array[String]): Unit = {


        println(Child1("c1"))
        println(Child1("c1").make)
        println(Child2("c2"))
        println(Child2("c2").make)

//        compose
    }
}
