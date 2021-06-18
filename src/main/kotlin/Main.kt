import utils.chooseFile
import utils.parseFileData
import utils.parseLawnMowersData
import utils.validateFileData
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val selectedFile = chooseFile()
    if (selectedFile != null && validateFileData(selectedFile).isNotEmpty()) {
        val fileData = parseFileData(selectedFile)

        val mapDimensions = fileData[0].split((" "))
        val mapHeight = Integer.parseInt(mapDimensions[0])
        val mapWidth = Integer.parseInt(mapDimensions[1])

        val lawnMowersData = fileData.filterIndexed { index, _ -> index != 0 }
        val simulator = Simulator(mapHeight, mapWidth, parseLawnMowersData(lawnMowersData))
        simulator.initSimulator()
    } else {
        exitProcess(0)
    }
}
