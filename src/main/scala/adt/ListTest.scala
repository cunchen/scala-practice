package adt

import scala.annotation.tailrec


object ListTest {

  def removeFirst[A](list : List[A]) : List[A] = list match {
    case Nil => Nil
    case Cons(_, tail) => tail
  }


  def addFirst[A](head : A, list : List[A]): List[A] =
    Cons(head, list)


  def drop[A](list : List[A], n: Int): List[A] = list match{
    case Nil => Nil
    case Cons(head, tail) => {
      if(n == 0)
        Cons(head, tail)
      else
        drop(tail, n - 1)
    }
  }

  def dropWhile[A](list: List[A], f: A => Boolean) : List[A] = list match {
    case Nil => Nil
    case Cons(head, tail) => {
      if( f(head) ) dropWhile(tail, f)   //符合判断条件删除当前节点，直接返回后续节点的递归
      else Cons(head, dropWhile(tail, f))   //不符合不删除，构建后续节点的递归
    }
  }

  def dropWhileCurry[A](List: List[A])(f: A => Boolean) : List[A] = List match {
    case Nil => Nil
    case Cons(head, tail) => {
      if( f(head) ) dropWhileCurry(tail)(f)
      else Cons(head, dropWhileCurry(tail)(f))
    }
  }

  def dropLast[A](list: List[A]) : List[A] = list match {
    case Nil => Nil
    case Cons(head, tail) => {
      tail match {
        case Cons(_, tt) => {
          if( tt != Nil)
            Cons(head, dropLast(tail))
          else
            Cons(head, Nil)
        }
      }
    }
  }

  def printList[A](li : List[A]): Unit = li match {
    case Nil => println()
    case Cons(head, tail) => {
      print(head)
      print("\t")
      printList(tail)
    }
  }

  def foldRight[A, B](li : List[A], b : B)(f: (A, B)=> B) : B = li match {
    case Nil => b
    case Cons(head, tail) => f(head, foldRight(tail, b)(f))
  }



  def foldLeft[A, B](li : List[A], b: B)(f: (B, A)=> B) : B = li match {
    case Nil => b
    case Cons(head, tail) => {
      val re = f(b, head)
      val fl = foldLeft(tail, re)(f)
      fl
    }
  }

  def length(li : List[Int]) : Int =  {
    foldLeft(li, 0)((a, b) => a + 1)
  }

  def sumLeft(li : List[Int]) : Int = {
    foldLeft(li, 0)(_+_)
  }

  def productLeft(li : List[Double]) : Double = {
    foldLeft(li, 1.0)(_*_)
  }

  def revert[A](li: List[A]): List[A] = li match {

    case Nil => Nil
    case Cons(head, tail) => {

      @tailrec
      def go(p : List[A], pre : List[A]): List[A] = {
        p match {
          case Nil => pre
          case Cons(h, t) => {
            go(t, Cons(h, pre))
          }
        }
      }

      go(tail, Cons(head, Nil))
    }
  }

  def append[A](a1: List[A], a2: List[A]): List[A] = {
    foldRight(a1, a2)((a, b) => Cons(a, b))
  }



  //Mark
  def combineArray[A](as : List[List[A]]) : List[A] = {
    foldRight(as, Nil:List[A])(append)
  }

  def elemAdd1(li: List[Int]): List[Int] = {
    foldRight(li, Nil : List[Int])((a : Int, b  : List[Int]) => Cons[Int](a + 1, b))
  }


  def double2String(li: List[Double]): String = {
    foldRight(li, "")((x, y) => x.toString + y)
  }

  def map[A, B](as: List[A])(f: A => B): List[B] = {
    foldLeft(as, Nil: List[B])((y, x) => {
      val fx = f(x)
      val re = Cons(fx, y)
      re
    })
  }


  def filter[A](as: List[A])(f: A=> Boolean): List[A] = {

    foldRight(as, Nil: List[A])((x, y) => {
      if (f(x)) y
      else Cons(x, y)
    })
  }


  def int2StringFoldRight[A](as: List[A]): String = {
    foldRight(as, "-")(_+_)
  }

  def int2StringFoldLeft[A](as: List[A]): String = {
    foldLeft(as, "-")(_+_)
  }

  def flatMap[A, B](as: List[A])(f: A => List[B]): List[B] = {
    combineArray(map(as)(f))
  }

  def filterByFlatMap[A](as: List[A])(f: A=> Boolean): List[A] = {
    flatMap(as)((a: A) => {
      if(f(a))
        Nil
      else
        List(a)
    })
  }

  def hasSubsequence[A] (sup: List[A], sub: List[A]): Unit = {

//    sup.
  }



  def main(args: Array[String]): Unit = {

    val list = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Cons(6, Nil))))))

    printList(list)

    //test removeFirst
    printList(removeFirst(list))
    println("removeFirst\n")

    //test addFirst
    printList(addFirst(0, list))
    println("addFirst\n")


    printList(drop(list, 2))
    println("drop\n")

    printList(dropWhile(list, (x: Int)=> x%2!=0))
    println("drop\n")

    printList(dropWhileCurry(list)(x=> x%2==0))
    println("drop\n")

    printList(dropLast(list))
    println("dropLast\n")

    println(int2StringFoldRight(list))
    println("dropLastFoldRight\n")

    println(int2StringFoldLeft(list))
    println("int2StringFoldLeft\n")

    println(length(list))
    println("length\n")


    println(sumLeft(list))
    println("sumLeft\n")

    printList(revert(list))
    println("revert\n")

    printList(append(list, List(2,3,4,5)))
    println("append\n")

    printList(combineArray(List(List(3, 2, 1), List( 5, 6, 7))))
    println("combineArray\n")

    printList(elemAdd1(list))
    println("elemAdd1\n")

    printList(map(list)((x) => {
      if(x%2 ==0) 0
      else x
    }))
    println("map\n")


    printList(filter(list)( x => x%2 == 0))
    println("filter\n")

    printList(flatMap(list)( x => {
      if(x%2 == 0)
        List(9, 9, 9)
      else
        List(x)

    }))
    println("flatMap\n")

    printList(filterByFlatMap(list)( x => x%2 == 0))
    println("filterByFlatMap")
  }

}



