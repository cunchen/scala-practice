object CaseClassTest {

    def main(args: Array[String]): Unit = {

        val a = new A(11)

        println(a)

        val b = new B(22)
        println(b)

        println(A.toString())


        for {
            x <- Seq(1, 2, 2.7, "one", "two", 'four)
        } {
            val str = x match {
                case 1
                => "int 1"
                case i: Int
                => "other int: "+i
                case d: Double => "a double: "+x
                case "one"
                => "string one"
                case s: String => "other string: "+s
                case unexpected => "unexpected value: " + unexpected
            }
            println(str)
        }

        for {
            x <- Seq(1, 2, 2.7, "one", "two", 'four)
        } {
            val str = x match {
                case 1
                => "int 1"
                case _: Int
                => "other int: "+x
                case _: Double => "a double: "+x
                case "one"
                => "string one"
                case _: String => "other string: "+x
                case _
                => "unexpected value: " + x
            }
            println(str)
        }


        //带有逻辑判断的匹配
        for {
            x <- Seq(1, 2, 2.7, "one", "two", 'four)
        } {
            val str = x match {
                case _: Int | _: Double => "a number: "+x   //或匹配
                case "one"
                => "string one"
                case _: String
                => "other string: "+x
                case _
                => "unexpected value: " + x
            }
            println(str)
        }
    }

    def checkY(y: Int) = {
        for {
            x <- Seq(99, 100, 101)
        } {
            val str = x match {
                case `y` => "found y!"      //变量需要使用反引号
                case i: Int => "int: "+i
            }
            println(str)
        }
    }
}

case class A(id: Int)
class B(id: Int)

