import utils.chooseFile
import utils.parseLawnMowersData
import utils.validateFileData

fun main(args: Array<String>) {
    val selectedFile = chooseFile()
    if (selectedFile != null && validateFileData(selectedFile)) {
        val lineList = mutableListOf<String>()
        selectedFile.bufferedReader().forEachLine { lineList.add(it) }
        lineList.forEach { println(">  " + it) }

        val mapDimensions = lineList[0].split((" "))
        val mapHeight = Integer.parseInt(mapDimensions[0])
        val mapWidth = Integer.parseInt(mapDimensions[1])

        val lawnMowersData = lineList.filterIndexed { index, _ -> index != 0 }
        val simulator = Simulator(mapHeight, mapWidth, parseLawnMowersData(lawnMowersData))
        simulator.initSimulator()
    }

}

