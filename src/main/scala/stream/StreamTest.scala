package stream

object StreamTest {

    def main(args: Array[String]): Unit = {


//        val stream = Cons(() => 1, () => Cons(() => 2, () =>Cons( () => 3, () => Cons(() => 4, () => Cons(() => 5, () => Cons(() => 6, () => Cons(() =>7, () => Empty)))))))
//
////        println("ToList: " + stream.toList())
////
////        println("Take 3: " + stream.take(3).toList())
////
////        println("TakeFold : " + stream.take2(4).toList())
////
////        println("TakeWhile : " + stream.takeWhile(_%2==1).toList())
////
////        println("Exist ==4 : " + stream.exists(_==4))
////        println("Exist2 ==9 :"  +stream.exists2(_==9))
////
////        println("Append : " + stream.append(Cons(() => 9, () =>Empty)).toList())
////
////        println("FaltMap : " +  stream.faltMap((xy) => if(xy % 2 == 0) Cons(() => 2, () => Cons(() => 2, () => Empty)) else Cons(() => 1, () => Empty)).toList())
//
//        println(stream.map(x => {
//            println("map " + x)
//            x + 10
//        }).filter(y => {
//            println("filter " + y)
//            y % 2 == 0
//        } ).toList)


//        val it1 = stream2.iterator
//        loop("Iterator1: ", it1.next, it1)

        // “打印”工资单
        println("** Paychecks:")
        netPay foreach {
            case (e, net) => println(f"} ${e.name+':'}%-16s ${net}%10.2f")
                    // 生成报表:
                    val report = (netPay foldLeft (0.0, 0.0, 0.0)) {
                    case ((totalSalary, totalNet, totalInsurance), (e, net)) =>
                    (totalSalary + e.annualSalary/52.0, totalNet + net, totalInsurance + e.insurancePremiumsPerWeek)
                    }
                    println("\n** Report:")
                    println(f" Total Salary: ${report._1}%10.2f")
                println(f" Total Net: ${report._2}%10.2f")
                println(f" Total Insurance: ${report._3}%10.2f")
        }

        println("finish")
    }

    def loop(s: String, i: Int, iter: Iterator[Int]): Unit = {
        // Stop after 200,000
        if (i < 200001) {
            if (i % 50000 == 0) println(s + i)
            loop(s, iter.next, iter)
        }
    }

    val stream1: Stream[Int] = {
        def loop(v: Int): Stream[Int] = v #:: loop(v + 1)
        loop(0)
    }

    def stream2: Stream[Int] = {
        def loop(v: Int): Stream[Int] = v #:: loop(v + 1)
        loop(0)
    }


    case class Employee (
                                name: String,
                                title: String,
                                annualSalary: Double,
                                taxRate: Double,
                                insurancePremiumsPerWeek: Double)
    val employees = List(
        Employee("Buck Trends", "CEO", 200000, 0.25, 100.0),
        Employee("Cindy Banks", "CFO", 170000, 0.22, 120.0),
        Employee("Joe Coder", "Developer", 130000, 0.20, 120.0))
    // 计算每周工资单:
    val netPay = employees map { e =>
        val net = (1.0 - e.taxRate) * (e.annualSalary / 52.0) -
                e.insurancePremiumsPerWeek
        (e, net)
    }

}
