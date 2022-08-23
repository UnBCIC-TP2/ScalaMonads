package br.unb.cic

import br.unb.cic.M.mMonad.pure
import cats.Monad
import cats.implicits.toFlatMapOps

sealed trait Expression

case class Const(val value: Integer) extends Expression
case class Add(val left: Expression, val right: Expression) extends Expression
case class Div(val left: Expression, val right: Expression) extends Expression

sealed trait M[A]

object M {
  type Message = String
  implicit val mMonad: Monad[M] = new Monad[M] {
    override def pure[A](x: A): M[A] = Value(x)

    override def flatMap[A, B](m: M[A])(f: A => M[B]): M[B] = m match {
      case Value(v) => f(v)
      case Error(b) => Error(b)
    }

    override def tailRecM[A, B](a: A)(f: A => M[Either[A, B]]): M[B] = ???
  }
}

import M.Message

case class Value[A](v: A) extends M[A]
case class Error[A](v: Message) extends M[A]

/** Variation one of eval: Error handling with cats */
object Expression {
  def eval(exp: Expression): M[Integer] = exp match {
    case Const(v)  => Value(v)
    case Add(l, r) => eval(l).flatMap(x => eval(r).flatMap(y => pure(x + y)))
    case Div(l, r) => eval(l).flatMap(x => eval(r).flatMap(y => if(y == 0) Error("Division by zero") else pure(x/y)))
  }
}

