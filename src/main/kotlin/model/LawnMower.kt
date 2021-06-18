package model

import enums.DirectionEnum

data class LawnMower(

    val coordX : Int,
    val coordY: Int,
    val actionList: CharArray,
    var direction: DirectionEnum = DirectionEnum.NORTH
)