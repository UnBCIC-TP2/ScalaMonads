package br.unb.cic

sealed trait Expression

case class Const(val value: Integer) extends Expression
case class Add(val left: Expression, val right: Expression) extends Expression
case class Div(val left: Expression, val right: Expression) extends Expression

abstract class M[A]


case class Value[A](v: A) extends M[A]
case class Error[A](v: String) extends M[A]

/** Variation one of eval: Error handling */
object Expression {
  def eval(exp: Expression): M[Int] = exp match {
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

