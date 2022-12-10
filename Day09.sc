import scala.collection.mutable

val PATH = "C:\\git\\advent-of-code-2022\\inputs\\input9.txt"
val source = scala.io.Source
  .fromFile(PATH)

val lines = try source.mkString
  .split("\n")
  .map(_.split(" "))
  .map { case Array(dir, steps) => (dir, steps.toInt) } finally source.close()

var visited = mutable.Set[(Int, Int)]()

var bridge: Array[(Int, Int)] = (0 until 2).map(index => (1000, 1000)).toArray
def moveTail() = {
  bridge = Array(bridge.head) ++ bridge.sliding(2)
    .map { case Array(head, tail) =>
      if (Math.abs(tail._1 - head._1) <= 1
        && Math.abs(tail._2 - head._2) <= 1) {
        tail
      } else if (tail._1 == head._1) {
        (tail._1, (head._2 + tail._2) / 2)
      } else if (tail._2 == head._2) {
        ((head._1 + tail._1) / 2, tail._2)
      } else if (Math.abs(head._1 - tail._1) == 2 && Math.abs(head._2 - tail._2) == 2) {
        ((head._1 + tail._1) / 2, (head._2 + tail._2) / 2)
      } else if (Math.abs(head._1 - tail._1) == 2) {
        ((head._1 + tail._1) / 2, head._2)
      } else {
        (head._1, (head._2 + tail._2) / 2)
      }
    }
  visited.add(bridge.last)
}


def moveHead(dir: String): Unit = {
  var head = bridge.head
  dir match {
    case "U" => head = (head._1 - 1, head._2)
    case "D" => head = (head._1 + 1, head._2)
    case "L" => head = (head._1, head._2 - 1)
    case "R" => head = (head._1, head._2 + 1)
  }
  bridge = Array(head) ++ bridge.tail
  (0 until 20).foreach(_ => moveTail)
}

lines.foreach { case (dir, steps) =>
  0 until steps foreach (step => {
    moveHead(dir)
  })
}
visited.size

visited = mutable.Set[(Int, Int)]()
bridge = (0 until 10).map(index => (1000, 1000)).toArray
lines.foreach { case (dir, steps) =>
  0 until steps foreach (step => {
    moveHead(dir)
  })
}
visited.size

