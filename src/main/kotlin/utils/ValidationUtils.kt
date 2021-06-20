package utils

import enums.DirectionEnum
import java.io.File

/**
 * Valide le contenu d'un fichier censé contenir les données de la simulation (taille de la carte et données
 * des tondeuses).
 * Les données de la carte et des tondeuses sont validées dans cet ordre par des fonctions séparées.
 * @param file Le fichier choisi par l'utilisateur.
 * @throws Exception levée à la première erreur détectée dans le fichier.
 * @return True si aucune erreur n'a été detectée d'ici la fin de la fonction.
 */
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

    val mapWidth = mapData[0]
    val mapHeight = mapData[1]

    val lawnMowersData = fileData.filterIndexed { index, _ -> index != 0 }
    validateLawnMowersData(lawnMowersData, mapWidth.toInt(), mapHeight.toInt())

    return true
}

/**
 * Valide que la première ligne du fichier ait bien seulement deux entiers positifs ou égaux à zéro.
 *
 * @param
 * @throws Exception si les dimensions sont incomplètes ou invalides.
 */
@Throws(Exception::class)
fun validateMapData(mapData: List<String>) {
    if (mapData.size < 2) {
        throw Exception("Les données de la carte sont incomplètes.")
    }

    if (mapData[0].toIntOrNull() == null || mapData[1].toIntOrNull() == null || mapData.size > 2) {
        throw Exception("Les données de la carte sont invalides.")
    }

    if (mapData[0].toInt() < 0 || mapData[1].toInt() < 0) {
        throw Exception("Les données de la carte sont invalides.")
    }
}

/**
 * Valide que la liste de lignes de chaînes de caractères contiennent bien des informations
 * cohérentes permettant de positionner des tondeuses sur une carte.
 *
 * @param lawnMowerData La liste contenant les différentes lignes de données appartenant aux tondeuses.
 * @param mapHeight La hauteur de la carte.
 * @param mapWidth La largeur de la carte.
 *
 * @throws Exception dès qu'une erreur est détectée.
 */
@Throws(Exception::class)
fun validateLawnMowersData(lawnMowerData: List<String>, mapWidth: Int, mapHeight: Int) {
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

    /**
     * Les tondeuses doivent avoir des coordonnées valides (supérieures ou égales à zéro) et ne pas être en dehors de la carte.
     * La direction initiale doit être N, E, W ou S (et donc appartenir aux valeurs de l'enum DirectionEnum).
     */
    lawnMowerList.forEachIndexed { lawnMowerIndex, lawnMower ->
        val lawnMowerInfo = lawnMower.first.split("\\s".toRegex())
        val lawnMowerActions = lawnMower.second.toCharArray()
        if (lawnMowerInfo.size < 3 ||
            lawnMowerInfo[0].toIntOrNull() == null ||
            lawnMowerInfo[1].toIntOrNull() == null ||
            lawnMowerInfo[0].toInt() < 0 ||
            lawnMowerInfo[1].toInt() < 0 ||
            lawnMowerInfo[0].toInt() > mapWidth ||
            lawnMowerInfo[1].toInt() > mapHeight ||
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
