package classtype

/**
  * preview {@link classtype.HigherKinded}
  * 类型 Lambda 指的是嵌入在另一函数中的函数,它只作用于类型级。假如我们需要使用的
  * 参数化类型中包含了太多的类型参数,便可以使用类型 Lambda 进行处理。
  */
object TypeLambda {

    trait Functor[A, +M[_]] {
        def map2[B](f: A => B): M[B]
    }

    object Functor {

        implicit class SeqFunctor[A](seq: Seq[A]) extends Functor[A, Seq] {
            def map2[B](f: A => B): Seq[B] = seq map f
        }

        implicit class OptionFunctor[A](opt: Option[A]) extends Functor[A, Option] {
            def map2[B](f: A => B): Option[B] = opt map f
        }

        /**
          * 运用类型映射机制将类型 λ 从参数化类型中取
          * 出(参照类型头像　{@link TypeProjections})。 λ 是带有嵌入类型参数的 Map 的别名,而 Scala 会通过后
          * 续的代码推导出嵌入的类型参数。
          *
          * 以下示例中MapFunctor2，Map必须包含上下文边界，但是存在的问题是Functor[A, +M[_]]中　+M[]只允许存在一个参数
          * 而Map此时的参数需要包含两个参数，因此这里使用　**类型Lambda** 匹配
          */
        implicit class MapFunctor[K, V1](mapV1: Map[K, V1]) extends Functor[V1, ({type L[a] = Map[K, a]})#L] {
            def map2[V2](f: V1 => V2): Map[K, V2] = mapV1 map {
                case (k, v) => (k, f(v))
            }
        }
//        implicit class MapFunctor2[K, V1](mapV1: Map[K, V1]) extends Functor[V1, Map[K, V1]] {
//            def map2[V2](f: V1 => V2): Map[K, V2] = mapV1 map {
//                case (k, v) => (k, f(v))
//            }
//        }

    }

    import Functor._
    def main(args: Array[String]): Unit = {
        println(List(1, 2, 3) map2 (_ * 2))
        println(Option(2) map2 (_ * 2))

        val m = Map("one" -> 1, "two" -> 2, "three" -> 3)
        println(m map2 (_ * 2))
    }

}
