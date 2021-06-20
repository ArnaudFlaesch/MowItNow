package utils

import enums.DirectionEnum
import java.io.File

@Throws(Exception::class)
fun validateFileData(file: File): Boolean {
    val fileData: List<String> = parseFileData(file)

    // On a besoin d'au moins trois lignes contenant potentiellement la taille de la carte et les données
    // d'une tondeuse
    if (fileData.size < 3) {
        throw Exception("Les données du fichier sont incomplètes.")
    }

    val mapData = fileData[0].split("\\s".toRegex())
    validateMapData(mapData)

    val lawnMowersData = fileData.filterIndexed { index, _ -> index != 0 }
    validateLawnMowersData(lawnMowersData)

    return true
}

@Throws(Exception::class)
fun validateMapData(mapData: List<String>) {
    if (mapData.size < 2) {
        throw Exception("Les données de la carte sont incomplètes.")
    }

    if (mapData[0].toIntOrNull() == null || mapData[1].toIntOrNull() == null || mapData.size > 2) {
        throw Exception("Les données de la carte sont invalides.")
    }
}

@Throws(Exception::class)
fun validateLawnMowersData(lawnMowerData: List<String>) {
    /**
     *  Les lignes concernant les données des tondeuses doivent être un nombre pair
     *  car il y a une ligne pour les coordonnées et la position et une ligne pour les déplacements.
     */
    if (lawnMowerData.size % 2 != 0) {
        throw Exception("Les données des tondeuses sont invalides.")
    }

    val lawnMowerList = lawnMowerData.filterIndexed { index, _ -> index % 2 == 0 }
        .zip(lawnMowerData.filterIndexed { index, _ -> index % 2 == 1 })

    val possibleDirections = DirectionEnum.values().map { it.name }
    val possibleActions = listOf("A", "G", "D")

    lawnMowerList.forEachIndexed { lawnMowerIndex, lawnMower ->
        val lawnMowerInfo = lawnMower.first.split("\\s".toRegex())
        val lawnMowerActions = lawnMower.second.toCharArray()
        if (lawnMowerInfo.size < 3 || lawnMowerInfo[0].toIntOrNull() == null || lawnMowerInfo[1].toIntOrNull() == null ||
            !possibleDirections.contains(lawnMowerInfo[2])
        ) {
            throw Exception("Les données de la tondeuse $lawnMowerIndex sont invalides.")
        }
        lawnMowerActions.forEachIndexed { lawnMowerActionIndex, action ->
            if (!possibleActions.contains(action.toString())) {
                throw Exception("L'action $lawnMowerActionIndex de la tondeuse $lawnMowerIndex est invalide.")
            }
        }
    }
}
