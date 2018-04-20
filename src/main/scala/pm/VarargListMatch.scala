package pm

import pm.Op.{Op, EQ, GT}

object VarargListMatch {

    def main(args: Array[String]): Unit = {

        test1
    }

    val wheres = Seq(
        WhereIn("state", "IL", "CA", "VA"),
        WhereOp("state", EQ, "IL"),
        WhereOp("name", EQ, "Buck Trends"),
        WhereOp("age", GT, 29))

    def test1: Unit = {
        for(where <- wheres) {
            where match {
                case WhereIn(col, val1, vals @ _*) =>
                    val valStr = (val1 +: vals).mkString(",")
                    println(s"Where $col In ($valStr)")
                case WhereOp(col, op, value) => println(s"WHERE $col $op $value")
                case _ => println(s"Error: Unkown expression: $where")
            }
        }
    }
}

case class WhereOp[T](columnNmae: String, op: Op, value: T)
case class WhereIn[T](columnName: String, val1: T, vals: T*)

object Op extends Enumeration {
    type Op = Value

    val EQ = Value("=")
    val NE = Value("!=")
    val LTGT = Value("<>")
    val LT = Value("<")
    val LE = Value("<=")
    val GT = Value(">")
    val GE = Value(">=")
}
