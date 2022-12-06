val PATH = "C:\\git\\advent-of-code-2022\\inputs\\input3.txt"
val source = scala.io.Source.fromFile(PATH)
val lines = try source.mkString.split("\n")
  .map(_.toCharArray.map(toDec)) finally source.close()

def toDec(c: Char): Int = {
  if (c <= 90) c - 'A' + 26 + 1 else c - 'a' + 1
}

lines.map(arr => arr.sliding(arr.length / 2, arr.length / 2).toArray)
  .flatMap {
    case Array(x, y) => x.sorted
      .collectFirst {
        case i: Int if y.contains(i) => i
      }
  }.sum

lines.map(_.toSet)
  .sliding(3, 3)
  .toArray
  .map(arr => arr(0) intersect arr(1) intersect arr(2))
  .flatMap(_.toArray)
  .sum


