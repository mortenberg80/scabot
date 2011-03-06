import sbt._ 
 
class Scabot(info: ProjectInfo) extends DefaultProject(info) { 
  val scalaToolsSnapshots = ScalaToolsSnapshots
  val scalatest = "org.scalatest" % "scalatest" %
    "1.3"
}
