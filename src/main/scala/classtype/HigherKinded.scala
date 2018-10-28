package classtype

import classtype.HigherKinded.Add

object HigherKinded {


    trait Add[T] {
        def add(t1: T, t2: T): T
    }

    object Add {
        implicit val addInt = new Add[Int] {
            def add(i1: Int, i2: Int): Int = i1 + i2
        }

        implicit val addIntPair = new Add[(Int, Int)] {
            def add(p1: (Int, Int), p2: (Int, Int)): (Int, Int) = (p1._1 + p2._1, p1._2 + p2._2)
        }
    }

    /**
      * 根据可使用add方法选用不同的隐式实例进行注入
      */
    def sumSeq[T: Add](seq: Seq[T]): T =
        seq reduce (implicitly[Add[T]].add(_, _))

    def main(args: Array[String]): Unit = {

        val x: (Int, Int) = (1, 2)

        println(Add.addIntPair)

        println(sumSeq(Vector(1 -> 10, 2 -> 20, 3 -> 30)))

        println(sumSeq(1 to 10))

        println(Option(2))
    }
}

/**
  * Reduce 相当于抽象出一个公共抽象Container　特征
  * sum方法允许公共Container参数注入
  */
object HigherKinded2 {

    import scala.language.higherKinds

    /**
      * M[T] 逆变(contravariant)是对Seq子类型兼容
      */
    trait Reduce[T, -M[T]] {
        def reduce(m: M[T])(f: (T, T) => T): T
    }

    /**
      * 与 Add 对象中定义的隐式值相比, seqReduce 和 optionReduce 都是隐式方法,而不是隐式
      * 值。这是因为我们需要从具体的实例中推导出类型参数 T 。我们无法像 Add 对象那样将
      * seqReduce 和 optionReduce 定义为隐式 val 类型。
      *
      * 在 seqReduce[T] = new Reduce[T, Seq] {...} 表达式中,我们并未设置 Seq 类型
      * 的类型参数。该类型参数将从 Reduce 的定义中推导出来。假如你直接设置了 Seq 的类型参
      * 数例如 new Reduce[T, Seq[T]] ,你会得到这样一条让人困惑的错误信息 Seq[T] takes no
      * type parameters, expected: one。
      */
    object Reduce{
        implicit def seqReduce[T] = new Reduce[T, Seq] {
            def reduce(seq: Seq[T])(f: (T, T) => T): T = seq reduce f
        }

        implicit def optionReduce[T] = new Reduce[T, Option] {
            def reduce(opt: Option[T])(f: (T, T) => T): T = opt reduce f
        }
    }

    /**
      * sum 方法的实现并非没有意义。与之前一样,我们定义了上下文边界 T: Add 。我们也希望
      * 定义 M[T] 的上下文边界,如 M[T] : Reduce 。不过我们无法做到这点,因为 Reduce 特征包
      * 含两个类型参数,而上下文边界只适用于包含单参数的场景。
      */
    def sum[T: Add, M[T]](container: M[T])(implicit red: Reduce[T, M]): T =
        red.reduce(container)(implicitly[Add[T]].add(_,_))


    def main(args: Array[String]): Unit = {
        println(sum(Vector(1 -> 10, 2 -> 20, 3 -> 30)))

        println(sum(1 to 10))

        println(sum(Option(2)))
    }
}


object HigherKinded3 {

    object ReduceImplicts {

        implicit val reduceInt = (x: Int, y: Int) => x + y
        implicit val reduceIntPair = (x: (Int, Int), y: (Int, Int)) => (x._1 + y._2, x._2 + y._2)
    }


    def sum[T](seq: Seq[T])(implicit f: (T, T) => T) = seq reduce f


    import ReduceImplicts._

    def main(args: Array[String]): Unit = {
        println(sum(Vector(1 -> 10, 2 -> 20, 3 -> 30)))

        println(sum(1 to 10))

    }

}

/**
  * 在之前的示例中,我们需要使用隐式方法,使得 Scala 能够推导出类型参数 T 的值,而现
  * 在我们仅仅定义了一些隐式实例,因此在调用 reduce 方法之前,Scala 无法推导出 T 的值。
  * 修改后的 sum 方法也变得更加简洁,调用 sum 方法将返回相同的结果
  */
object HigherKinded4 {

    trait Reduce1[-M[_]] {
        def reduce[T](m: M[T])(f: (T, T) => T): T
    }

    object Reduce1 {
        implicit val seqReduce = new Reduce1[Seq] {
            def reduce[T](seq: Seq[T])(f: (T, T) => T): T = seq reduce f
        }

        implicit val optionReduce = new Reduce1[Option] {
            def reduce[T](opt: Option[T])(f: (T, T) => T): T = opt reduce f
        }
    }
}

