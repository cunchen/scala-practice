object MethodTest {

    def main(args: Array[String]): Unit = {

        Circle(Point(0.0, 0.0), 2.3).draw(Point(1.0, 1.0))(f => println(s"ShapesDrawingActor: $f"))
    }
}

case class Point(x: Double, y: Double)

abstract class Shape() {


    def draw(offset: Point = Point(0.0, 0.0))(f: String => Unit): Unit =
        f(s"draw(offset = $offset, ${this.toString}")
}

case class Circle(center: Point, radius: Double) extends Shape

case class Rectangle(lowerLeft: Point, height: Double, width: Double) extends Shape

