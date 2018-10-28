package classtype


private class ClassPath {


    class ThisClass {
        var x = "1"

        //二者等价
        def setX1(x: String): Unit = this.x = x

        def setX2(x: String): Unit = ThisClass.this.x = x
    }


    private class SuperClass {


        trait X {
            def setXX(x: String): Unit = {}
        }

        class C1 {
            var x = "1"
            def setX1(x: String): Unit = this.x = x

            def setX2(x: String): Unit = C1.this.x = x
        }

        class C2 extends C1

        class C3 extends C2 with X {

            def setX3(x: String): Unit = super.setX1(x)

            def setX4(x: String): Unit = C3.super.setX1(x)

            def setX5(x: String): Unit = C3.super[C2].setX1(x)

            def setX6(x: String): Unit = C3.super[X].setXX(x)

            //　错误
            // def setX7(x:String): Unit = C3.super[C1].setX1(x)

            // 错误
            // def setX8(x:String): Unit = C3.super.super.setX1(x)

        }
    }

}

/**
  * 可以通过使用点号分隔的路径表达式查找嵌套类型。路径表达式中除最后一个元素之
  * 外,其他元素都必须保持固定(stable),即这些元素必须是包、单例对象或具有相同别名
  * 的类型声明。路径中最后一个元素可以是不固定的,包括类、trait 或者类型成员。
  */

package P1 {
    object O1 {
        object O2 {
            val name = "name"
        }
        class C1 {
            val name = "name"
        }
    }
}
class C7 {
    val name1 = P1.O1.O2.name
    type C1 = P1.O1.C1
    val c1 = new P1.O1.C1
    // val name2 = P1.O1.C1.name
}