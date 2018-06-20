object LengthCompareTest {

    def main(args: Array[String]): Unit = {


        val seqStr = Seq("a", "b", "c")


        println("seqStr.lengthCompare(0)" + seqStr.lengthCompare(0))

        println("seqStr.lengthCompare(3)" + seqStr.lengthCompare(3))

        println("seqStr.lengthCompare(5)" + seqStr.lengthCompare(5))
    }

}
