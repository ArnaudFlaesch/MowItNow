import enums.DirectionEnum
import model.LawnMower
import org.apache.logging.log4j.LogManager

class Simulator(private val height: Int, private val width: Int, private val lawnMowers: MutableList<LawnMower>) {

    private val logger = LogManager.getLogger()

    fun startSimulator(): List<LawnMower> {
        lawnMowers.forEachIndexed { index, lawnMower ->
            lawnMower.actionList.forEach { action ->
                lawnMowers[index] = when (action) {
                    'G', 'D' -> changeLawnMowerDirection(lawnMower, action.toString())
                    'A' -> moveLawnMowerForward(lawnMower)
                    else -> throw Exception("Mouvement inconnu")
                }
            }
            logger.info("La tondeuse $index a terminé de se déplacer, sa direction est ${lawnMower.direction.name} et sa position ${lawnMower.coordX}/${lawnMower.coordY}")
        }
        return lawnMowers
    }

    private fun changeLawnMowerDirection(lawnMower: LawnMower, movement: String): LawnMower {
        val newDirection = if (movement == "G" || movement == "D") {
            if (movement == "G") {
                when (lawnMower.direction) {
                    DirectionEnum.N -> DirectionEnum.W
                    DirectionEnum.E -> DirectionEnum.N
                    DirectionEnum.S -> DirectionEnum.E
                    DirectionEnum.W -> DirectionEnum.S
                }
            } else {
                when (lawnMower.direction) {
                    DirectionEnum.N -> DirectionEnum.E
                    DirectionEnum.E -> DirectionEnum.S
                    DirectionEnum.S -> DirectionEnum.W
                    DirectionEnum.W -> DirectionEnum.N
                }
            }
        } else {
            throw Exception("Mouvement inconnu")
        }
        lawnMower.direction = newDirection
        return lawnMower
    }

    private fun moveLawnMowerForward(lawnMower: LawnMower): LawnMower {
        val newCoordinates = when (lawnMower.direction) {
            DirectionEnum.N -> Pair(lawnMower.coordX, lawnMower.coordY + 1)
            DirectionEnum.E -> Pair(lawnMower.coordX + 1, lawnMower.coordY)
            DirectionEnum.S -> Pair(lawnMower.coordX, lawnMower.coordY - 1)
            DirectionEnum.W -> Pair(lawnMower.coordX - 1, lawnMower.coordY)
        }
        val isAnotherLawnMowerPresent = lawnMowers.singleOrNull {
            it.coordX == newCoordinates.first && it.coordY == newCoordinates.second
        }
        if (isAnotherLawnMowerPresent == null && newCoordinates.first >= 0 && newCoordinates.second >= 0 &&
            newCoordinates.first <= this.width && newCoordinates.second <= this.height
        ) {
            lawnMower.coordX = newCoordinates.first
            lawnMower.coordY = newCoordinates.second
        }
        return lawnMower
    }

    fun getLawnMowers(): List<LawnMower> {
        return this.lawnMowers
    }
}
