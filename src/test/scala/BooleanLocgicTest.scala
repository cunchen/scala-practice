object BooleanLocgicTest {

    def main(args: Array[String]): Unit = {

        println("""true^true:""" + (true^true))
        println("""true^false:""" + (true^false))
        println("""false^true:""" + (false^true))
        println("""false^false:""" + (false^false))

        println(System.currentTimeMillis())
    }
}
