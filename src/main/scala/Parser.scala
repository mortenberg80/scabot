import scala.util.matching.Regex

class Parser(message: String) {

    var command = ""

    val PingRE = """PING""".r
    val NoticeRE = """NOTICE.*""".r

    def parse() = message match {
      case PingRE() => command = "ping"
      case NoticeRE() => command = "notice"
      case _ => command = ""
    }
    
    def getCommand() = command
}