package implicits

import scala.collection.immutable.List

object ImplicityMethod {

    def main(args: Array[String]): Unit = {
        val my = MyList(List(1, 3, 5, 2, 5))

        println(my.sortBy1(i => -i))
        println(my.sortBy2(i => -i))
    }
}

case class MyList[A](list: List[A]) {


    def sortBy1[B](f: A => B)(implicit  ord: Ordering[B]): List[A] =
        list.sortBy(f)(ord)

    def sortBy2[B: Ordering](f: A => B): List[A] =
        list.sortBy(f)(implicitly[Ordering[B]])


}
