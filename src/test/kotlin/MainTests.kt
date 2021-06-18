import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MainTests {

    @Test
    fun testMainMessage() {
        assertEquals("Hello World !", getMessage())
    }
}