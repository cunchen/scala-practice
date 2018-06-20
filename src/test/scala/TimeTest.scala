import java.text.SimpleDateFormat
import java.util.Date

object TimeTest {

    def main(args: Array[String]): Unit = {
        println(System.currentTimeMillis())


        println(classOf[CaseClassTest].getName)


        val sdf = new SimpleDateFormat()

        val date = new Date(1528193132782l)
        println(sdf.format(date))
    }
}


case class CaseClassTest(a: String,
                        b: Long,
                         c: Int)