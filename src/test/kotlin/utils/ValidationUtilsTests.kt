package utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File

class ValidationUtilsTests {

    @Test
    fun testValidateFile() {
        val testFile = File(this::class.java.classLoader.getResource("mowitnow.txt").file)
        assertTrue(validateFileData(testFile))
    }

    @Test
    fun testValidateFileErrorNoData() {
        val testFile = File(this::class.java.classLoader.getResource("mowitnow-empty.txt").file)
        val exception = assertThrows<Exception> { validateFileData(testFile) }
        assertEquals("Les données du fichier sont incomplètes.", exception.message)
    }

    @Test
    fun testValidateFileErrorMissingMapData() {
        val testFile = File(this::class.java.classLoader.getResource("mowitnow-missingMapData.txt").file)
        val exception = assertThrows<Exception> { validateFileData(testFile) }
        assertEquals("Les données de la carte sont invalides.", exception.message)
    }

    @Test
    fun testValidateMapDataMissingParam() {
        val mapDataMissingParam = listOf("5")
        val exception = assertThrows<Exception> { validateMapData(mapDataMissingParam) }
        assertEquals("Les données de la carte sont incomplètes.", exception.message)
    }

    @Test
    fun testValidateMapDataWrongParam() {
        val mapDataWrongParam = listOf("S", "5")
        val exception = assertThrows<Exception> { validateMapData(mapDataWrongParam) }
        assertEquals("Les données de la carte sont invalides.", exception.message)
    }

    @Test
    fun testValidateMapDataTooManyParams() {
        val mapDataWrongParam = listOf("5", "S", "N")
        val exception = assertThrows<Exception> { validateMapData(mapDataWrongParam) }
        assertEquals("Les données de la carte sont invalides.", exception.message)
    }

    @Test
    fun testValidateLawnMowerDataFailMissingActions() {
        val lawnMowersData = listOf("1 2", "GAGAGAGAA", "3 3 E")
        val exception = assertThrows<Exception> { validateLawnMowersData(lawnMowersData) }
        assertEquals("Les données des tondeuses sont invalides.", exception.message)
    }

    @Test
    fun testValidateFileErrorWrongLawnMowerData() {
        val lawnMowersData = listOf("1 2", "GAGAGAGAA", "3 3 E", "GAGAGAGAA")
        val exception = assertThrows<Exception> { validateLawnMowersData(lawnMowersData) }
        assertEquals("Les données de la tondeuse 0 sont invalides.", exception.message)
    }

    @Test
    fun testValidateFileErrorWrongLawnMowerActions() {
        val lawnMowersData = listOf("1 2 N", "GAGAGAGAA", "3 3 E", "EEEE")
        val exception = assertThrows<Exception> { validateLawnMowersData(lawnMowersData) }
        assertEquals("L'action 0 de la tondeuse 1 est invalide.", exception.message)
    }
}
