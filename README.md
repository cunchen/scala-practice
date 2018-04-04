# scala-practice

主要是《Scala函数式编程》 的习题练习以及一些Scala踩到的一些坑

## 目录说明

- Chapter2 Why scala
- Chpater3 DataStruct
  - [List 遍历 foldLeft / foldRight详解](http://blog.csdn.net/cun_chen/article/details/79474240)
  - [Scala Tree Fold DSF深度优先遍历详解](http://blog.csdn.net/cun_chen/article/details/79533442)
- Chapter4 Exception
- Chapter5 Stream


# Case

## Case Class
Case Class 属性不可以变，如果改变就会触发编译错误

case 关键字的另一个特征便是让编译器自动为我们生成许多
方法,其中包括了类似于 Java 语言中 String 、 equals 和 hashCode 方法。

Scala 调用生成的 equals 方法,以判断 p00 == p20 和 p20 == p20b 是否成立。这与 Java 的
做法不同,Java 通过比较引用是否相同来判断 == 是否成立。在 Java 中如果希望执行一次
逻辑比较,你需要明确地调用 equals 方法。

编译器同时会生成一个伴生对象(companion
object),伴生对象是一个与 case 类同名的单例对象



## 多参数列表

- 形式更优雅
如同 Java 方法一样, draw 方法也可以只使用一个带两个参数值的参数列表。如果那样,
客户端代码就会像这样写:`
s.draw(Point(1.0, 2.0),
str => println(s"ShapesDrawingActor: $str")
)`
这份代码并没那么清晰和优雅。使用默认值开启 offset 也没那么便捷,因此我们不得不对
参数进行命名:`
s.draw(f = str => println(s"ShapesDrawingActor: $str"))`
- 类型推断
- 隐含参数推断


## Future
scala并发工具

## 递归
Scala 采用的是
局部作用域类型推断,无法推断出递归函数的返回类型。
递归是函数式编程的特点,也是优雅地实现很多算法的强大工具。

所以,Scala 编译器对
尾递归做了有限的优化。它会对函数调用自身做优化,但不会优化所谓的 trampoline 的情
况,也就是“a 调用 b 调用 a 调用 b”的情形。

编译器是否对自己的尾递归执行了优化。没
有人希望在生产环境中出现栈空间崩溃。幸运的是,如果你加一个 tailrec 关键字

## 类型推断

什么时候需要显式类型注解
在实际编程中,你在以下情况中必须提供显式的类型注解。
- 声明了可变的 var 变量或不可变的 val 变量,没有进行初始化。(例如,在类中的
抽象声明,如 val book: String, var count: Int )。
- 所有的方法参数(如 def deposit(amount: Money) = {...} )。
- 方法的返回值类型,在以下情况中必须显式声明其类型。
	- 在方法中明显地使用了 return (即使在方法末尾也是如此)。
	- 递归方法。
	- 两个或多个方法重载(拥有相同的函数名),其中一个方法调用了另一个重载方
法,调用者需要显式类型注解。
	- Scala 推断出的类型比你期望的类型更为宽泛,如 Any 。

***开发 API 时,如果与客户端分开构建,应该显式地声明返回类型,并尽可
能地返回一个你所能返回的最通用的类型。***


### 保留字
保留字 | 描述
-| -
abstract |做抽象声明
case |match 表达式中的 case 子句;定义一个 case 类
catch |捕捉抛出的异常
class |声明一个类
def |定义一个方法
do |用于 do...while 循环
else |与 if 配对的 else 语句
extends |表示接下来的 class 或 trait 是所声明的 class 或 trait 的父类型
false |Boolean 的 false 值
final |用于 class 或 trait,表示不能派生子类型;用于类型成员,则表示派生的 class 或 trait 不能覆写它
finally |finally 语句跟在相应的 try 语句之后,无论是否抛出异常都会执行
for |for 循环
forSome |用在已存在的类型声明中,限制其能够使用的具体类型
if |if 语句
implicit |使得方法或变量值可以被用于隐含转换;将方法参数标记为可选的,只要在调用该方法时,作用域内有类型匹配的候选对象,就会使用该对象作为参数
import |将一个或多个类型抑或类型的成员导入到当前作用域
lazy |推迟 val 变量的赋值
match| 用于类型匹配语句
new |创建类的一个新实例
null |尚未被赋值的引用变量的值
object |用于单例声明,单例是只有一个实例的类
override| 当原始成员未被声明为 final 时,用 override 覆写类型中的一个具体成员
package |声明包的作用域
private |限制某个声明的可见性
protected| 限制某个声明的可见性
requires |停用,以前用于自类型
return |从函数返回
sealed |用于父类型,要求所有派生的子类型必须在同一个源文件中声明
super |类似 this ,但表示父类型
this |对象指向自身的引用;辅助构造函数的方法名
throw |抛出异常
rait |这是一个混入模块,对类的实例添加额外的状态和行为;也可以用于声明而不实现方法,类似 Java 的 interface
try |将可能抛出异常的代码块包围起来
true |Boolean 的 true 值
type |声明类型
val |声明一个“只读”变量
var |声明一个可读可写的变量
while |用于 while 循环
with |表示所声明的类或实例化的对象包括后面的 trait
yield| 在 for 循环中返回元素,这些元素会构成一个序列
_ |占位符,使用在 import、函数字面量中
: |分隔标识符和类型注解
= |赋值
=> |在函数字面量中分隔参数列表与函数体
<- |在 for 循环中的生成表达式
<: |在参数化类型和抽象类型声明中,用于限制允许的类型
<% |在参数化类型和抽象类型的 view bound 声明中
>:| 在参数化类型和抽象类型声明中,用于限制允许的类型
\# |在类型注入中使用
\@ |注解
⇒ |(Unicode \u21D2) 与 => 相同
→ |(Unicode \u2192) 与 -> 相同
← |(Unicode \u2190) 与 <- 相同

## 字面量

类型| 格式| 例子
-| -| -
十进制| 0 或一个非零值,后面跟上 0 个或多个数字(0-9)| 0, 1, 321
十六进制| 0x 后面跟上一个或多个十六进制数字(0-9,A-F,a-f)| 0xFF, 0x1a3b
八进制| 0 后面跟上一个多个八进制数字(0-7) a| 013,077
> 八进制 Scala 2.10中废弃

数字字面量前加 - 表示负数

类型| 下限| 上限
-| -| -
Long | -2^63| 2^63 - 1
Int| -2^31| 2^31 - 1
Short| -2^15| 2^15 - 1
Char| 0| 2^16 - 1
Byte| -2^7| 2^7 -1

浮点数字面量默认Double类型，指数部分e后跟 = -
布尔型字面量 true false
### 字符字面量
字符字面量要么是单引号内的一个可打印的 Unicode 字符,要么是一个转义序列。值在 0~255 的 Unicode 字符也可以用八进制数字的转义形式表示,即一个反斜杠(\)后面跟上最多 3 个八进制数字字符。如果反斜杠后面不是有效的转义序列,会引发编译时的错误。

***不可打印的 Unicode 字符,如 \u0009 (水平制表符)是不允许的。应该使用等价的
转义形式 \t 。回顾一下表 2-1 中提到的 3 个 Unicode 字符,可以有效地替换相应的 ASCII
序列: ⇒ 替换 => ,→替换 -> ,←替换 <- 。***

### 字符串字面量
字符串字面量是被双引号或者三重双引号包围的字符串序列,如 """..."""

用三重双引号包含的字符串字面量也被称为多行字符串字面量。这些字符串可以跨越多行,换行符是字符串的一部分。可以包含任意字符,可以是一个双引号也可以是两个连续的双引号,但不允许出现三个连续的双引号。对于包含有反斜杠 \ ,但反斜杠不用于构成Unicode 字符,也不用于构成有效转义序列的字符串很适合采用这种字符串字面量

使用多行字符串时,你可能希望各行子串有良好的缩进以使代码美观,但却不希望多余的空格出现在字符串的输出中。String.stripMargin 可以解决这个问题。它会移除每行字符
串开头的空格和第一个遇到的垂直分隔符 | 。

### 符号字面量
Scala 支持符号,符号是一些规定的字符串。两个同名的符号会指向内存中的同一对象。

符号字面量是单引号( ' )后跟上一个或多个数字、字母或下划线("\_" ),但第一个字符不能为数字。所以 '1symbol 是无效的符号。

符号字面量 'id 是表达式 scala.Symbol("id") 的简写形式,如果你需要创建一个包含空格的符号,可以使用 Symbol.apply ,如 Symbol(" Programming Scala ") 中的空格均为符号的一部分。

### 函数字面量
`(i: Int, s: String) => s+i `是一个
类型为 Function2[Int,String,String] (返回值类型为 String )的函数字面量。 甚至可以用函数字面量来声明变量,以下两种声明是等价的:
```
val f1: (Int,String) => String
= (i, s) => s+i
val f2: Function2[Int,String,String] = (i, s) => s+i
```

### 元组字面量
Scala 库中包含 TupleN 类(如 Tuple2 ),用于组建 N 元素组,它以小括号加上逗号分隔的
元素序列的形式来创建元素组。 TupleN 表示的多个类各自独立, N 的取值从 1 到 22,包括
22

元组的实例是不可变的、first-class 的值(因为它们是对象,与你定义的其他类的实例没有区别),所以你可以将它们赋值给变量,将它们作为输入参数,或从方法中将其返回。
你也可以用字面量语法来声明 Tuple 类型的变量:
```
val t1: (Int,String) = (1, "two")
val t2: Tuple2[Int,String] = (1, "two")
```

元组用法示例
```
val t = ("Hello", 1, 2.3)
println( "Print the whole tuple:" + t)
println( "Print the first item:" + t._1)
println( "Print the second item:" + t._2)
println( "Print the third item:" + t._3)

val (t1, t2, t3) = ("World", '!', 0x22)
println( t1 + ", " + t2 + ", " + t3 )

val (t4, t5, t6) = Tuple3("World", '!', 0x22)
println( t4 + ", " + t5 + ", " + t6 )
```

## Option、Some 和 None 避免使用null

```
val stateCapitals = Map(
"Alabama" -> "Montgomery",
"Alaska" -> "Juneau",
// ...
"Wyoming" -> "Cheyenne")
println("Alabama: " + stateCapitals.get("Alabama") )
println("Wyoming: " + stateCapitals.get("Wyoming") )
println("Unknown: " + stateCapitals.get("Unknown") )
println( "Get the capitals wrapped in Options:" )

println("Alabama: " + stateCapitals.get("Alabama").get )
println("Wyoming: " + stateCapitals.get("Wyoming").getOrElse("Oops!") )
println("Unknown: " + stateCapitals.get("Unknown").getOrElse("Oops2!") )
println( "Get the capitals themselves out of the Options:" )
```

### 封闭类的继承
现在我们来探讨 Option 的一个很有用的特性。 Option 的一个关键点在于它只有两个有效
的子类。如果我们有值,则对应 Some 子类;如果没有值,则对应 None 子类。没有其他有
效的 Option 子类型。所以,我们可以防止用户创建一个他们自己的子类。

### 命名空间
Scala 沿用 Java 用包来表示命名空间的这一做法,但它却更具灵活性。文件名不必与类名一致,包结构不一定要与目录结构一致。所以,你可以定义与文件的“物理”位置独立的包结构。


Scala 也支持使用嵌套块结构语法来定义包的作用域,与 C# 的命名空间语法和 Ruby 表示
命名空间的 modules 用法类似:
```
package com {
package example {
package pkg1 {
class Class11 {
def m = "m11"
}
class Class12 {
def m = "m12"
}
}
package pkg2 {
class Class21 {
def m = "m21"
def makeClass11 = {
new pkg1.Class11
}
def makeClass12 = {
new pkg1.Class12
}
}
}

package pkg3.pkg31.pkg311 {
class Class311 {
def m = "m21"
}
}
}
}
```


有一种情况你必须使用单独的 package 语句。我们称之为连续包声明:
```
package com.example
// 导入mypkg中所有包级别的声明
package mypkg
class MyPkgClass {
// ...
}
```

### 导入类型及其成员

- 用下划线( _ )当通配符,导入包中的所有类型
- java.io.File 中所有的静态方法和属性。与之等价的 Java import 语句为`import static java.io.File.*` 。Scala 没 有 import static 这 样 的 写 法, 因 为 Scala 将object 类型与其他类型一视同仁。
- 选择性导入 `java.util.{Map, HashMap}`
- import 语句几乎可以放在任何位置上,所以你可以将其可见性限制在需要的作用域中,可以在导入时对类型做重命名,也可以限制不想要的类型的可见性

```
def stuffWithBigInteger() = {
import java.math.BigInteger.{
ONE => _,
TEN,
ZERO => JAVAZERO }
println( "TEN: "+TEN )
println( "ZERO: "+JAVAZERO )
}
```

java.math.BigInteger.ONE 常量重命名为下划线,使得该常量不可见。当你需要导入除
少部分以外的所有声明时,可以采用这一技术。
接着, java.math.BigInteger.TEN 导入时未经重命名,所以可以用 TEN 引用它。
最后, java.math.BigInteger.ZERO 常量被赋予了 JAVAZERO 的“别名”。