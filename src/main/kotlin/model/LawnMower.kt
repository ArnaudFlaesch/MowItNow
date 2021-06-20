package model

import enums.DirectionEnum

data class LawnMower(

    var coordX: Int,
    var coordY: Int,
    val actionList: List<Char>,
    var direction: DirectionEnum
)
