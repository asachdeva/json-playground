package asachdeva

import io.circe.generic.extras.Configuration

package object jsonplayground {

  implicit val config: Configuration =
    Configuration.default.withSnakeCaseMemberNames

  def printlnGood(in: Any): Unit =
    println(Console.GREEN + in + Console.RESET)

  def printlnBad(in: Any): Unit =
    println(Console.RED + in + Console.RESET)

  final implicit class PipeAndTap[A](private val a: A) extends AnyVal {
    @inline final def pipe[B](ab: A => B): B = ab(a)
    @inline final def tap[U](au: A => U): A = { au(a); a }
    @inline final def tapAs[U](u: => U): A = { u; a }
  }

}
