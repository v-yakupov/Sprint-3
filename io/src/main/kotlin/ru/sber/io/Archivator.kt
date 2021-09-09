package ru.sber.io

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
class Archivator {

    /**
     * Метод, который архивирует файл logfile.log в архив logfile.zip.
     * Архив должен располагаться в том же каталоге, что и исходной файл.
     */
    fun zipLogfile(inputFile: File) {
        val outputFile = File(inputFile.parentFile.absolutePath + File.separator + inputFile.nameWithoutExtension + ".zip")
        val fis = FileInputStream(inputFile)
        val fos = FileOutputStream(outputFile)
        val zos = ZipOutputStream(fos)
        val buffer = ByteArray(4 * 1024)
        var length: Int = fis.read(buffer)

        zos.putNextEntry(ZipEntry(inputFile.name))
        while (length != -1) {
            zos.write(buffer, 0, length)
            length = fis.read(buffer)
        }
        zos.closeEntry()
        zos.close()
        fos.close()
        fis.close()
    }

    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile(inputFile: File) {
        val fis = FileInputStream(inputFile)
        val zis = ZipInputStream(fis)
        val buffer = ByteArray(4 * 1024)
        var zipEntry = zis.nextEntry
        var outputFile: File
        var fos: FileOutputStream
        var length: Int

        while (zipEntry != null) {
            outputFile = File(inputFile.parentFile.absolutePath + File.separator + zipEntry.name)
            fos = FileOutputStream(outputFile)
            length = zis.read(buffer)

            while (length != -1) {
                fos.write(buffer, 0, length)
                length = zis.read(buffer)
            }

            zis.closeEntry()
            fos.close()
            zipEntry = zis.nextEntry
        }

        zis.close()
        fis.close()
    }
}