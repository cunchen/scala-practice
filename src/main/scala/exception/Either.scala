package exception


//相比Option， Option对象不能提供任何关于不反馈的信息
sealed trait InternalEither[+E, +A] {

    def map[B](f: A => B): InternalEither[E, B] = this match {
        case InternalRight(a) => InternalRight(f(a))
        case InternalLeft(a) => InternalLeft(a)
    }

    def flatMap[EE >: E, B](f: A => InternalEither[EE, B]): InternalEither[EE, B] = {
        this.map(f) match {
            case InternalRight(a) => a
            case InternalLeft(a) => InternalLeft(a)
        }
    }


    def orElse[EE >: E,B >: A](b: => InternalEither[EE, B]): InternalEither[EE, B] = this match {
        case InternalRight(a) => InternalRight(a)
        case _ => b
    }

    def map2[EE >: E, B, C](b: InternalEither[EE, B])(f: (A, B) => C): InternalEither[EE, C] = {
        this.flatMap(aa => b.map(bb => f(aa, bb)))
    }
}
//表示失败
case class InternalLeft[+E](value: E) extends InternalEither[E, Nothing]
//表示成功
case class InternalRight[+A](value: A) extends InternalEither[Nothing, A]




