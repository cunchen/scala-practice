import java.lang.{Integer => JInt}
import java.util.{ArrayList => JArrayList, List => JList}

import scala.collection.JavaConverters._

object ListConversion {

    def main(args: Array[String]): Unit = {
        val jList: JArrayList[Int] = new JArrayList[Int]
        jList.add(1)
        jList.add(2)
        jList.add(3)

        val list = List[Int](1, 2, 3)
        val jListWrapper = new JArrayList[JInt]
        jListWrapper.add(1)
        jListWrapper.add(2)
        jListWrapper.add(3)

        println(jlist2List(jList))

        println(list2Jlist(list))

        println(jlistWithWrapper2List(jListWrapper))

        println(List2jlistWithWrapper(list))


        println(jListWrapper.asScala.toList)

        println(list.asJava)

        println(list.map(m => m: JInt).asJava)

    }

    def jlist2List(li: JList[Int]): List[Int] = {
        asScalaBuffer(li).toList
    }

    def list2Jlist(li: List[Int]): JList[Int] ={
        seqAsJavaList(li)
    }

    def jlistWithWrapper2List(li: JList[JInt]): List[Int] = {
        asScalaBuffer(li).map(l => l: Int).toList
    }

    def List2jlistWithWrapper(li: List[Int]): JList[JInt] = {
        seqAsJavaList( li.map( i => i: JInt))
    }
}
