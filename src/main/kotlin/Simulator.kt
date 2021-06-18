import enums.DirectionEnum
import model.LawnMower
import org.apache.logging.log4j.LogManager

class Simulator(private val height: Int, val width: Int, private val lawnMowers: MutableList<LawnMower>) {

    private val logger = LogManager.getLogger()

    fun initSimulator(): List<LawnMower> {
        lawnMowers.forEachIndexed { index, lawnMower  ->
            lawnMower.actionList.forEach { action ->
                lawnMowers[index] = when (action) {
                    'G', 'D' -> changeLawnMowerDirection(lawnMower, action.toString())
                    'A' ->  moveLawnMowerForward(lawnMower)
                    else -> throw Exception("Mouvement inconnu")
                }
                println(lawnMowers[index])
            }
        }
        return lawnMowers
    }

    private fun changeLawnMowerDirection(lawnMower: LawnMower, action: String): LawnMower {
        lawnMower.direction = DirectionEnum.getNewDirection(lawnMower.direction, action)
        return lawnMower
    }

    private fun moveLawnMowerForward(lawnMower: LawnMower): LawnMower {
        val newCoordinates = when (lawnMower.direction) {
            DirectionEnum.N -> Pair(lawnMower.coordX, lawnMower.coordY + 1)
            DirectionEnum.E-> Pair(lawnMower.coordX + 1, lawnMower.coordY)
            DirectionEnum.S -> Pair(lawnMower.coordX, lawnMower.coordY - 1)
            DirectionEnum.W -> Pair(lawnMower.coordX - 1, lawnMower.coordY)
        }
        val isAnotherLawnMowerPresent = lawnMowers.singleOrNull {
            it.coordX == newCoordinates.first && it.coordY == newCoordinates.second
        }
        if (isAnotherLawnMowerPresent == null && newCoordinates.first >= 0 && newCoordinates.second >= 0) {
            lawnMower.coordX = newCoordinates.first
            lawnMower.coordY = newCoordinates.second
        }
        return lawnMower
    }



}
