package enums

enum class DirectionEnum {
    N,
    E,
    S,
    W;

    companion object {
        fun getNewDirection(currentDirection: DirectionEnum, movement: String): DirectionEnum {
            return if (movement == "G" || movement == "D") {
                if (movement == "G") {
                    when (currentDirection) {
                        N -> W
                        E -> N
                        S -> E
                        W -> S
                    }
                } else {
                    when (currentDirection) {
                        N -> E
                        E -> S
                        S -> W
                        W -> N
                    }
                }
            } else {
                throw Exception("Mouvement inconnu")
            }
        }
    }
}
