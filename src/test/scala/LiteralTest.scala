object LiteralTest {

    def main(args: Array[String]): Unit = {
        val test =
            """First line\n
Second line\t
Fourth line"""
        println(test)

        hello("world")
    }


    def hello(name: String) = s"""Welcome!
                                Hello, $name!
                                * (Gratuitous Star!!)
                                |We're glad you're here.
                                | Have some extra whitespace.""".stripMargin
}
