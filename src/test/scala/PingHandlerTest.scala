import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers


/*
NOTICE AUTH :*** Looking up your hostname
NOTICE AUTH :*** Checking Ident
NOTICE AUTH :*** Found your hostname
PING :2192289211
*/

class PingHandlerSpec extends FlatSpec with ShouldMatchers {

	val responder = new Responder(System.out) {
		var sentMessage = ""
		override def respond(message: String) {
			sentMessage = message
		}
	}


	"A PingHandler"	should "act on a PING message" in {
		val pingHandler = new PingHandler(null)
		pingHandler.canHandle("PING") should be (true)
	}

	"A PingHandler" should "not act on a NOTICE message" in {
		val pingHandler = new PingHandler(null)
		pingHandler.canHandle("NOTICE") should be (false)
	}

	"A PingHandler" should "respond with PONG when PING is received" in {
		val pingHandler = new PingHandler(responder)
		pingHandler.handle("PING :12345")
		responder.sentMessage should equal ("PONG :12345")
	}

	"A PingHandler" should "not send any messages when NOTICE is received" in {
		responder.sentMessage = ""
		val pingHandler = new PingHandler(responder)
		pingHandler.handle("NOTICE")
		responder.sentMessage should equal ("")
	}
}