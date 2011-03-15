import java.net._
import java.io._

/*
  HOST = 'irc.homelien.no'
  PORT = 6667
  NICK = 'mynick'
  IDENT = 'pybot'
  REALNAME = 'I am'
  CHANNEL = '#mychannel'
*/

object Bot {
  def main(args: Array[String]) {

    val hostname = "irc.no.quakenet.eu.org"
    //val hostname = "irc.homelien.no"
    val port = 6667

    var bot = new Bot(hostname, port)
    var botThread = new Thread(bot)
    botThread.start()

    var systemIn = new BufferedReader(new InputStreamReader(System.in))
    var line = systemIn.readLine()
    while(line != "quit") {
      bot.sendMessage(line)
      line = systemIn.readLine()
    }
    println("Hello, world!")

    bot.quit()
  }
}

class Bot(var hostname:String, var port: Int) extends Runnable {

  var echoSocket: Socket = null
  var out: PrintWriter = null
  var in: BufferedReader = null

  def connect() = {
    echoSocket = new Socket(hostname, port)
    out = new PrintWriter(echoSocket.getOutputStream(), true)
    in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()))

    sendMessage("NICK Scabot")
    sendMessage("USER Scabot 0 :TheScalaBot")
    sendMessage("JOIN #oztest")
  }

  def run() = {
    connect()
    
    var line = in.readLine()
    while(line != null) {
      println(line)
      line = in.readLine()
      val tokens = line.split(" ")
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

