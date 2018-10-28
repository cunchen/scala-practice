package classtype

/**
  *
  * 类型投影
  *
  *
  * 调用 Service.Log 和 Service1.Log 时,Scala 将分别查找名为 Service 和 Service1 的对象,
但是这些伴生对象却并不存在。
不过,我们可以使用 # 符号来对我们想查找的类型进行映射。第一次尝试未能通过类
型检查。尽管 Service.Log 和 ConsoleLogger 类型均为 Logger 类型的子类型,但是由于
Service.Log 是抽象类型,因此我们尚且不知道该类型是否确实是 ConsoleLogger 类型的子
类型。换言之,最后出现的 Service.Log 的具体定义可能是 Logger 类的另外一个子类,且
该子类与 ConsoleLogger 不兼容。


  最后提一句,我们日常工作中编写的那些简单的类型名称为类型标识符(type designator)。
这些类型标识符本质上是这些类型映射的缩写。下面列举了一些类型标识符和对应的类型映射,
该示例改编自《Scala 语言规范》的 3.2 节:


    Int                 // scala.type#Int
    scala.Int           // scala.type#Int
    package pkg {
        class MyClass
            type t      // pkg.MyClass.type#t
        }
    }




  */
object TypeProjections {
    trait Logger {
        def log(message: String): Unit
    }

    class ConsoleLogger extends Logger {
        def log(message: String): Unit = println(s"log: $message")
    }

    trait Service {
        type Log <: Logger
        val logger: Log
    }

    class Service1 extends Service {
        type Log = ConsoleLogger
        val logger: ConsoleLogger = new ConsoleLogger
    }

    /**
      * 代码中出现的两个 Logge 类型能被视为相同类型吗?答案是否定的。错误信息表示 Scala
      * 期望得到类型为 this.Logger 的 logger 对象。在 Scala 中,每一个 Service 实例的 logger
      * 属性类型都不相同。换言之, logger 的实际类型是路径相关的(path-dependent)。
      * @param args
      */
    def main(args: Array[String]): Unit = {
        //val l1: Service.Log = new ConsoleLogger       //wrong
        //val l2: Service1.Log = new ConsoleLogger      //wrong
        //val l3: Service#Log = new ConsoleLogger       //wrong
        val l4: Service1#Log = new ConsoleLogger
    }
}
