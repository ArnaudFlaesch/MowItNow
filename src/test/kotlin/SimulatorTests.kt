import enums.DirectionEnum
import model.LawnMower
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

    @Test
    fun testSimulatorPreventCollisions() {
        val lawnMowerList = mutableListOf(
            LawnMower(0, 0, "GAAAAA".toList(), DirectionEnum.S),
            LawnMower(5, 0, "DAAAAA".toList(), DirectionEnum.S)
        )
        val simulator = Simulator(5, 5, lawnMowerList)
        val updatedLawnMowers = simulator.initSimulator()
        assertEquals(4, updatedLawnMowers[0].coordX)
        assertEquals(0, updatedLawnMowers[0].coordY)
        assertEquals(DirectionEnum.E, updatedLawnMowers[0].direction)

        assertEquals(5, updatedLawnMowers[1].coordX)
        assertEquals(0, updatedLawnMowers[1].coordY)
        assertEquals(DirectionEnum.W, updatedLawnMowers[1].direction)
    }
}
