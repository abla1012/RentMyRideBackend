package com.acme.rentmyride.service

import com.acme.rentmyride.datasource.FahrzeugDataSource
import com.acme.rentmyride.entity.Fahrzeug
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap

@Service
class FahrzeugReadService(private val dataSource: FahrzeugDataSource) {

    fun getFahrzeuge(suchkriterien: MultiValueMap<String, String>): Collection<Fahrzeug> {
        if (suchkriterien.isEmpty()) {
            return dataSource.getFahrzeuge()
        }

        if (suchkriterien.size == 1) {
            val beschreibung = suchkriterien["beschreibung"]
            if (beschreibung?.size == 1) {
                return dataSource.getFahrzeugeOrderByBeschreibung()
            }

            val preis = suchkriterien["preis"]
            if (preis?.size == 1) {
                return dataSource.getFahrzeugeOrderByPreis()
            }
        }

        return dataSource.getFahrzeuge()
    }

    fun getFahrzeuge(): Collection<Fahrzeug> {
        return dataSource.getFahrzeuge()
    }


    fun getFahrzeug(fahrzeugnummer: String): Fahrzeug {
        return dataSource.getFahrzeug(fahrzeugnummer)
    }
}