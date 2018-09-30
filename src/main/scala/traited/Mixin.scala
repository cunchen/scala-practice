package traited

class Mixin {

}

class ButtonWithCallbacks(val label: String,
                          val callbacks: List[() => Unit] = Nil) extends Widget{

    def click() : Unit = {
        updateUI()
        callbacks.foreach(f => f())
    }

    protected def updateUI(): Unit = {
        // 修改UI样式
    }
}

object ButtonWithCallbacks {
    def apply(label: String, callback: () => Unit) =
        new ButtonWithCallbacks(label, List(callback))

    def appley(label: String) =
        new ButtonWithCallbacks(label, Nil)
}


abstract class Widget


trait Observer[-State] {
    def receiveUpdate(state: State): Unit
}

trait Subject[State] {
    private var observers: List[Observer[State]] = Nil

    def addObserver(observer: Observer[State]): Unit =
        observers ::= observer// observers = observer :: observers

    def notifyObservers(state: State): Unit =
        observers foreach(_.receiveUpdate(state))
}


class Button(val label: String) extends  Widget{

    def click(): Unit = updateUI()
    def updateUI(): Unit = {}
}


class ObservableButton(name: String) extends Button(name) with Subject[Button] {

    override def click(): Unit = {
        super.click()
        notifyObservers(this)
    }
}

class ButtonCountObserver extends Observer[Button] {
    var count = 0
    def receiveUpdate(state: Button): Unit = count += 1
}

object TestMain {

    def main(args: Array[String]): Unit = {
        val button = new ObservableButton("Click Me")
        val bco1 = new ButtonCountObserver
        val bco2 = new ButtonCountObserver

        button addObserver bco1
        button addObserver bco2
        (1 to 5) foreach(_ => button.click())

        println(bco1.count)
        println(bco2.count)

    }
}