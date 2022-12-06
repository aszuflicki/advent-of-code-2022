val PATH = "C:\\git\\advent-of-code-2022\\inputs\\input6.txt"
val source = scala.io.Source
  .fromFile(PATH)

val lines = try source.mkString
  .toCharArray finally source.close()

lines.sliding(4)
  .zipWithIndex
  .collectFirst { case (arr, i) if arr.toSet.size == 4 => i + 4 }

lines.sliding(14)
  .zipWithIndex
  .collectFirst { case (arr, i) if arr.toSet.size == 14 => i + 14 }
