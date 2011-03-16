import java.io._

class Responder(out: OutputStream) {

	val writer = new PrintWriter(out, true)

	def respond(message: String) = {
		println("Sending message %s".format(message))
		writer.println(message)
	}

	def close() = writer.close()
}