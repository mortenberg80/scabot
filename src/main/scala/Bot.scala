import java.net._
import java.io._

/**
 * Protocol: http://www.ietf.org/rfc/rfc1459.txt
 */

object Bot {
  def main(args: Array[String]) {

    val servername = "underworld.no.quakenet.org"
    //val servername = "irc.homelien.no"
    val port = 6667

    var bot = new Bot(servername, port)
    var botThread = new Thread(bot)
    botThread.start()

    var systemIn = new BufferedReader(new InputStreamReader(System.in))
    println("Waiting for user input")
    var line = systemIn.readLine()
    while(line != "quit") {
      bot.sendMessage(line)
      line = systemIn.readLine()
    }

    bot.quit()
  }
}

class Bot(var servername:String, var port: Int) extends Runnable {

  val nick = "Scabot"
  val username = "Scabot"
  val channel = "#oztest"
  val realname = "Scabot"
  val hostname = "0" // ignored

  var echoSocket: Socket = null
  var responder: Responder = null
  var in: BufferedReader = null

  def connect() = {
    println("Connecting to %s:%d".format(servername, port))
    echoSocket = new Socket(servername, port)
    responder = new Responder(echoSocket.getOutputStream())
    in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()))

    
    sendMessage("NICK %s".format(nick))
    sendMessage("USER %s %s %s :%s".format(username, hostname, servername, realname))
    sendMessage("JOIN %s".format(channel))
  }

  def run() = {
    println("Starting bot")
    connect()

    val pingHandler = new PingHandler(responder)
    
    var line = in.readLine()
    while(line != null) {
      println(line)
      
      if(pingHandler.canHandle(line)) pingHandler.handle(line)
      line = in.readLine()
    }
  }

  def sendMessage(message: String) {
    println("Writing message to server: " + message)
    responder.respond(message)
  }

  def quit() = {
    responder.close()
    in.close()
    echoSocket.close()
  }
}

trait Handler {
  
  def canHandle(message: String): Boolean
  def handle(message: String)
}

