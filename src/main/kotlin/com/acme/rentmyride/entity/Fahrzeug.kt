package com.acme.rentmyride.entity

data class Fahrzeug(
    val marke: String,
    val name: String,
    val ps: Int,
    val preis: Int,
    val standort: String,
    val ausstattung: String,
    val zeitraum: String,
    val fotoURL: String,
    val id: Int = 0
)
