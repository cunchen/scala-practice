package implicits

object ToJsonTest {

    def main(args: Array[String]): Unit = {
        val a = Address("1 Scala Lane", "Anytown")
        println(a.toJSON())

        val p = Person("Buck Trends", a)
        println(p.toJSON())
    }

    case class Address(street: String, city: String)
    case class Person(name: String, address: Address)

    trait ToJson {
        def toJSON(level: Int = 0): String

        val INDENTATION = " "

        def indentation(level: Int = 0): (String, String) =
            (INDENTATION * level, INDENTATION * (level + 1))
    }

    implicit class AddressToJSON(address: Address) extends ToJson {

        def toJSON(level: Int = 0): String = {
            val (outdent, indent) = indentation(level)

            s"""{
               |${indent}"street": "${address.street}",
               |${indent}"city": "${address.city}
               |$outdent}""".stripMargin
        }
    }

    implicit class PersonToJSON(person: Person) extends ToJson {
        def toJSON(level: Int = 0): String = {
            val (outdent, indent) = indentation(level)
            s"""
               |${indent}"name":        "${person.name}",
               |${indent}"address":     "${person.address.toJSON(level + 1)}
             |$outdent}""".stripMargin
        }
    }
}