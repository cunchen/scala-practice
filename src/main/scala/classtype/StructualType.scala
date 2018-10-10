package classtype

class StructualType {

}

//这个实现的一个缺点是,任何想要观察 Subject 状态变化的类型都必须实现 Observer 特
//征。但实际上,真正的最低要求是实现 receiveUpdate 方法。

//import 语句暗示了另外一个缺点。因为我们没有类型名可用于验证候选的观察者实例是否
//实现了正确的方法,编译器必须使用反射来确认该方法存在于该实例中。
private class demo1 {

    trait Subject {

        import scala.language.reflectiveCalls

        type State
        type Observer = { def receiveUpdate(state: Any): Unit}

        private var observers: List[Observer] = Nil

        def addObserver(observer: Observer) = observers ::= observer

        def notifyyObservers(state: State) = observers.foreach(_.receiveUpdate(state))
    }


}


//解耦最终方案
package demo2 {

    trait Subject {

        type State
        type Observer = State => Unit

        private var observers: List[Observer] = Nil

        def addObserver(observer: Observer) = observers ::= observer

        def notifyyObservers(state: State) = observers.foreach(o => o(state))
    }


}
