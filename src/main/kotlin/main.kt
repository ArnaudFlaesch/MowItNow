import utils.openSelectFileModal
import utils.parseFileData
import utils.parseLawnMowersData
import utils.validateFileData
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val selectedFile = openSelectFileModal()
    if (selectedFile != null && validateFileData(selectedFile)) {
        val simulator = initSimulator(selectedFile)
        simulator.startSimulator()
    } else {
        exitProcess(0)
    }
}

fun initSimulator(file: File): Simulator {
    val fileData = parseFileData(file)

    val mapDimensions = fileData[0].split((" "))
    val mapHeight = Integer.parseInt(mapDimensions[0])
    val mapWidth = Integer.parseInt(mapDimensions[1])

    val lawnMowersData = fileData.filterIndexed { index, _ -> index != 0 }
    return Simulator(mapHeight, mapWidth, parseLawnMowersData(lawnMowersData))
}
