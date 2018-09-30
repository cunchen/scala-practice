package implicits

object ToMapImplicity {

    def main(args: Array[String]): Unit = {

        val s1 = Seq(1, 2, 3)

        for (s <- s1)
            println(s)

        val s2 = Some(2)

        for (s <- s2)
            println(s)

    }

//    trait TraversableOnce[+A] {
//        self =>
//
//        def toMap[T, U](implicit ev: <:<[A, (T, U)]): Map[T, U] = {
//
//            val b = Map.newBuilder[T, U]
//            for (x <- self)
//                b += x
//            b.result()
//        }
//
//    }

}
