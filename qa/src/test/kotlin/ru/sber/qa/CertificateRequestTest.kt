package ru.sber.qa

import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

internal class CertificateRequestTest {

    @BeforeEach
    fun setUp() {
        mockkObject(Scanner)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun process() {
        every { Scanner.getScanData() } returns Random.nextBytes(100)
        val data = Scanner.getScanData()
        val hrEmployeeNumber = 1L
        val certificateRequest = CertificateRequest(1L, CertificateType.LABOUR_BOOK)
        val certificate = certificateRequest.process(hrEmployeeNumber)

        assertEquals(certificate.processedBy, hrEmployeeNumber)
        assertEquals(certificate.data, data)
        assertEquals(certificate.certificateRequest, certificateRequest)
    }
}