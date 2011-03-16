class PingHandler(responder: Responder) extends Handler{

	val PingRE = """PING\s*(.*)""".r

	def canHandle(message: String):Boolean = message match {
		case PingRE(argument) => true
		case _ => false
	}

	def handle(message: String) = message match {
		case PingRE(argument) => respond(argument)
		case _ => 
	}

	def respond(argument: String) = {
		responder.respond("PONG %s".format(argument))
	}
}