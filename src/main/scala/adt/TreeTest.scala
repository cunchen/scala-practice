package adt

object TreeTest {

  def deepFirstSearch[A, B](tree: Tree[A])(f: A => B)(g: (B, B) => B):B = tree match{
      case Leaf(l) => f(l)
      case Branch(left, right) => g( deepFirstSearch(left)(f)(g), deepFirstSearch(right)(f)(g))
  }

  def countNodes[A](tree: Tree[A]): Int = {
    def go[A](tree: Tree[A], sum: Int): Int = tree match {
      case Leaf(v) => sum + 1
      case Branch(left, right) => sum + 1 + go(left, sum) + go(right, sum)
      case _ => 0
    }

    go(tree, 0)
  }

  def maximum(tree: Tree[Int]): Int =
    deepFirstSearch(tree)(l => l)(_ max _)

  def depth[A](tree: Tree[A]): Int =
    deepFirstSearch(tree)(_ => 1)(_.max(_) + 1)

  def map[A, B](tree: Tree[A])(f: A => B): Tree[B] = {
    deepFirstSearch(tree)( x => (Leaf(f(x)): Tree[B]))( (a, b) => Branch(a, b))
  }



  def main(args: Array[String]): Unit = {

    val tree = Branch(
                  Branch(
                    Branch
                      (Leaf(1),
                        Branch(
                          Leaf(7),
                          Branch(
                            Leaf(8),
                            Leaf(9)
                          ))),
                    Branch(
                      Leaf(34), Leaf(4))),
                  Branch(
                    Leaf(5), Leaf(6)))


    println(countNodes(tree))

    println("Max value :" + maximum(tree))

    println("Depth :" + depth(tree))

    println("Map :" + map(tree)(x => if(x%2 == 0) Branch(Leaf(1), Leaf(2)) else x))
  }
}
