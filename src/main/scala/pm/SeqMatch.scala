package pm

object SeqMatch {





    def main(args: Array[String]): Unit = {

        val nonEmptySeq = Seq(1, 2, 3, 4, 5)
        val emptySeq = Seq.empty[Int]
        val nonEmptyList = List(1, 2, 3, 4, 5)
        val emptyList = Nil
        val nonEmptyVector = Vector(1, 2, 3, 4, 5)
        val emptyVector = Vector.empty[Int]
        val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)
        val emptyMap = Map.empty[String, Int]

        def seqToString[T](seq: Seq[T]): String = seq match {
            case head +: tail => s"$head +: " + seqToString(tail)
            case Nil => "Nil"
        }

        for (seq <- Seq(nonEmptySeq, emptySeq, nonEmptyList, emptyList, nonEmptyVector, emptyVector, nonEmptyMap.toSeq, emptyMap.toSeq)) {
            println(seqToString(seq))
        }




        val itemsCosts = Seq(("Pencil", 0.52), ("Paper", 1.35), ("Notebook", 2.43))
        val itemsCostsIndices = itemsCosts.zipWithIndex
        for (itemCostIndex <- itemsCostsIndices) {
            itemCostIndex match {
                case ((item, cost), index) => println(s"$index: $item costs $cost each")
            }
        }
    }

}
