### Scala Monads

An introduction to the Monads idiom using Scala.
Main reference:

   * [Monads For Functional Programming (P. Wadler)](https://homepages.inf.ed.ac.uk/wadler/papers/marktoberdorf/baastad.pdf)

#### Additional references:

   * [The Quick Essence of Functional Programming (video from Prof. Ralf Lammel)](https://youtu.be/s3YeFgiO7MA)
   * [Software extension and integration with type classes (R. Lammel and K. Ostermann)](https://www.informatik.uni-marburg.de/~kos/papers/gpce06.pdf)

#### Branchs

   * master: the very basic implementation of an interpreter for simple expressions
   * error: supporting error handling without using monads
   * monads: a first attemp to implament a monadic version of the interpreter
   * monad-operator: refactoring of the `bind` function to an infix operator
   * cats: an implementation of `M` as an instance of the Monad type class
   