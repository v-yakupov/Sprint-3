package ru.sber.qa

import io.mockk.*
import org.junit.jupiter.api.*

import java.time.*

internal class HrDepartmentTest {
    private val monday = LocalDate.of(2021, 9, 6).atStartOfDay(ZoneOffset.UTC).toInstant()
    private val tuesday = LocalDate.of(2021, 9, 7).atStartOfDay(ZoneOffset.UTC).toInstant()
    private val sunday = LocalDate.of(2021, 9, 11).atStartOfDay(ZoneOffset.UTC).toInstant()
    private val hrDepartment =  mockkClass(HrDepartment::class)
    private val certNDFL = mockk<CertificateRequest>()
    private val certLABOUR = mockk<CertificateRequest>()

    @BeforeEach
    fun setUp() {
        every { certNDFL.certificateType } returns CertificateType.NDFL
        every { certLABOUR.certificateType } returns CertificateType.LABOUR_BOOK
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun receiveRequest() {
        every { hrDepartment.clock } returns Clock.fixed(sunday, ZoneOffset.UTC.normalized())
        assertThrows<WeekendDayException> { HrDepartment.receiveRequest(certLABOUR) }

        every { hrDepartment.clock } returns Clock.fixed(monday, ZoneOffset.UTC.normalized())
        assertThrows<NotAllowReceiveRequestException> { HrDepartment.receiveRequest(certLABOUR) }
        assertDoesNotThrow { HrDepartment.receiveRequest(certNDFL) }

        every { hrDepartment.clock } returns Clock.fixed(tuesday, ZoneOffset.UTC.normalized())
        assertThrows<NotAllowReceiveRequestException> { HrDepartment.receiveRequest(certNDFL) }
        assertDoesNotThrow { HrDepartment.receiveRequest(certLABOUR) }
    }
}