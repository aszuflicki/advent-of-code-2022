import scala.collection.mutable

val PATH = "C:\\git\\advent-of-code-2022\\inputs\\input5.txt"

val source = scala.io.Source
  .fromFile(PATH)

val lines = try source.mkString finally source.close()
val linesSplit = lines.split("\n\n")

def getCrateStacks(linesSplit: Seq[String]) = {
  val crateLines = linesSplit.head.split("\n")
  val crateStacks: Seq[mutable.Stack[String]] =
    0 to crateLines(0).length / 4 map (_ => mutable.Stack[String]())

  crateLines
    .map(_.sliding(4, 4).toArray)
    .reverse
    .slice(1, crateLines(0).length / 4 + 1)
    .map(_.map(_.charAt(1).toString))
    .map(arr => arr.zipWithIndex)
    .foreach(_.foreach {
      case (str, index) if str.trim.nonEmpty => crateStacks(index).push(str)
      case _ =>
    })

  crateStacks
}

val crateStacksA = getCrateStacks(linesSplit)
val pattern = """move (\d+) from (\d+) to (\d+)""".r
val instructions = linesSplit(1).split("\n")
  .flatMap(line => pattern.findAllIn(line).matchData
    .map(m => (m.group(1).toInt, m.group(2).toInt, m.group(3).toInt)).toArray)
val crateStacksB = getCrateStacks(linesSplit)

instructions.foreach { case (c, f, t) =>
  for (_ <- 0 until c)
    crateStacksA(t - 1).push(crateStacksA(f - 1).pop())
}

crateStacksA.map(s => s.top).mkString("")

instructions.foreach { case (c, f, t) =>
  val temp = mutable.Stack[String]()
  (0 until c) foreach (_ => temp.push(crateStacksB(f - 1).pop()))
  while (temp.nonEmpty) crateStacksB(t - 1).push(temp.pop())
}

crateStacksB.map(s => s.top).mkString("")
