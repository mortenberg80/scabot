import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers


/*
NOTICE AUTH :*** Looking up your hostname
NOTICE AUTH :*** Checking Ident
NOTICE AUTH :*** Found your hostname
PING :2192289211
*/

class ParseSpec extends FlatSpec with ShouldMatchers {

  "A Parser" should "set command to 'ping' when message is PING :12345" in {
    val parser = new Parser("PING :12345")
    parser.parse()
    parser.command should equal ("ping")
  }

  "A Parser" should "set command argument to 12345 value when message is PING :12345" in {
    val parser = new Parser("PING :12345")
    parser.parse()
    parser.argument should equal (":12345")
  }

  "A Parser" should "set command to 'notice' when message is NOTICE" in {
    val parser = new Parser("NOTICE AUTH :*** Looking up your hostname")
    parser.parse()
    parser.command should equal ("notice")
  }

  //it should "throw NoSuchElementException if an empty stack is popped" in {
    //val emptyStack = new Stack[String]
    //evaluating { emptyStack.pop() } should produce [NoSuchElementException]
  //}
}
