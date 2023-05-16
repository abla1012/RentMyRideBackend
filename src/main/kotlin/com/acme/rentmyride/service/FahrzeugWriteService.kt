package com.acme.rentmyride.service

import com.acme.rentmyride.datasource.FahrzeugDataSource
import com.acme.rentmyride.entity.FahrzeugDTO
import org.springframework.stereotype.Service
import java.util.*

@Service
class FahrzeugWriteService(private val dataSource : FahrzeugDataSource) {
    fun addFahrzeug(fahrzeug: FahrzeugDTO) : UUID {

        //hier normalerweise validierung
        /* z.B: Nur kein Fashrzeug mit gleicher beschreibung -> getFahrzeugByBeschreibung müsste man dann impelemtieren
        val beschreibungVorhanden = getFahrzeugByBeschreibung(fahrzeug.beschreibung)
        if (beschreibungVorhanden != null) {
            throw IllegalArgumentException("Fahrzeug mit der Beschreibung ${fahrzeug.beschreibung} Beschreibung bereits vorhanden")
        }
         */

        return dataSource.addFahrzeug(fahrzeug)
    }

    fun deleteFahrzeug(fahrzeugnummer : String) : Unit {
        return dataSource.deleteFahrzeug(fahrzeugnummer)
    }
}