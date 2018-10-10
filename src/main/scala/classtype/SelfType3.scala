package classtype

object SelfType3 {
    def main(args: Array[String]): Unit = {
        val c1 = new C1
        c1.talk("Hello")
        c1.c2.c3.talk("World")
    }
}

//这里 self 这个名字是可以任意取的,并不是关键字。
//如果没有自类型标记,我们就不能直接从 C3.talk 中调用 C1.talk ,因为两者名称相同,
// 后者屏蔽了前者。 C3 也不是 C1 的直接子类,所以也不能调用 super.talk 。
//所以,在这里,你可以认为自类型标记是一个“通用的 this”引用。
class C1 { self =>

    def talk(message: String) = println("C1.talk:" + message)

    class C2 {
        class C3 {
            def talk(message: String) = self.talk("C3.talk:" + message )

        }

        val c3 = new C3
    }

    val c2 = new C2

}
