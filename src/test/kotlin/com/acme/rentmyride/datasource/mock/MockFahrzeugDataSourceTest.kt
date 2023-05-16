package com.acme.rentmyride.datasource.mock

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test

class MockFahrzeugDataSourceTest{

    private val mockDataSource = MockFahrzeugDataSource()

    @Test
    fun `should should provide a collection of fahrzeuge`() {
        //given

        //when
        val fahrzeuge = mockDataSource.getFahrzeuge()
        
        //then
        assertThat(fahrzeuge).isNotEmpty
    }
}