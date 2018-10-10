package classtype

class SelfType2 {

}


trait Persistence { def startPersistence(): Unit}

trait Midtier { def startMidtier(): Unit}

trait UI { def startUI(): Unit}


trait Database extends Persistence {
    def startPersistence(): Unit = println("Starting Database")
}

trait BizLogic extends Midtier {

    def startMidtier(): Unit = println("Strating BizLogic")
}

trait WebUI extends UI {
    def startUI(): Unit = println("Starting WebUI")
}

//两者等价
//自类型标记强调用混入实现组合。继承意味着类之间是父类与子类的关系。
//除非需要继承大规模的“模块”(trait),且自类型标记能更清楚地表明设计思路的情况,否则大部分 Scala 代码倾向于使用继承的方法,而不是自类型标记。
trait App {
    self: Persistence with Midtier with UI =>

//trait App extends Persistence with Midtier with UI {

    def run(): Unit = {
        startPersistence()
        startMidtier()
        startUI()
    }
}


object MyApp extends App with Database with BizLogic with WebUI {

    def main(args: Array[String]): Unit = {
        this.run()
    }

}