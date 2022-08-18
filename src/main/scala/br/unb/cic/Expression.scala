package br.unb.cic

sealed trait Expression

case class Const(val value: Integer) extends Expression
case class Add(val left: Expression, val right: Expression)
case class Div(val left: Expression, val right: Expression)

abstract class M[A, B]

case class Value[A,B](v: A) extends M[A, B]
case class Error[A,B](v: B) extends M[A, B]

/** Variation one of eval: Error handling */
object Expression {
  def eval(exp: Expression): M[Integer, String] = exp match {
    case Const(v)  => Value(v)
    case Add(l, r) => {
      (eval(l), eval(r)) match {
        case (Value(v1), Value(v2)) => Value(v1 + v2)
        case (Error(s), _) => Error(s)
        case (_, (Error(s))) => Error(s)
      }
    }
    case Div(l, r) => {
      (eval(l), eval(r)) match {
        case (Value(v1), Value(0))  => Error("division by zero")
        case (Value(v1), Value(v2)) => Value(v1 / v2)
        case (Error(s), _) => Error(s)
        case (_, Error(s)) => Error(s)
      }
    }
  }
}

