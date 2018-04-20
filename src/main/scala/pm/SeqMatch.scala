package pm

object SeqMatch {

    def main(args: Array[String]): Unit = {
        test2()

    }


    //Seq 匹配测试
    def test1(): Unit = {
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


    def test2() = {

        val nonEmptyList = List(1, 2, 3, 4, 5)
        val nonEmptyVector = Vector(1, 2, 3, 4, 5)
        val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)

        def reverseSeqToString[T](l: Seq[T]): String = l match {
            case prefix :+ end => reverseSeqToString(prefix) + s" :+ $end"
            case Nil => "Nil"
        }

        for (seq <- Seq(nonEmptyList, nonEmptyVector, nonEmptyMap.toSeq)) {
            println(reverseSeqToString(seq))
        }
    }

    def test3() = {
        println(Nil :+ 1 :+ 2)      //左结合 List需要 O(n)时间复杂度,这两个方法都必须要从列表的头部遍历一遍。Vector,则需要 O(1) 的时间复杂度。
    }

}

case class Address(street: String, city: String, country: String)
case class Person(name: String, age: Int, address: Address)
