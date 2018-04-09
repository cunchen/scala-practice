package exception

sealed trait Either[+E, +A] {

    def map[B](f: A => B): Either[E, B] = this match {
        case Right(a) => Right(f(a))
        case Left(a) => Left(a)
    }

    def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = {
        this.map(f) match {
            case Right(a) => a
            case Left(a) => Left(a)
        }
    }


    def orElse[EE >: E,B >: A](b: => Either[EE, B]): Either[EE, B] = this match {
        case Right(a) => Right(a)
        case _ => b
    }

    def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = {
        this.flatMap(aa => b.map(bb => f(aa, bb)))
    }
}
//表示失败
case class Left[+E](value: E) extends Either[E, Nothing]
//表示成功
case class Right[+A](value: A) extends Either[Nothing, A]




