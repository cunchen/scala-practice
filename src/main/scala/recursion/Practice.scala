package recursion

object Practice {


  def curry[A, B, C](f : (A, B) => C) : A => (B => C) =
    (a : A) => {
      (b : B) => f(a, b)
    }

  def uncurry[A, B, C](f: A => B => C) : (A, B) => C =
    (a : A, b : B) => f(a)(b)

  def compose[A, B, C](f: B => C, g: A => B): A => C =
    (a : A) => f( g(a))

  def main(args: Array[String]): Unit = {

    //curry test
    println(curry[String, String, String]( (a, b) => a + b)("Hello ")("Scala"))


    //uncurry test
    println(uncurry[String, String, String]( (a : String) => ( (b : String) => a + b))("Scala ", "programming"))


    //compose test
    def bool2String(b : Boolean) : String =
      if (b)
        "right"
      else
        "wrong"

    println(compose[Boolean, String, String]( (b : String) => "The answer is %s".format(b), bool2String)(true))
    println(compose[Boolean, String, String]( (b : String) => "The answer is %s".format(b), bool2String)(false))
  }
}
