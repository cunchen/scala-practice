import chapter3.{Cons, ListTest, Nil}

object Test {

    def main(args: Array[String]): Unit = {
        val ll = Cons("1", Cons("2", Nil))

        //其实内部实现对方法的重写
        val res = ListTest.map(ll)(new TestClass().setId)
        println("new TestClass().setId : \t" + res)

        val res2 = ListTest.map(ll)(new TestClass().setId(_))
        println("new TestClass().setId(_) : \t" +res2)
    }
}
class TestClass {
    var idd = ""
    def setId(id: String): TestClass = {
        idd = id
        this
    }

    override def toString: String = idd
}