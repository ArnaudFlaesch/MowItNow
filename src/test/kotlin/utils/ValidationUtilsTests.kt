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
        val exception = assertThrows<Exception>({ "Devrait échouer à cause de l'absence de données dans le fichier" }) {
            validateFileData(testFile)
        }
        assertEquals("Les données du fichier sont incomplètes.", exception.message)
    }

    @Test
    fun testValidateFileErrorMissingMapData() {
        val testFile = File(this::class.java.classLoader.getResource("mowitnow-missingMapData.txt").file)
        val exception = assertThrows<Exception>({ "Devrait échouer à cause de l'absence de données dans le fichier" }) {
            validateFileData(testFile)
        }
        assertEquals("Les données de la carte sont invalides.", exception.message)
    }

    @Test
    fun testValidateFileErrorWrongMapData() {
        val mapData = listOf("5")
        val exception = assertThrows<Exception>({ "Devrait échouer à cause de l'absence de données dans le fichier" }) {
            validateMapData(mapData)
        }
        assertEquals("Les données de la carte sont incomplètes.", exception.message)
    }

    @Test
    fun testValidateFileErrorWrongLawnMowerActions() {
        val lawnMowersData = listOf("1 2 N", "GAGAGAGAA", "3 3 E", "EEEE")
        val exception = assertThrows<Exception>({ "Devrait échouer à cause de l'absence de données dans le fichier" }) {
            validateLawnMowersData(lawnMowersData)
        }
        assertEquals("L'action 0 de la tondeuse 1 est invalide.", exception.message)
    }
}
