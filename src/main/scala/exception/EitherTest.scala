package exception

object EitherTest {


    def mean (xs: IndexedSeq[Double]): Either[String, Double] =
        if(xs.isEmpty) Left("men of empty list!")
        else Right(xs.sum / xs.length)

    def safeDiv(x: Int, y: Int):Either[Exception, Int] =
        try Right(x /y)
        catch {case e: Exception => Left(e)}

    def Try[A](a: => A) : Either[Exception, A] =
        try Right(a)
        catch { case e: Exception => Left(e)}

    def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] = {
//        def sequence[E, A](es: List[Either[E, A]])(ee: => E): Either[E, List[A]] = {

        es match {
            case h :: t => h.flatMap(hh => sequence(t).map(hh :: _))
            case Nil => Right(Nil)
//            case Nil =>  Left(ee)
        }
    }

    def sequence2[E, A](es: List[Either[E, A]]): Either[E, List[A]] = {
        es.foldRight[Either[E,List[A]]](Right(Nil: List[A]))((x, y) => x.flatMap(hh => y.map(hh :: _)))
    }

    def traverse[E, A, B](as: List[A]) (f: A => Either[E, B]): Either[E, List[B]] = {

        as match {
            case h :: t => f(h).flatMap(hh => traverse(t)(f).map(hh :: _))
            case Nil => Right(Nil)
        }
    }

}
