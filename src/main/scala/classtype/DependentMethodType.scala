package classtype

private object DependentMethodType {

    import concurrent.ExecutionContext.Implicits.global
    import scala.concurrent.duration._
    import scala.concurrent.{Await, Future}

    case class LocalResponse(statusCode: Int)

    case class RemoteResponse(message: String)

    /**
      * 以 Computation 为父节点的这组密封类(sealed class)提供了服务所需要的所有计算类型:
包括了本地处理和远程处理。由于所需要执行的工作也封装到一个 Future 对象中,因此这
些计算将以异步的方式执行。本地处理将返回对应的 LocalResposne 对象,而远程处理则
返回对应的 RemoteResponse 对象:

      由 于 LocalResponse 与 RemoteResponse 毫 不 相 关, 因 此 handle 方 法 并 未 返 回
它们共有的某个父类实例。 handle 方法另辟蹊径,它将依据输入参数决定返回类型。由
于 无 法 通 过 类 型 检 查 的 缘 故, 当 输 入 参 数 类 型 为 RemoteComputation 时, 不 可 能 返 回
LocalResponse ,反之亦然
      */
    sealed trait Computation {
        type Response
        val work: Future[Response]
    }

    case class LocalComputation(work: Future[LocalResponse]) extends Computation {
        type Response = LocalResponse
    }

    case class RemoteComputation(work: Future[RemoteResponse]) extends Computation {
        type Response = RemoteResponse
    }


    object Service {
        def handle(computation: Computation) = {

            val duration = Duration(2, SECONDS)
            println(Await.result(computation.work, duration))
        }
    }

    def main(args: Array[String]): Unit = {
        Service.handle(LocalComputation(Future(LocalResponse(0))))

        Service.handle(RemoteComputation(Future(RemoteResponse("remote call"))))
    }
}
