package br.unb.cic

sealed trait Expression

case class Const(val value: Integer) extends Expression
case class Add(val left: Expression, val right: Expression)
case class Div(val left: Expression, val right: Expression)

/** Variation zero of eval */
object Expression {
  def eval(exp: Expression): Integer = exp match {
    case Const(v)  => v
    case Add(l, r) => eval(l) + eval(r)
    case Div(l, r) => eval(l) / eval(r)
  }
}

