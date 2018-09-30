package exception

import scala.util.{Try, Success, Failure}

//解决引用透明问题
object TryTest {

    def main(args: Array[String]): Unit = {


        val r1 = for  {
            i1 <- positive(5)
            i2 <- positive(10 * i1)
            i3 <- positive(25 * i2)
            i4 <- positive(2 * i3)
        } yield (i1 + i2 + i3 + i4)

        println(r1)

        val r2 = for  {
            i1 <- positive(5)
            i2 <- positive(-1 * i1)
            i3 <- positive(25 * i2)
            i4 <- positive(-2 * i3)
        } yield (i1 + i2 + i3 + i4)

        println(r2)
    }

    def positive(i: Int): Try[Int] = Try {
        assert(i > 0, s"nonpositive number $i")
        i
    }

    def positive2(i: Int): Try[Int] = {
        if(i > 0) Success(i)
        else Failure(new AssertionError("assertion failed"))
    }

}
