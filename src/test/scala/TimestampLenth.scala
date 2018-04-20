import java.lang.Long

object TimestampLenth {

    def main(args: Array[String]): Unit = {

        val t1 = System.currentTimeMillis()
        println("result: " + (~t1).toHexString)

        Thread.sleep(1000)

        val t2 = System.currentTimeMillis()
        val c = (~(~t2)).toHexString
        println("t2: " + t2)
        println("t2.toHexString: " + t2.toHexString)
        println("(~t2).toHexString: " + (~t2).toHexString)
        println("(~(~t2)).toHexString: " + c)
        println("Long.parseLong((~(~t2)).toHexString).toHexString, 16): " + Long.parseLong(c).toHexString, 16)

    }
}
