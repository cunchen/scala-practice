package classtype


abstract class SubjectObserver {

    type S <: Subject
    type O <: Observer

    trait Subject {
        self: S =>

        private var observers = List[O]()

        def addObserver(observer: O) = observers ::= observer

        def notifyyObservers() = observers.foreach(_.receiveUpdate(self))
    }

    trait Observer {
        def receiveUpdate(subject: S)
    }
}

case class Button(label: String) {

    def click(): Unit = {}
}

object ButtonSubjectObserver extends SubjectObserver {

    type S = ObservableButton
    type O = ButtonObserver

    class ObservableButton(label: String) extends Button(label) with Subject {

        override def click(): Unit = {
            super.click()
            notifyyObservers()
        }
    }

    trait ButtonObserver extends Observer {
        def receiveUpdate(subject: ButtonSubjectObserver.S)
    }

}

import ButtonSubjectObserver._

import scala.collection.mutable

class ButtonClickObserver extends ButtonObserver {

    val clicks = new mutable.HashMap[String, Int]()

    def receiveUpdate(button: ObservableButton): Unit = {
        val count = clicks.getOrElse(button.label, 0) + 1
        clicks.update(button.label, count)

    }
}

object RunMain {


    def main(args: Array[String]): Unit = {
        val buttons = Vector(new ObservableButton("one"), new ObservableButton("two"))
        val observer = new ButtonClickObserver
        buttons foreach (_.addObserver(observer))
        for (i <- 0 to 2) buttons(0).click()
        for (i <- 0 to 4) buttons(1).click()
        println(observer.clicks)
    }
}
