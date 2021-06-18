package utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File

class FileUtilsTests {

    @Test
    fun testValidateFile() {
        val testFile = File(this::class.java.classLoader.getResource("mowitnow.txt").file)
        assertEquals(5, validateFileData(testFile).size)
    }

    @Test
    fun testValidateFileErrorNoData() {
        val testFile = File(this::class.java.classLoader.getResource("mowitnow-empty.txt").file)
        val exception = assertThrows<Exception>({ "Devrait échouer à cause de l'absence de données dans le fichier" }) {
            validateFileData(testFile)
        }
        assertEquals(exception.message, "Les données du fichier sont incomplètes")
    }

    @Test
    fun testParseFile() {
        val testFile = File(this::class.java.classLoader.getResource("mowitnow.txt").file)
        val lineList = parseFileData(testFile)
        assertEquals("5 5", lineList[0])
        assertEquals("1 2 N", lineList[1])
        assertEquals("GAGAGAGAA", lineList[2])
        assertEquals("3 3 E", lineList[3])
        assertEquals("AADAADADDA", lineList[4])
    }

    @Test
    fun testParseLawnMowerData() {
        val dataList = listOf("1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA")
        val lawnMowerList = parseLawnMowersData(dataList)
        assertEquals(2, lawnMowerList.size)

        assertEquals(1, lawnMowerList[0].coordX)
        assertEquals(2, lawnMowerList[0].coordY)
        assertEquals(9, lawnMowerList[0].actionList.size)
        assertTrue(lawnMowerList[0].actionList.map { it.toString() }.containsAll(listOf("G", "A", "G", "A", "G", "A", "G", "A", "A")))

        assertEquals(3, lawnMowerList[1].coordX)
        assertEquals(3, lawnMowerList[1].coordY)
        assertEquals(10, lawnMowerList[1].actionList.size)
        assertTrue(lawnMowerList[1].actionList.map { it.toString() }.containsAll(listOf("A", "A", "D", "A", "A", "D", "A", "D", "D", "A")))
    }
}
