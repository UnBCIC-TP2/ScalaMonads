package br.unb.cic

sealed trait Expression

case class Const(val value: Integer) extends Expression
case class Add(val left: Expression, val right: Expression) extends Expression
case class Div(val left: Expression, val right: Expression) extends Expression

sealed trait M[A]

object M {
  type Message = String
  def pure[A](v: A) : M[A] = Value(v)

  def bind[A,B](m: M[A], f : A => M[B]): M[B] = m match {
    case Value(v) => f(v)
    case Error(b) => Error(b)
  }
}

import M._

case class Value[A](v: A) extends M[A]
case class Error[A](v: Message) extends M[A]

/** Variation one of eval: Error handling */
object Expression {
  def eval(exp: Expression): M[Integer] = exp match {
    case Const(v)  => Value(v)
    case Add(l, r) => bind(eval(l), (x: Integer) => bind(eval(r), (y: Integer) => pure(x + y)))
    case Div(l, r) => bind(eval(l), (x: Integer) => bind(eval(r), (y: Integer) => if(y == 0) Error("Division by zero") else pure(x / y)))
  }
}

