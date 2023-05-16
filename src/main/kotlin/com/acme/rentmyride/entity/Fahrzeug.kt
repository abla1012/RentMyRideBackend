package com.acme.rentmyride.entity

import java.math.BigDecimal
import java.util.UUID

data class Fahrzeug(
    //@JsonIgnore
    val fahrzeugnummer: UUID,
    val beschreibung: String,
    val kategorie: KategorieTyp,
    val anzahlTueren: Int,
    val hatKlimaanlage: Boolean,
    val anzahlSitze: Int,
    val preisProTag: BigDecimal,
    val bestizer_fk: Int,
    val bild: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Fahrzeug
        return fahrzeugnummer != null && fahrzeugnummer == other.fahrzeugnummer
    }

    /**
     * Hashwert aufgrund der Fahrzeugnummer
     * @return Der Hashwert.
     */
    override fun hashCode() = fahrzeugnummer.hashCode()

    override fun toString() = "$fahrzeugnummer  | $beschreibung"

    /**
     * Properties überschreiben, z.B. bei PUT-Requests von der REST-Schnittstelle
     * @param beschreibung Ein transientes FahrzeugObjekt mit den neuen Werten für die Properties
    @Suppress("DataClassContainsFunctions")
    fun set(neu: Fahrzeug) {
    beschreibung = neu.beschreibung
    bild = neu.bild
    kilometerstand = neu.kilometerstand
    erstzulassung = neu.erstzulassung
    fahrzeugtyp = neu.fahrzeugtyp
    fahrzeughalter = neu.fahrzeughalter
    }
     */
}