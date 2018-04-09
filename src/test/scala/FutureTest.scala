import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.Source

object FutureTest {


    def sleep(millis: Long) = {
        Thread.sleep((millis))
    }

    def doWork(index: Int) = {
        sleep((math.random * 100).toLong)
        index
    }

    def main(args: Array[String]): Unit = {
        (1 to 5) foreach { index =>
            val future = Future {
                doWork(index)
            }
            future.onSuccess{
                case answer: Int => println(s"Success! returned: $answer")
            }

            future.onFailure{
                case th: Throwable => println(s"FAILURE! returned: $th")
            }
        }

        sleep(1000)
        println("Finito!")


        Source.fromFile("")
    }
}
