package com.acme.rentmyride.service

import com.acme.rentmyride.datasource.FahrzeugDataSource
import com.acme.rentmyride.entity.Fahrzeug
import org.springframework.stereotype.Service

@Service
class FahrzeugReadService(private val dataSource : FahrzeugDataSource) {

    fun getFahrzeuge() : Collection<Fahrzeug> {
        return dataSource.getFahrzeuge()
    }

    fun getFahrzeug(fahrzeugnummer : String) : Fahrzeug {
        return dataSource.getFahrzeug(fahrzeugnummer)
    }
}