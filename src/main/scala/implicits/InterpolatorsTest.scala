package implicits


object InterpolatorsTest {

    implicit  class jsonForStringContext(val sc: StringContext) {
        def json(values: Any*) {

            val keyRE = """^[\s{,]*(\S+):\s*""".r
            val keys = sc.parts map {
                case keyRE(key) => key
                case str => str
            }
            val kvs = keys zip values
//            JSONObject(kvs.toMap)

        }
    }

    def main(args: Array[String]): Unit = {

        val a = "a"
        val b = "b"
        val c = "c"

//        val sc = StringContext("{\"a\": $a, \"b\": $b, \"c\":$c}")
//        val sc = StringContext("\"a\": $a, \"b\": $b, \"c\":$c")

        val name = "a"
        val book = "book2"

        println(json"{name: $name, book: $book}")
//        println(sc.json(a, b, c))
    }

}
