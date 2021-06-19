package enums

enum class DirectionEnum {
    N,
    E,
    S,
    W;

    companion object {
        fun getNewDirection(currentDirection: DirectionEnum, movement: String): DirectionEnum {
            return if (movement == "G" || movement == "D") {
                when (currentDirection) {
                    N -> if (movement == "G") W else E
                    E -> if (movement == "G") N else S
                    S -> if (movement == "G") E else W
                    W -> if (movement == "G") S else N
                }
            } else {
                throw Exception("Mouvement inconnu")
            }
        }
    }
}
