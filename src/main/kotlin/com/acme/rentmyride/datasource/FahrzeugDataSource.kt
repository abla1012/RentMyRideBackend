package com.acme.rentmyride.datasource

import com.acme.rentmyride.entity.Fahrzeug
import com.acme.rentmyride.entity.FahrzeugDTO
import java.util.UUID

interface FahrzeugDataSource {
    fun getFahrzeuge(): Collection<Fahrzeug>

    fun getFahrzeugeOrderByBeschreibung(): Collection<Fahrzeug>

    fun getFahrzeugeOrderByPreis(): Collection<Fahrzeug>

    fun getFahrzeug(fahrzeugnummer: String): Fahrzeug
    fun addFahrzeug(fahrzeug: FahrzeugDTO): Collection<Fahrzeug>
    fun deleteFahrzeug(fahrzeugnummer: String): Collection<Fahrzeug>
}