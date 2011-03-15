class PingHandler(responder: Responder) {

	val PingRE = """PING\s*(.*)""".r

	def canHandle(message: String) = message match {
		case PingRE(argument) => true
		case _ => false
	}

	def handle(message: String) = message match {
		case PingRE(argument) => respond(argument)
	}

	def respond(argument: String) = {
		responder.respond("PONG %s".format(argument))
	}
}