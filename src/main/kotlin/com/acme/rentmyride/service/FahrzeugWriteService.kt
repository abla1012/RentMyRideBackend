package com.acme.rentmyride.service

import com.acme.rentmyride.datasource.FahrzeugDataSource
import com.acme.rentmyride.entity.Fahrzeug
import com.acme.rentmyride.entity.FahrzeugDTO
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap

@Service
class FahrzeugWriteService(private val dataSource: FahrzeugDataSource, private val readService: FahrzeugReadService) {
    fun addFahrzeug(fahrzeug: FahrzeugDTO): Collection<Fahrzeug> {

        //hier normalerweise validierung
        /* z.B: Nur kein Fashrzeug mit gleicher beschreibung -> getFahrzeugByBeschreibung müsste man dann impelemtieren
        val beschreibungVorhanden = getFahrzeugByBeschreibung(fahrzeug.beschreibung)
        if (beschreibungVorhanden != null) {
            throw IllegalArgumentException("Fahrzeug mit der Beschreibung ${fahrzeug.beschreibung} Beschreibung bereits vorhanden")
        }
         */
        dataSource.addFahrzeug(fahrzeug)

        return readService.getFahrzeuge()
    }

    fun deleteFahrzeug(fahrzeugnummer: String): Collection<Fahrzeug>  {
        dataSource.deleteFahrzeug(fahrzeugnummer)

        return readService.getFahrzeuge()
    }
}