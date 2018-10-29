package future

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

object FutureTest {

    def main(args: Array[String]): Unit = {
        val futures = (0 to 9) map {
            i => Future{
                val s = i.toString
                print(s)
                s
            }
        }

        val f = Future.reduce(futures)((x1, x2) => x1 + x2)

        val n = Await.result(f, Duration.Inf)

        println(f)
    }

}
