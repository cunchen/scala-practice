package exception

sealed trait Option[+A] {
    def map[B](f: A => B): Option[B] = this match {
        case None => None
        case Some(a) => Some(f(a))
    }

    def flatMap[B](f: A => Option[B]): Option[B] ={
        map(f) getOrElse None
    }

    // B >: A表示B类型必须是A的父类
    def getOrElse[B >: A](default: => B): B = this match{
        case None => default
        case Some(a) => a
    }


    //不对ob求值 除非需要
    def orElse[B >: A](ob: => Option[B]): Option[B] = {
        this map(Some(_)) getOrElse ob
    }

    def orElse_1[B >: A](ob: => Option[B]): Option[B] = this match {
        case None => ob
        case _ => this
    }

    //如果值不满足f  转换Some为None
    def filter(f: A => Boolean): Option[A] = this match {
        case Some(a) if f(a) => this
        case _ => None
    }
}
case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]

