package chapter5LazyEvaluate

sealed trait Stream[+A] {

}

case object Empty extends Stream[Nothing]


//一个非空的stream由head和tail组成，head和tail都是非严格求值。由于技术限制，参数必须声明为thunk，而传名参数
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
    def cons[A](hd: => A, ti: => Stream[A]): Stream[A] = {
        lazy val head = hd
        lazy val tail = ti
        Cons(() => head, () => tail)
    }

    def empty[A]: Stream[A] = Empty

    def apply[A](as: A*): Stream[A] =
        if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
}
