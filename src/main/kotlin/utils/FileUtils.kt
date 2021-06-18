package utils

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

fun validateFileData(file: File): Boolean {
    return true
}

fun parseFileData(file: File): List<String> {
    val lineList = mutableListOf<String>()
    file.bufferedReader().forEachLine { lineList.add(it) }
    return lineList
}

fun parseLawnMowersData(fileData: List<String>): List<LawnMower> {
    // Filtre la liste en deux liste distinctes contenant respectivement les coordonnées de chaque tondeuse et leurs actions.
    // Les deux listes sont zippées pour pouvoir boucler et créer la liste des tondeuses.
    return fileData
        .filterIndexed { index, _ -> index % 2 == 0 }
        .zip(fileData.filterIndexed { index, _ -> index % 2 == 1 })
        .map { lawnMowerData ->
            val coordinates = lawnMowerData.first.split(" ")
            val actionsList = lawnMowerData.second.toCharArray()
            LawnMower(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]), actionsList)
        }
}
