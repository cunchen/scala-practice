package adt

import scala.annotation.tailrec


sealed trait List[+A]

case object Nil extends List[Nothing]

case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def append[A](li1: List[A], li2: List[A]): List[A] = li1 match {
    case Nil => li2
    case Cons(head, tail) => Cons(head, append(tail, li2))
  }

  def printList[A](li: List[A]): Unit = li match {
    case Nil => println()
    case Cons(head, tail) => {
      print(head)
      print("\t")
      printList(tail)
    }
  }


  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))

  }

  @tailrec
  def foldLeft[A, B](as: List[A], b: B)(f: (B, A) => B): B = as match {
    case Nil => b
    case Cons(head, tail) => foldLeft(tail, f(b, head))(f)
  }

  def sumFoldRight(ns: List[Int]): Int = {
    foldRight(ns, 0)((x, y) => x + y)
  }

  def producetFoldRight(ns: List[Double]): Double = {
    foldRight(ns, 1.0)(_ * _)
  }

  def sumFoldLeft(ns: List[Int]): Int = {
    foldLeft(ns, 0)(_ + _)
  }

  def produceFoldLeft(ns: List[Double]): Double = {
    foldLeft(ns, 1.0)(_ * _)
  }


}
