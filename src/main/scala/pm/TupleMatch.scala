package pm

object TupleMatch {

    def main(args: Array[String]): Unit = {
        val langs = Seq(
            ("Scala", "Martin" , "Odersky"),
            ("Coljure", "Rich", "Hickey"),
            ("Lisp", "John", "McCarthy")
        )

        for (tuple <- langs) {
            tuple match {
                case ("Scala", _, _) => println("Found Scala")
                case (lang, first, last) => println(s"Found other language: $lang ($first, $last)")
            }
        }
    }
}
