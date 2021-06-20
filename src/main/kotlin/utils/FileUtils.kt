package utils

import enums.DirectionEnum
import model.LawnMower
import java.io.File
import javax.swing.JFileChooser

/**
 * Affiche une popup permettant à l'utilisateur de sélectionner un fichier contenant les données
 * de la carte et des tondeuses.
 *
 * @return Le fichier choisi par l'utilisateur si c'est le cas.
 */
fun openSelectFileModal(): File? {
    val fileChooser = JFileChooser()
    return if (fileChooser.showDialog(null, "Choisissez un fichier") == JFileChooser.APPROVE_OPTION) {
        fileChooser.selectedFile
    } else {
        null
    }
}

/**
 * Retourne le contenu d'un fichier ligne par ligne.
 */
fun parseFileData(file: File): List<String> {
    val lineList = mutableListOf<String>()
    file.bufferedReader().forEachLine { lineList.add(it) }
    return lineList
}

/**
 * Filtre la liste en deux liste distinctes contenant respectivement les coordonnées de chaque tondeuse et leurs actions.
 * Les deux listes sont zippées pour pouvoir boucler et créer la liste des tondeuses.
 */
fun parseLawnMowersData(lawnMowersData: List<String>): List<LawnMower> {
    return lawnMowersData
        .filterIndexed { index, _ -> index % 2 == 0 }
        .zip(lawnMowersData.filterIndexed { index, _ -> index % 2 == 1 })
        .map { lawnMowerData ->
            val lawnMowerInfo = lawnMowerData.first.split("\\s".toRegex())
            val actionsList = lawnMowerData.second.toCharArray()
            LawnMower(
                Integer.parseInt(lawnMowerInfo[0]),
                Integer.parseInt(lawnMowerInfo[1]),
                actionsList.toList(),
                DirectionEnum.valueOf(lawnMowerInfo[2])
            )
        }
}
