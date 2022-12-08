import scala.collection.mutable
import scala.util.matching.Regex

val PATH = "C:\\git\\advent-of-code-2022\\inputs\\input7.txt"
val source = scala.io.Source
  .fromFile(PATH)

val chunks = try source.mkString
  .split("\\$")
  .map(_.split("\n").map(_.trim)) finally source.close()

case class Node(name: String, parentNode: Node, var children: Array[Node] = Array(), var size: Int = 0, isDir: Boolean = false)

val mount = Node("\\", null, Array(), isDir = true)
var cur = mount

val cd = """cd ([a-zA-Z\\.]+)""".r
val ls = """ls""".r
val dir = """dir ([a-zA-Z]+)""".r
val file = """(\d+) ([a-z\\.]+)""".r

def getGroup(pattern: Regex, s: String, index: Int = 1): String = {
  pattern.findAllIn(s)
    .matchData
    .map(_.group(index))
    .toArray
    .head
}
chunks.foreach {
  case cmd if cd.matches(cmd.head) =>
    getGroup(cd, cmd.head) match {
      case ".." =>
        cur = cur.parentNode
      case name =>
        cur = cur.children
          .collectFirst { case node if node.name == name => node }.get
    }

  case cmd if ls.matches(cmd.head) =>
    cur.children = cmd.tail.map {
      case line if dir.matches(line) =>
        Node(getGroup(dir, line), cur, isDir = true)
      case line if file.matches(line) =>
        Node(getGroup(file, line, 2), cur, size = getGroup(file, line).toInt)
    }
  case _ =>
}

var dirs = mutable.Stack[Int]()

def collectDirSizes(node: Node): Int = {
  node.size = node.children.map {
    case dir if dir.isDir =>
      val size = collectDirSizes(dir)
      dirs.push(size)
      size
    case node =>
      node.size
  }.sum
  node.size
}

collectDirSizes(mount)

dirs.filter(_ <= 100000).sum

val threshold = 30000000 - (70000000 - mount.size)
dirs.toArray.filter(_ >= threshold).min
