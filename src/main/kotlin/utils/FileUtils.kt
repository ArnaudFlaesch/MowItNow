package utils

import enums.DirectionEnum
import model.LawnMower
import java.io.File
import javax.swing.JFileChooser

fun chooseFile(): File? {
    val fileChooser = JFileChooser()
    return if (fileChooser.showDialog(null, "Choisissez un fichier") == JFileChooser.APPROVE_OPTION) {
        fileChooser.selectedFile
    } else {
        null
    }
}

fun parseFileData(file: File): List<String> {
    val lineList = mutableListOf<String>()
    file.bufferedReader().forEachLine { lineList.add(it) }
    return lineList
}

fun parseLawnMowersData(fileData: List<String>): MutableList<LawnMower> {
    // Filtre la liste en deux liste distinctes contenant respectivement les coordonnées de chaque tondeuse et leurs actions.
    // Les deux listes sont zippées pour pouvoir boucler et créer la liste des tondeuses.
    return fileData
        .filterIndexed { index, _ -> index % 2 == 0 }
        .zip(fileData.filterIndexed { index, _ -> index % 2 == 1 })
        .map { lawnMowerData ->
            val lawnMowerInfo = lawnMowerData.first.split("\\s".toRegex())
            val actionsList = lawnMowerData.second.toCharArray()
            LawnMower(
                Integer.parseInt(lawnMowerInfo[0]),
                Integer.parseInt(lawnMowerInfo[1]),
                actionsList.toList(),
                DirectionEnum.valueOf(lawnMowerInfo[2])
            )
        }.toMutableList()
}
