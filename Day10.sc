val PATH = "C:\\git\\advent-of-code-2022\\inputs\\input10.txt"
val source = scala.io.Source
  .fromFile(PATH)

val lines = try source.mkString
  .split("\n")
  .map(_.split(" ")) finally source.close()

var prev = 1;
val values = Array(prev) ++ lines.flatMap {
  case Array(_, value) =>
    val lastPrev = prev
    prev = prev + value.toInt
    Array(lastPrev, prev)
  case _ => Array(prev)
}
values(19) * 20 +
  values.splitAt(20)._2.sliding(40, 40)
    .filter(_.length == 40)
    .zipWithIndex
    .map { case (arr, index) => arr.last * ((index + 1) * 40 + 20) }
    .toArray
    .sum

values
  .sliding(40, 40)
  .map(_.zipWithIndex
    .map {
      case (x, index) if Array(x - 1, x, x + 1).contains(index) => "#"
      case _ => "."
    })
  .foreach(row => println(row.mkString))
