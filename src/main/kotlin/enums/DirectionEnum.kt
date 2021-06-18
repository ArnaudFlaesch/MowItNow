package enums

enum class DirectionEnum {
    N,
    E,
    S,
    W;

    companion object {
        fun getNewDirection(currentDirection: DirectionEnum, movement: String): DirectionEnum {
            val indexOfCurrentDirection = values().indexOf(currentDirection)
            val index = if (movement == "G") {
                if (indexOfCurrentDirection == 0) {
                    3
                } else {
                    indexOfCurrentDirection - 1
                }
            } else if (movement == "D") {
                if (indexOfCurrentDirection == 3) {
                    0
                } else {
                    indexOfCurrentDirection + 1
                }
            } else {
                throw Exception("Mouvement inconnu")
            }
            return values()[index]
        }
    }
}
