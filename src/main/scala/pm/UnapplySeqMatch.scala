package pm

object UnapplySeqMatch {

    val nonEmptyList = List(1, 2, 3, 4, 5)
    val emptyList = Nil
    val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)

    def windows[T](seq: Seq[T]): String = seq match {
        case Seq(head1, head2, _*) => s"($head1, $head2），　" + windows(seq.tail)
        case Seq(head, _*) => s"（$head, _) " + windows(seq.tail)
        case Nil => "Nil"
    }

    def windows2[T](seq: Seq[T]): String = seq match {
        case Seq(head1, head2, head3, _*) => s"($head1, $head2, $head3), " + windows2(seq.tail)
        case Seq(head1, head2, _*) => s"($head1, $head2），　" + windows2(seq.tail)
        case Seq(head, _*) => s"（$head, _) " + windows2(seq.tail)
        case Nil => "Nil"
    }


    //优化window写法
    def windows3[T](seq: Seq[T]): String = seq match {
        case head1 +: head2 +: tail => s"($head1, $head2), " + windows3(seq.tail)
        case head +: tail => s"($head, _), " + windows3((tail))
        case Nil => "Nil"
    }

    def windows4[T](seq: Seq[T]) = {
//        seq.sliding(2).toSeq
        seq.sliding(2,3).toList
    }


    def main(args: Array[String]): Unit = {
        println("windows : ")
        for (seq <- Seq(nonEmptyList, emptyList, nonEmptyMap.toSeq)) {
            println(windows(seq))
        }

        println("\nwindows2 : ")
        for (seq <- Seq(nonEmptyList, emptyList, nonEmptyMap.toSeq)) {
            println(windows2(seq))
        }

        println("\nwindows3 : ")
        for (seq <- Seq(nonEmptyList, emptyList, nonEmptyMap.toSeq)) {
            println(windows3(seq))
        }

        println("\nwindows4 : ")
        for (seq <- Seq(nonEmptyList, emptyList, nonEmptyMap.toSeq)) {
            println(windows4(seq))
        }

        val s = Option(None)

        s.map(ss => println(ss))
    }

}
