
object Test {

    def main(args: Array[String]): Unit = {
        val ll = List("1", "2")

        //其实内部实现对方法的重写
        val res = ll.map({
            println("map" +  _)
            new TestClass().setId
        })
        println("new TestClass().setId : \t" + res)

        val res2 = ll.map(new TestClass().setId(_))
        println("new TestClass().setId(_) : \t" +res2)
    }
}
class TestClass {
    var idd = ""
    def setId(id: String): TestClass = {
        println("setId " + id)
        idd = id
        this
    }

    override def toString: String = idd
}