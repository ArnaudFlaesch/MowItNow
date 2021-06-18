package utils

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.File

class FileUtilsTests {

    @Test
    fun testValidateFile() {
        val testFile = File(this::class.java.classLoader.getResource("mowitnow.txt").file)
        assertTrue(validateFileData(testFile))
    }
}
