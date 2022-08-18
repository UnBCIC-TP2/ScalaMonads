package br.unb.cic

sealed trait Expression

case class Const(val value: Integer) extends Expression
case class Add(val left: Expression, val right: Expression)
case class Div(val left: Expression, val right: Expression)

abstract class M[A, B]

case class Value[A,B](v: A) extends M[A, B]
case class Error[A,B](v: B) extends M[A, B]

object M {
  def pure[A, B](v: A) : M[A,B] = Value(v)

  def bind[A,B, C](m: M[A, B], f : A => M[C,B]): M[C,B] = m match {
    case Value(v) => f(v)
    case Error(b) => Error(b)
  }
}

import M._
/** Variation one of eval: Error handling */
object Expression {
  def eval(exp: Expression): M[Integer, String] = exp match {
    case Const(v)  => Value(v)
    case Add(l, r) => bind(eval(l), (x: Integer) => bind(eval(r), (y: Integer) => pure(x + y)))
    case Div(l, r) => bind(eval(l), (x: Integer) => bind(eval(r), (y: Integer) => if(y == 0) Error("Division by zero") else pure(x + y)))
  }
}

