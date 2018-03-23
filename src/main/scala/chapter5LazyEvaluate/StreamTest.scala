package chapter5LazyEvaluate

object StreamTest {

    def main(args: Array[String]): Unit = {


        val stream = Cons(() => 1, () => Cons(() => 2, () =>Cons( () => 3, () => Cons(() => 4, () => Cons(() => 5, () => Cons(() => 6, () => Cons(() =>7, () => Empty)))))))

//        println("ToList: " + stream.toList())
//
//        println("Take 3: " + stream.take(3).toList())
//
//        println("TakeFold : " + stream.take2(4).toList())
//
//        println("TakeWhile : " + stream.takeWhile(_%2==1).toList())
//
//        println("Exist ==4 : " + stream.exists(_==4))
//        println("Exist2 ==9 :"  +stream.exists2(_==9))
//
//        println("Append : " + stream.append(Cons(() => 9, () =>Empty)).toList())
//
//        println("FaltMap : " +  stream.faltMap((xy) => if(xy % 2 == 0) Cons(() => 2, () => Cons(() => 2, () => Empty)) else Cons(() => 1, () => Empty)).toList())

        println(stream.map(x => {
            println("map " + x)
            x + 10
        }).filter(y => {
            println("filter " + y)
            y % 2 == 0
        } ).toList)
    }
}
