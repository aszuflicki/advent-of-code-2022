val PATH = "C:\\git\\advent-of-code-2022\\inputs\\input1.txt"
val source = scala.io.Source.fromFile(PATH)
val sourceString = try source.mkString finally source.close()

var lines = sourceString.split("\n\n")
  .map(_.split("\n"))
  .map(_.map(_.toLong))
  .map(_.sum)
  .sorted

lines.last
lines.drop(lines.length - 3).sum
