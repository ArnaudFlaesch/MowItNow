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
    fun testValidateFileErrorWrongMapData() {
        val testFile = File(this::class.java.classLoader.getResource("mowitnow-wrongMapData.txt").file)
        val exception = assertThrows<Exception>({ "Devrait échouer à cause de l'absence de données dans le fichier" }) {
            validateFileData(testFile)
        }
        assertEquals("Les données de la carte sont incomplètes.", exception.message)
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
    fun testValidateFileErrorMissingLawnMowerData() {
        val testFile = File(this::class.java.classLoader.getResource("mowitnow-wrongLawnMowerData.txt").file)
        val exception = assertThrows<Exception>({ "Devrait échouer à cause de l'absence de données dans le fichier" }) {
            validateFileData(testFile)
        }
        assertEquals("L'action 0 de la tondeuse 0 est invalide.", exception.message)
    }
}