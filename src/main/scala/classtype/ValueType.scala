package classtype

/**
  *
  * 这里值类型不是AnyVal子类
  * 值类型包括:参数类型、单例类型、类型映射、类型标识符、复合类型、既存类型、元组
类型、函数类型以及中缀类型。除了常规的写法之外,Scala 还为最后三种类型提供了更为
方便的简写语法,因此我们会对这三种类型进行回顾。在对这几种类型进行讲解的同时,
我们会讲述一些之前未谈及的具体细节。
  */
class ValueType {


    //元祖类型
    val t1: Tuple3[String, Int, Double] = ("one", 2, 3.14)
    val t2: (String, Int, Double) = ("one", 2, 3.14)

    //函数类型
    val f1: Function2[Int,Double,String] = (i,d) => s"int $i, double $d"
    val f2: (Int,Double) => String = (i,d) => s"int $i, double $d"


    //中缀类型
    val left1: Either[String,Int] = Left("hello")
    val left2: String Either Int = Left("hello")
    val right1: Either[String,Int] = Right(1)
    val right2: String Either Int = Right(2)

    val xll1: Int Either Double Either String = Left(Left(1))
    //val xll2: (Int Either Double) Either String = Left(Left(1))
    val xll2: (Int Either Double) Either String = Left(Left(1))
    //xll2: Either[Either[Int,Double],String] = Left(Left(1))

    val xlr1: Int Either Double Either String = Left(Right(3.14))
    //xlr1: Either[Either[Int,Double],String] = Left(Right(3.14))

    val xlr2: (Int Either Double) Either String = Left(Right(3.14))
//    xlr2: Either[Either[Int,Double],String] = Left(Right(3.14))

    val xr1:
        Int Either Double Either String = Right("foo")
//    xr1: Either[Either[Int,Double],String] = Right(foo)

    val xr2: (Int Either Double) Either String = Right("foo")
//    xr2: Either[Either[Int,Double],String] = Right(foo)

    val xl: Int Either (Double Either String) = Left(1)
//    xl: Either[Int,Either[Double,String]]= Left(1)

    val xrl: Int Either (Double Either String) = Right(Left(3.14))
//    xrl: Either[Int,Either[Double,String]] = Right(Left(3.14))

    val xrr: Int Either (Double Either String) = Right(Right("bar"))
//    xrr: Either[Int,Either[Double,String]] = Right(Right(bar))
}
