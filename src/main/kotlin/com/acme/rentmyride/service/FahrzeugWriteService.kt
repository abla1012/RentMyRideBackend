package com.acme.rentmyride.service

import com.acme.rentmyride.datasource.FahrzeugDataSource
import com.acme.rentmyride.entity.Fahrzeug
import org.springframework.stereotype.Service

@Service
class FahrzeugWriteService(private val dataSource: FahrzeugDataSource, private val readService: FahrzeugReadService) {
    fun addFahrzeug(fahrzeug: Fahrzeug): String {

        //hier normalerweise validierung
        /* z.B: Nur kein Fashrzeug mit gleicher beschreibung -> getFahrzeugByBeschreibung müsste man dann impelemtieren
        val beschreibungVorhanden = getFahrzeugByBeschreibung(fahrzeug.beschreibung)
        if (beschreibungVorhanden != null) {
            throw IllegalArgumentException("Fahrzeug mit der Beschreibung ${fahrzeug.beschreibung} Beschreibung bereits vorhanden")
        }
         */
        return dataSource.addFahrzeug(fahrzeug)
    }

    fun deleteFahrzeug(id: Int): String  {
        return dataSource.deleteFahrzeug(id)
    }
}