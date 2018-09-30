package caseclass

case class CaseA(id: Long, name: String) {

    def isPositive:Boolean = {
        if(id < 0l) true
        else false
    }


    def isPositive(id: Long): Boolean = {
        if(id < 0l) true
        else false
    }

}


class CaseB(name: String)

class CaseC(var name: String)



object CaseTest {

    def main(args: Array[String]): Unit = {
        val c = new CaseC("ads")
    }
}