package accessibility

protected class A (a: String){

    protected[accessibility] val c: String = "c"


    private val b: B = new B()

    //b.b           不可访问
    b.bc

    class B {
        private val b: String = "bb"

        private[accessibility] val bc: String = "bb"
    }
}






private object TestMain {

    def main(args: Array[String]): Unit = {
        val a = new A("abc")
        println(a)
    }
}