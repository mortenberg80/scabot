import java.net._
import java.io._

object Bot {
  def main(args: Array[String]) {

    var hostname = "wineasy.se.quakenet.org"
    var port = 6667

    var bot = new Bot(hostname, port)
    var botThread = new Thread(bot)
    botThread.start()

    var systemIn = new BufferedReader(new InputStreamReader(System.in))
    var line = systemIn.readLine()
    while(line != "quit") {
      bot.sendMessage(line)
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
    out.println("NICK scabot")
    out.println("USER scabot " + hostname + " bla scabot")
    out.println("JOIN #oztest")
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
    out.write(message)
  }

  def quit() = {
    out.close()
    in.close()
    echoSocket.close()
  }


}

