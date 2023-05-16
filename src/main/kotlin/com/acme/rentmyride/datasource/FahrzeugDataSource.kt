package com.acme.rentmyride.datasource

import com.acme.rentmyride.entity.Fahrzeug
import com.acme.rentmyride.entity.FahrzeugDTO
import java.util.UUID

interface FahrzeugDataSource {
    fun getFahrzeuge() : Collection<Fahrzeug>
    fun getFahrzeug(fahrzeugnummer : String) : Fahrzeug
    fun addFahrzeug(fahrzeug: FahrzeugDTO) : UUID
    fun deleteFahrzeug(fahrzeugnummer: String) : Unit
}