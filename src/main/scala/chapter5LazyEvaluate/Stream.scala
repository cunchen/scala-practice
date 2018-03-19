package chapter5LazyEvaluate

import chapter4Exception.{None, Option, Some}

sealed trait Stream[+A] {

    def toList(): List[A] = {
        foldRight( Nil: List[A])((x, y) => x:: y)
    }


    def take(n: Int): Stream[A] = {

        def go(ls : Stream[A], int: Int): Stream[A] = ls match {
            case Empty => Empty
            case Cons(h, t) => if(int <= 0) Empty else  Cons(h, () => go(t(), int -1))
        }

        go(this, n)
    }

    def take2(n: Int): Stream[A] = {

        var int = n;
        foldLeft(Empty: Stream[A])((y, x) => {
            int = int - 1
            if(int <= -1)
                y
            else
                Cons( () => x, () => y)
        })
    }

    def takeWhile(p: A=> Boolean): Stream[A] = {
        foldRight( Empty: Stream[A])((x, y) => if(p(x)) Cons(() => x, () => y) else y)
    }


    def exists(p: A => Boolean): Boolean = this match {
        case Cons(h, t) => p(h()) || t().exists(p)
        case _ => false
    }

    def exists2(p: A => Boolean): Boolean = {
        foldRight(false)((x, y) => p(x) || y)
    }

    def headOptionFold: Option[A] = {
        foldRight(None: Option[A])((x, y) => Some(x))
    }

    def map[B](f: A => B): Stream[B] = {
        foldRight(Empty: Stream[B])((x, y) => Cons(() => f(x), () => y))
    }

    def filter(f: A => Boolean): Stream[A] = {
        foldRight(Empty: Stream[A])((x, y) => if(f(x)) Cons(() => x, () => y) else y )
    }

    def faltMap[B](f: A => Stream[B]): Stream[B] = {
        foldRight(Empty: Stream[B])((x, y) => f(x).append(y))
    }


    //TODO question
    def append[B>:A](s: => Stream[B]): Stream[B] = {
        foldRight(s: Stream[B] )((x, y) => Cons(() => x, () => y))
    }


    private def foldRight[C](c: C)( f: (A, C) => C): C = this match {
        case Empty => c
        case Cons(h, t) => f(h(), t().foldRight(c)(f))
    }

    private def foldLeft[C]( c: C)(f: (C, A) => C): C = this match {
        case Empty => c
        case Cons(h, t) => t().foldLeft(f(c, h()))(f)
    }

}

case object Empty extends Stream[Nothing]


//一个非空的stream由head和tail组成，head和tail都是非严格求值。由于技术限制，参数必须声明为thunk，而传名参数
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A] {
    //用于创建空stream的只能构造器
    def cons[A](hd: => A, ti: => Stream[A]): Stream[A] = {
        lazy val head = hd                  //惰性求值缓存，避免重复求值
        lazy val tail = ti
        Cons(() => head, () => tail)
    }

    def empty[A]: Stream[A] = Empty


    //根据多个参数构建一个Stream
    def apply[A](as: A*): Stream[A] =
        if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))


    def headOption: Option[A] = this match {
        case empty => None
        case Cons(h, _) => Some(h())            //对h thunk显示调用h()强制求值
    }


}
