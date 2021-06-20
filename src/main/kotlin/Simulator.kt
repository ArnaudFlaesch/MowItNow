import enums.DirectionEnum
import model.LawnMower
import org.apache.logging.log4j.LogManager

class Simulator(private val height: Int, private val width: Int, private val lawnMowers: MutableList<LawnMower>) {

    private val logger = LogManager.getLogger()

    /**
     * Exécute tour à tour la liste des actions de chaque tondeuse de la simulation.
     * Après que chaque tondeuse ait fini de se déplacer, ses nouvelles coordonnées sont affichées.
     */
    fun startSimulator(): List<LawnMower> {
        lawnMowers.forEachIndexed { index, lawnMower ->
            lawnMower.actionList.forEach { action ->
                lawnMowers[index] = when (action) {
                    'G', 'D' -> changeLawnMowerDirection(lawnMower, action.toString())
                    'A' -> moveLawnMowerForward(lawnMower)
                    else -> throw Exception("Mouvement inconnu")
                }
            }
            logger.info(lawnMowerStatusMessage(lawnMower, index))
        }
        return lawnMowers
    }

    /**
     * Détermine la nouvelle direction d'une tondeuse à partir de son mouvement (Gauche ou Droite)
     * et retourne la tondeuse modifiée.
     * @param lawnMower La tondeuse en cours de déplacement.
     * @param movement L'action en cours.
     * @return L'objet Tondeuse modifié.
     */
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

    /**
     * Avance la tondeuse en fonction de sa direction et modifie ses coordonnées.
     * Les coordonnées ne sont pas modifiées si elles dépassent la taille de la carte ou si
     * une tondeuse se trouve déjà à cet endroit.
     * @param lawnMower La tondeuse en cours de déplacement.
     * @return L'objet Tondeuse avec éventuellement ses coordonnées modifiées.
     */
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

    private fun lawnMowerStatusMessage(lawnMower: LawnMower, index: Int): String {
        return "La tondeuse $index a terminé de se déplacer, sa direction est ${lawnMower.direction.name} \n" +
            " et sa position X:${lawnMower.coordX} Y:${lawnMower.coordY}"
    }
}
