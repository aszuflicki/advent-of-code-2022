val PATH = "C:\\git\\advent-of-code-2022\\inputs\\input4.txt"
val source = scala.io.Source.fromFile(PATH)
val lines = try source.mkString
  .split("\n")
  .map(_.split("[,-]")
    .map(_.toInt)) finally source.close()

lines.count { case Array(x1, y1, x2, y2) => (x1 >= x2 && y1 <= y2) || (x1 <= x2 && y1 >= y2) }
lines.count { case Array(x1, y1, x2, y2) => (x1 <= x2 && x2 <= y1) || (x1 >= x2 && x1 <= y2) }
