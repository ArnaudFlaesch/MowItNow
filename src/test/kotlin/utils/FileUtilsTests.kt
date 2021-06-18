package utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.File

class FileUtilsTests {

    @Test
    fun testValidateFile() {
        val testFile = File(this::class.java.classLoader.getResource("mowitnow.txt").file)
        assertTrue(validateFileData(testFile))
    }

    @Test
    fun testParseFile() {
        val testFile = File(this::class.java.classLoader.getResource("mowitnow.txt").file)
        val lineList = parseFileData(testFile)
        assertEquals("5 5", lineList[0])
        assertEquals("1 2", lineList[1])
        assertEquals("NGAGAGAGAA", lineList[2])
        assertEquals("3 3", lineList[3])
        assertEquals("EAADAADADDA", lineList[4])
    }

    @Test
    fun testParseLawnMowerData() {
        val dataList = listOf("1 2", "NGAGAGAGAA", "3 3", "EAADAADADDA")
        val lawnMowerList = parseLawnMowersData(dataList)
        assertEquals(2, lawnMowerList.size)

        assertEquals(1, lawnMowerList[0].coordX)
        assertEquals(2, lawnMowerList[0].coordY)
        assertEquals(10, lawnMowerList[0].actionList.size)
        assertTrue(lawnMowerList[0].actionList.map { it.toString() }.containsAll(listOf("N", "G", "A", "G", "A", "G", "A", "G", "A", "A")))

        assertEquals(3, lawnMowerList[1].coordX)
        assertEquals(3, lawnMowerList[1].coordY)
        assertEquals(11, lawnMowerList[1].actionList.size)
        assertTrue(lawnMowerList[1].actionList.map { it.toString() }.containsAll(listOf("E", "A", "A", "D", "A", "A", "D", "A", "D", "D", "A")))
    }
}
