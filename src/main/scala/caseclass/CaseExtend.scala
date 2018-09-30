package caseclass

class CaseExtend {

}

case class Person(name: String,
                  age: Option[Int] = None,
                  address: Option[String] = None)


//注意这里必须使用override
class Employee(override val name: String,
                    override val age: Option[Int] = None,
                    override val address: Option[String] = None,
                    title: String = "[unknow]",
                    manager: Option[Employee] = None) extends Person(name, age, address)


object Employee {

    def apply(name: String,
              age: Option[Int] = None,
              address: Option[String] = None,
              title: String = "[unknow]",
              manager: Option[Employee] = None) = new Employee(name)
}



object Test {

    def main(args: Array[String]): Unit = {
        val em = Employee("bob")

        println(em)
    }

}