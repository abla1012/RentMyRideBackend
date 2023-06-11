package com.acme.rentmyride.datasource

import com.acme.rentmyride.entity.Fahrzeug

interface FahrzeugDataSource {
    fun getFahrzeuge(): Collection<Fahrzeug>

    fun getFahrzeugeOrderByMarke(): Collection<Fahrzeug>

    fun getFahrzeugeOrderByName(): Collection<Fahrzeug>

    fun getFahrzeugeOrderByPs(): Collection<Fahrzeug>

    fun getFahrzeug(id: Int): Fahrzeug
    fun addFahrzeug(fahrzeug: Fahrzeug): String
    fun deleteFahrzeug(id: Int): String
}