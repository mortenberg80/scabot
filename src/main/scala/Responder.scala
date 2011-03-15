import java.io._

class Responder(out: OutputStream) {

	val writer = new PrintWriter(out, true)

	def respond(message: String) = {
		writer.println(message)
	}
}