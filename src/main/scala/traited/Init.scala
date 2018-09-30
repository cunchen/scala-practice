//package traited
//
//class Init {
//
//}
//
//trait Clickable {
//
//
//    def click(): Unit = updateUI()
//
//    protected def updateUI(): Unit
//
//}
//
//trait VetoableClicks extends Clickable {
//
//    val maxAllowed = 1
//    private var count = 0
//
////    abstract override def click() = {
////        if (count < maxAllowed) {
////            count += 1
////            super.click()
////        }
////    }
//
////    override protected def updateUI(): Unit = {}
//}
//
//trait ObservableClicks extends Clickable with Subject[Clickable]{
////    abstract override def click() = {
////        super.click()
////        notifyObservers(this)
////    }
//}
//
//
//
////好像已经不可以 [Scala 使用线性化(linearization)算法解决具体类继承树中 trait 和类的优先级问题]
//object TestMain {
//
//
//    class ClickCountObserver extends Observer[Clickable] {
//        var count = 0
//        override def receiveUpdate(state: Clickable): Unit = count += 1
//    }
//
//    def main(args: Array[String]): Unit = {
//        val button = new Button("Click Me!")
//                with ObservableClicks
//                with VetoableClicks
//        {
//            override val maxAllowed: Int = 2
//        }
//
//        val bco1 = new ClickCountObserver
//        val bco2 = new ClickCountObserver
//
//        button addObserver bco1
//        button addObserver bco2
//        (1 to 5) foreach (_ => button.click())
//        assert(bco1.count == 2, s"bco1.count ${bco1.count} != 2")
//        assert(bco2.count == 2, s"bco2.count ${bco2.count} != 2")
//        println("Success!")
//    }
//
//
//}