package br.unb.cic


import org.scalatest.funsuite.AnyFunSuite
import br.unb.cic.Expression.eval
class TestSuite extends AnyFunSuite {

  test("test for Div(10,5)") {
    val l = Const(10)
    val r = Const(5)
    val d = Div(l, r)

    assert (eval(d) == Value(2))
  }

  test("test for Div(100,Add(5, 5))") {
    val d = Div(Const(100), Add(Const(5),Const(5)))
    assert (eval(d) == Value(10))
  }

  test("test for Div(100, 0)") {
    val d = Div(Const(100), Const(0))
    eval(d) match {
      case Error(_) => assert(true)
      case _ => assert(false)
    }
  }
}

