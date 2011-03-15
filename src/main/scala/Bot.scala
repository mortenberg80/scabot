import java.net._
import java.io._

/**
 * Protocol: http://www.ietf.org/rfc/rfc1459.txt
 */

object Bot {
  def main(args: Array[String]) {

    val servername = "underworld.no.quakenet.org"
    //val servername = "irc.no.quakenet.eu.org"
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
  var out: PrintWriter = null
  var in: BufferedReader = null

  def connect() = {
    println("Connecting to %s:%d".format(servername, port))
    echoSocket = new Socket(servername, port)
    out = new PrintWriter(echoSocket.getOutputStream(), true)
    in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()))

    
    sendMessage("NICK %s".format(nick))
    sendMessage("USER %s %s %s :%s".format(username, hostname, servername, realname))
    // sendMessage("USER " + username + " " + hostname + " " + servername + " :" + realname)
    sendMessage("JOIN %s".format(channel))
  }

  def run() = {
    println("Starting bot")
    connect()
    
    var line = in.readLine()
    while(line != null) {
      println(line)
      
      val parser = new Parser(line)
      parser.parse()
      if("ping".equals(parser.command)) {
        sendMessage("PONG %s".format(parser.argument))
      }
      line = in.readLine()
    }
  }

  def sendMessage(message: String) {
    println("Writing message to server: " + message)
    out.println(message)
  }

  def quit() = {
    out.close()
    in.close()
    echoSocket.close()
  }


}

