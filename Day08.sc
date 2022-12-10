val PATH = "C:\\git\\advent-of-code-2022\\inputs\\input8.txt"
val source = scala.io.Source
  .fromFile(PATH)

val lines = try source.mkString
  .split("\n") finally source.close()

//var board = lines.map(_.toCharArray.map(tree => (tree.toString.toInt, false)))
var board = lines.map(_.toCharArray.map(tree => tree.toString.toInt))

var calculatetd = board.map(line => {
  line.zipWithIndex
    .map { case (tree, index) =>
      val (a, b) = line.splitAt(index)
      val horizont1 = a.reverse.zipWithIndex
        .collectFirst { case (height, index) if height >= tree => index + 1 }
        .getOrElse(a.length)
      val horizont2 = b.tail.zipWithIndex
        .collectFirst { case (height, index) if height >= tree => index + 1 }
        .getOrElse(b.tail.length)
      (tree, Array(horizont1, horizont2))
    }
}).transpose
  .map(line => {
    line.zipWithIndex
      .map { case ((tree, arr), index) =>
        val (a, b) = line.map(_._1).splitAt(index)
        val horizont1 = a.reverse.zipWithIndex
          .collectFirst { case (height, index) if height >= tree => index + 1 }
          .getOrElse(a.length)
        val horizont2 = b.tail.zipWithIndex
          .collectFirst { case (height, index) if height >= tree => index + 1 }
          .getOrElse(b.tail.length)
        (tree, arr ++ Array(horizont1, horizont2))
      }
  })

calculatetd.flatMap(_.map { case (_, arr) => arr }).map(_.product).max



//def dropFirstMatch(ls: Array[Int], value: Int) = {
//  val (a, b) = ls.splitAt(ls.indexOf(value))
//  a ++ b.tail
//}

//board = board.map(line => {
//  var left = Array(-1)
//  var right: Array[Int] = line.map(_._1) ++ Array(-1)
//  line.map(tree => {
//    right = dropFirstMatch(right, tree._1)
//    val isVisible = left.max < tree._1 || right.max < tree._1
//    left = left ++ Array(tree._1)
//    (tree._1, isVisible || tree._2)
//  })
//})

//val boardRotated = board.transpose
//
//val count = boardRotated.flatMap(line => {
//  var left = Array(-1)
//  var right: Array[Int] = line.map(_._1) ++ Array(-1)
//  line.map(tree => {
//    right = dropFirstMatch(right, tree._1)
//    val isVisible = left.max < tree._1 || right.max < tree._1
//    left = left ++ Array(tree._1)
//    (tree._1, isVisible || tree._2)
//  })
//}).count { case (_, isVisible) => isVisible }
