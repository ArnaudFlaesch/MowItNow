import enums.DirectionEnum
import model.LawnMower
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class SimulatorTests {

    @Test
    fun testSimulator() {
        val testFile = File(this::class.java.classLoader.getResource("mowitnow.txt").file)
        val simulator = initSimulator(testFile)
        simulator.startSimulator()
        val updatedLawnMowers = simulator.getLawnMowers()

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
        val updatedLawnMowers = simulator.startSimulator()
        assertEquals(4, updatedLawnMowers[0].coordX)
        assertEquals(0, updatedLawnMowers[0].coordY)
        assertEquals(DirectionEnum.E, updatedLawnMowers[0].direction)

        assertEquals(5, updatedLawnMowers[1].coordX)
        assertEquals(0, updatedLawnMowers[1].coordY)
        assertEquals(DirectionEnum.W, updatedLawnMowers[1].direction)
    }

    @Test
    fun testSimulatorPreventOutOfBounds() {
        val lawnMowerList = mutableListOf(
            LawnMower(0, 0, "DAAGAAA".toList(), DirectionEnum.S),
            LawnMower(5, 0, "GAAAAA".toList(), DirectionEnum.S),
            LawnMower(0, 1, "AAAAAA".toList(), DirectionEnum.N)
        )
        val simulator = Simulator(5, 5, lawnMowerList)
        simulator.startSimulator()

        val updatedLawnMowers = simulator.getLawnMowers()

        assertEquals(0, updatedLawnMowers[0].coordX)
        assertEquals(0, updatedLawnMowers[0].coordY)
        assertEquals(DirectionEnum.S, updatedLawnMowers[0].direction)

        assertEquals(5, updatedLawnMowers[1].coordX)
        assertEquals(0, updatedLawnMowers[1].coordY)
        assertEquals(DirectionEnum.E, updatedLawnMowers[1].direction)

        assertEquals(0, updatedLawnMowers[2].coordX)
        assertEquals(5, updatedLawnMowers[2].coordY)
        assertEquals(DirectionEnum.N, updatedLawnMowers[2].direction)
    }
}
