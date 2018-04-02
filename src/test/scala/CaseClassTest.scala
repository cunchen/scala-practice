object CaseClassTest {

    def main(args: Array[String]): Unit = {

        val a = new A(11)

        println(a)

        val b = new B(22)
        println(b)

        println(A.toString())

    }
}

case class A(id: Int)
class B(id: Int)

