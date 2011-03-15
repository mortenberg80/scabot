import scala.util.matching.Regex

class Parser(message: String) {

    var command = ""
    var argument = ""

    val PingRE = """PING (.*)""".r
    val NoticeRE = """NOTICE.*""".r

    def parse() = message match {
      case PingRE(pingArgument) => {
      	command = "ping"
      	argument = pingArgument
      }
      case NoticeRE() => command = "notice"
      case _ => command = ""
    }
    
    def getCommand() = command
}