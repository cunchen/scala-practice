package loop

object LoopTest {

    def main(args: Array[String]): Unit = {

//        val s1 = new AbleForLoopA("a")
//
//        for (s <- s1) println(s)

        val s2 = new AbleForLoopB("b")

        for (s <- s2) println(s)

        val names = Seq("Kitty", "Tom", "Luke", "Kit")


        names.filter(_.startsWith("K")).foreach(println)

//        for (name <- names
//             if name.startsWith("K")
//             if name.endsWith("t")
//        ) {
//            println(name)
//        }


        println("----------------------")
        names.takeWhile(!_.startsWith("L")).foreach(println)
        println("----------------------")
        names.dropWhile(_.startsWith("K")).foreach(println)

    }

    class AbleForLoopA(name: String)

    class AbleForLoopB(name: String) {

        def foreach[U](f: String => U) = if (!name.isEmpty) f(name)

    }

}
