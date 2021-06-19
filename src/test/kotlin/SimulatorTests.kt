import enums.DirectionEnum
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class SimulatorTests {

    @Test
    fun testSimulator() {
        val testFile = File(this::class.java.classLoader.getResource("mowitnow.txt").file)
        val updatedLawnMowers = initSimulator(testFile)

        assertEquals(2, updatedLawnMowers.size)

        assertEquals(1, updatedLawnMowers[0].coordX)
        assertEquals(3, updatedLawnMowers[0].coordY)
        assertEquals(DirectionEnum.N, updatedLawnMowers[0].direction)

        assertEquals(5, updatedLawnMowers[1].coordX)
        assertEquals(1, updatedLawnMowers[1].coordY)
        assertEquals(DirectionEnum.E, updatedLawnMowers[1].direction)
    }
}
