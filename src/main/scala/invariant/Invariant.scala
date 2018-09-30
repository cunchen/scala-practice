package invariant

class Invariant {

}

class CSuper {

    val s = "s"
    def msuper() = println("CSuper")
}

class C
        extends CSuper {

    val c = "c"
    def m() = println("C")
}

class CSub
        extends C {

    val sub = "sub"
    def msub() = println("CSub")
}

private object TestMain {

    def main(args: Array[String]): Unit = {
        var f: C => C = (c: C) => new C

        f = (c: CSuper) => new CSub

        f = (c: CSuper) => new C

        f = (c: C) => new CSub

//        f = (c: CSuper) => new CSuper

    }
}