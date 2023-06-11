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
            val marke = suchkriterien["marke"]
            if (marke?.size == 1) {
                return dataSource.getFahrzeugeOrderByMarke()
            }

            val name = suchkriterien["name"]
            if (name?.size == 1) {
                return dataSource.getFahrzeugeOrderByName()
            }

            val ps = suchkriterien["ps"]
            if (ps?.size == 1) {
                return dataSource.getFahrzeugeOrderByPs()
            }
        }

        return dataSource.getFahrzeuge()
    }

    fun getFahrzeuge(): Collection<Fahrzeug> {
        return dataSource.getFahrzeuge()
    }


    fun getFahrzeug(id: Int): Fahrzeug {
        return dataSource.getFahrzeug(id)
    }
}