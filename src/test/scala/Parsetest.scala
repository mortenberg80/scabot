import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ParseSpec extends FlatSpec with ShouldMatchers {

  "A Parser" should "set command to 'ping' when message is PING" in {
    val parser = new Parser("PING")
    parser.parse()
    parser.command should equal ("ping")

  }

  "A Parser" should "set command to 'auth' and arguments when message is AUTH" in {
    val parser = new Parser("AUTH")
    parser.parse()
    parser.command should equal ("auth")

  }

  //it should "throw NoSuchElementException if an empty stack is popped" in {
    //val emptyStack = new Stack[String]
    //evaluating { emptyStack.pop() } should produce [NoSuchElementException]
  //}
}
