package caseclass

class UnaryMethod {





}

case class Complex(real: Double) {
    def unary_- : Complex = Complex(-real)

    def -(other: Complex) = Complex(real - other.real)
}


object Test{
    def main(args: Array[String]): Unit = {
        val a = new Complex(10.0)

        val b = -a

        val c = b - a

        println(a)
        println(b)
        println(c)
    }
}