package chapter5LazyEvaluate

import chapter4Exception.{None, Option, Some}

sealed trait MyStream[+A] {


    def toList(): List[A] = {
        foldRight( Nil: List[A])(_ :: _)
    }


    def take(n: Int): MyStream[A] = {

        def go(ls : MyStream[A], int: Int): MyStream[A] = ls match {
            case Empty => Empty
            case Cons(h, t) => if(int <= 0) Empty else  Cons(h, () => go(t(), int -1))
        }

        go(this, n)
    }

    def take2(n: Int): MyStream[A] = {

        var int = n;
        foldLeft(Empty: MyStream[A])((y, x) => {
            int = int - 1
            if(int <= -1)
                y
            else
                Cons( () => x, () => y)
        })
    }

    def takeWhile(p: A=> Boolean): MyStream[A] = {
        foldRight( Empty: MyStream[A])((x, y) => if(p(x)) Cons(() => x, () => y) else y)
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

    def map[B](f: A => B): MyStream[B] = {
        foldRight(Empty: MyStream[B])((x, y) => {
            Cons(() => f(x), () => y)
        })
    }

    def filter(f: A => Boolean): MyStream[A] = {
        foldRight(Empty: MyStream[A])((x, y) => {
            if(f(x))
                Cons(() => x, () => y)
            else
                y })
    }

    def faltMap[B](f: A => MyStream[B]): MyStream[B] = {
        foldRight(Empty: MyStream[B])((x, y) => f(x).append(y))
    }


    //TODO question
    def append[B>:A](s: => MyStream[B]): MyStream[B] = {
        foldRight(s: MyStream[B] )((x, y) => Cons(() => x, () => y))
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

case object Empty extends MyStream[Nothing]


//一个非空的MyStream由head和tail组成，head和tail都是非严格求值。由于技术限制，参数必须声明为thunk，而传名参数
case class Cons[+A](h: () => A, t: () => MyStream[A]) extends MyStream[A] {
    //用于创建空MyStream的只能构造器
    def cons[A](hd: => A, ti: => MyStream[A]): MyStream[A] = {
        lazy val head = hd                  //惰性求值缓存，避免重复求值
        lazy val tail = ti
        Cons(() => head, () => tail)
    }

    def empty[A]: MyStream[A] = Empty


    def headOption: Option[A] = this match {
        case empty => None
        case Cons(h, _) => Some(h())            //对h thunk显示调用h()强制求值
    }

    //根据多个参数构建一个MyStream
    def apply[A](as: A*): MyStream[A] =
        if (as.isEmpty) Empty else Cons(() => as.head, () => apply(as.tail: _*))

}
