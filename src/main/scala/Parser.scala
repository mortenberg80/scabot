class Parser(message: String) {

    var command = ""

    def parse() = message match {
      case "PING" => command = "ping"
      case "AUTH" => command = "auth"
      case _ => command = ""
    }
    
    def getCommand() = command
}
