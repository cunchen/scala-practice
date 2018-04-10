package pm

object GuardMatch {

    def main(args: Array[String]): Unit = {


        for (i <- Seq(1, 2, 3, 4, 5)) {
            i match {
                case _ if i%2 == 0 => println((s"even: $i"))        // _ 代替变量名,因为已经有 i 可以作为变量名了
                case _ =>  println(s"odd: $i")
            }
        }
    }
}
