val PATH = "C:\\git\\advent-of-code-2022\\inputs\\input2.txt"
val source = scala.io.Source.fromFile(PATH)
val sourceString = try source.mkString finally source.close()
val lines = sourceString.split("\n")

lines
  .map {
    case "A X" => 1 + 3
    case "B X" => 1 + 0
    case "C X" => 1 + 6
    case "A Y" => 2 + 6
    case "B Y" => 2 + 3
    case "C Y" => 2 + 0
    case "A Z" => 3 + 0
    case "B Z" => 3 + 6
    case "C Z" => 3 + 3
    case _ => 0
  }.sum

lines.map {
  case "A X" => 3 + 0
  case "B X" => 1 + 0
  case "C X" => 2 + 0
  case "A Y" => 1 + 3
  case "B Y" => 2 + 3
  case "C Y" => 3 + 3
  case "A Z" => 2 + 6
  case "B Z" => 3 + 6
  case "C Z" => 1 + 6
  case _ => 0
}.sum


