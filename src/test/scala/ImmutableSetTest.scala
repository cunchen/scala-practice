object ImmutableSetTest {

    def main(args: Array[String]): Unit = {


        val s1 = Set[String]("a")


        println(s1 + "b")



        val s2 = Set[Int]()

        val ls = List(1, 2, 3, 4)
        ls.foreach{ s =>
            s2 + s
        }

        println(s2 + 3)
    }

}
