package com.acme.rentmyride.service

import com.acme.rentmyride.datasource.FahrzeugDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class FahrzeugServiceTest {

    private val dataSource : FahrzeugDataSource = mockk(relaxed = true)

    val fahrzeugService = FahrzeugReadService(dataSource)

    @Test
    fun `should call its data source`() {
        //given

        //every { dataSource.getFahrzeuge() } returns listOf(Fahrzeug())
        
        //when
        val fahrzeuge = fahrzeugService.getFahrzeuge()
        
        //then
        verify(exactly = 1) { dataSource.getFahrzeuge() }
    }
    
        
        
}