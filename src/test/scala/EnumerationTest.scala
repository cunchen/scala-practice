import Bread.Bread

object EnumerationTest {

    def main(args: Array[String]): Unit = {
        println(Bread.values.filter(_.toString.endsWith("wow")))


        for (va <- Bread.values)
            println(va +"" + va.id)
    }

    def isTerrier(b: Bread) = b.toString.endsWith("wow")

}

object Bread extends Enumeration {
    type Bread = Value
    val wi = Value(2)
    val doberman = Value("Doberman Pinscher")
    val yorkie = Value("Yorkshire Terrier")
    val scottie = Value("Scottish Terrier")
    val dane = Value(("Great Dane"))
    val portie = Value(("Portuguese Water Dog"))

    val wow = Value
}
