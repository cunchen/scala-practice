package exception

object OptionTest {


    // 平均值
    def mean(xs: Seq[Double]): Option[Double] = {
        if(xs.isEmpty) None
        else Some(xs.sum / xs.length)
    }


    // 方差
    def variance(xs: Seq[Double]): Option[Double] = {
        mean(xs) flatMap(m => mean(xs.map(x => math.pow(x - m, 2))))

    }

    def lift[A, B](f: A => B): Option[A] => Option[B] = {
        _ map f
    }

    def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
        a.flatMap( aa => b.map(bb => f(aa, bb)))
    }

    // <- 绑定
    // 编译器会将绑定操作转换为 flatMap，对最后一个绑定和yield会转换为map调用
    def map2For[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
        for {
            aa <- a
            bb <- b
        }yield f(aa, bb)
    }

    def map3[A, B, C, D](a: Option[A], b: Option[B], c: Option[C])(f: (A, B, C) => D): Option[D] = {

        a.flatMap( aa => b.flatMap(bb => c.map(cc => f(aa, bb, cc))))
    }

    def map3For[A, B, C, D](a: Option[A], b: Option[B], c:Option[C])(f: (A, B, C) => D): Option[D] = {
        for {
            aa <- a
            bb <- b
            cc <- c
        }yield f(aa, bb, cc)
    }


    //TODO Review
    def sequence[A](as: List[Option[A]]): Option[List[A]] = {

        as match {
            case Nil => Some(Nil)
            case h :: t => h flatMap(hh => sequence(t).map(hh :: _))

                //h flatMap (hh => sequence(t) map (hh :: _))
        }
    }

    //TODO review
    def traverse[A, B](a: List[A])(f: A=> Option[B]): Option[List[B]] = {

        a match {
            case Nil => Some(Nil)
            case h :: t => f(h).flatMap(bb => traverse(t)(f).map(bb :: _))
        }
    }

    val abs0: Option[Double] => Option[Double] = lift(math.abs)

    def main(args: Array[String]): Unit = {

        println(abs0(Some(-1.2)))


        println("123" + None)
        println("map2 :" + map2(Some(123), None)(_.toString + _ toString))

        println("sequence :" + sequence(List(Some("a"), Some("b"), Some("c"))))

        println("traverse :" + traverse(List(1,2,3,4,5,6))(x =>Some(x + 1)))

    }

}

case class Employee(name: String, department: String) {

    def lookupByName(name: String): Option[Employee] = ???

    val joeDepartment: Option[String] = lookupByName("Joe").map(_.department)
}
