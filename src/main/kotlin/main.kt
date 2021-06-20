import utils.openSelectFileModal
import utils.parseFileData
import utils.parseLawnMowersData
import utils.validateFileData
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val selectedFile = openSelectFileModal()
    if (selectedFile != null) {
        val simulator = initSimulator(selectedFile)
        simulator.startSimulator()
    } else {
        exitProcess(0)
    }
}

/**
 * Valide le contenu d'un fichier choisi par l'utilisateur et crée la simulation
 * à partir de ce contenu.
 * @param file Le fichier contenant les données de la simulation.
 * @return Un objet Simulator permettant de lancer et de récupérer les résultats de la simulation.
 */
fun initSimulator(file: File): Simulator {
    if (validateFileData(file)) {
        val fileData = parseFileData(file)

        val mapDimensions = fileData[0].split((" "))
        val mapHeight = Integer.parseInt(mapDimensions[0])
        val mapWidth = Integer.parseInt(mapDimensions[1])

        val lawnMowersData = fileData.filterIndexed { index, _ -> index != 0 }
        return Simulator(mapHeight, mapWidth, parseLawnMowersData(lawnMowersData))
    } else {
        exitProcess(0)
    }
}
