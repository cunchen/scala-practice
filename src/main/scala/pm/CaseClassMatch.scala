package pm

object CaseClassMatch {

    def main(args: Array[String]): Unit = {

        val alice = Person("Alice", 25, Address("1 Scala Lane", "Chicago", "USA"))
        val bob = Person("Bob", 29, Address("2 Java Ave.", "Miami", "USA"))
        val charlie = Person("Charlie", 32, Address("3 Python Ct.", "Boston", "USA"))

        for (person <- Seq(alice, bob, charlie)) {
            person match {
                case Person("Alice", 25, Address(_, "Chicago", _)) => println("Hi Alice!")
                case Person("Bob", 29, Address("2 Java Ave.", "Miami", "USA")) => println("Hi Bob!")
                case Person(name, age, _) => println(s"Who are you, $age year-old person named $name?")
            }
        }

        for (person <- Seq(alice, bob, charlie)) {
            person match {
                case p@Person("Alice", 25, address) => println(s"Hi Alice! $p")
                case p@Person("Bob", 29, a@Address(street, city, country)) => println(s"Hi ${p.name}! age ${p.age}, in ${a.city}")
                case p@Person(name, age, _) => println(s"Who are you, $age year-old person named $name? $p")
            }
        }

        for {
            x <- Seq(List(5.5, 5.6, 5.7), List("a", "b"))
        } yield {
            x match {
                case seqd: Seq[Double] => ("seq double", seqd)
                case seqs: Seq[String] => ("seq string", seqs)
                case _ => ("unknown!", x)
            }
        }

        val dogBreeds = Seq(Some("Doberman"), None, Some("Yorkshire Terrier"),
            Some("Dachshund"), None, Some("Scottish Terrier"),
            None, Some("Great Dane"), Some("Portuguese Water Dog"))
        println("second pass:")
        for {
            Some(breed) <- dogBreeds
            upcasedBreed = breed.toUpperCase()
        } println(upcasedBreed)
    }
}

case class Address(street: String, city: String, country: String)

case class Person(name: String, age: Int, address: Address)

