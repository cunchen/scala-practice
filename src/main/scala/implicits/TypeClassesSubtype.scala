package implicits

object TypeClassesSubtype {


    trait Stringizer[+T] {
        def stringize: String
    }

    implicit class AnyStringizer(a: Any) extends Stringizer[Any]{
        override def stringize: String = a match {
            case s: String => s
            case i: Int => (i * 10).toString
            case f: Float => (f * 10.1).toString
            case other => throw new UnsupportedOperationException(s"Can't stringize $other")
        }
    }

    def main(args: Array[String]): Unit = {
        val list: List[Any] = List(1, 2,2F, "three", 'symbol)
        list foreach { (x: Any) =>
            try {
                println(s"$x: ${x.stringize}")
            } catch {
                case e: UnsupportedOperationException => println(e)
            }

        }
    }



}
