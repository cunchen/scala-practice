package classtype

//上下文边界
package context {

    object Serialization {

        case class Rem[A](value: A) {
            def serialized: String = s"-- $value --"
        }

        type Writable[A] = A => Rem[A]

        implicit val fromInt: Writable[Int] = (i: Int) => Rem(i)
        implicit val fromFloat: Writable[Float] = (f: Float) => Rem(f)
        implicit val fromString: Writable[String] = (s: String) => Rem(s)
    }

    import Serialization._

    object RemoteConnection {
        def write[T: Writable](t: T): Unit =
            println(t.serialized)

    }

    object RunMain {
        def main(args: Array[String]): Unit = {
            RemoteConnection.write(100)
            RemoteConnection.write(3.14f)
            RemoteConnection.write("hello")
        }
    }

}

//视图边界
package view {

    object Serialization {

        case class Writable(value: Any) {
            def serialized: String = s"-- $value --"
        }

        implicit def fromInt(i: Int) = Writable(i)

        implicit def fromFloat(f: Float) = Writable(f)

        implicit def fromString(s: String) = Writable(s)
    }

    import Serialization._

    object RemoteConnection {
        def write[T <% Writable](t: T): Unit =   // def write[B](t: T)(implicit view: T => B): B
            println(t.serialized)
    }

    object RunMain {
        def main(args: Array[String]): Unit = {
            RemoteConnection.write(100)
            RemoteConnection.write(3.14f)
            RemoteConnection.write("hello")
        }
    }

}
