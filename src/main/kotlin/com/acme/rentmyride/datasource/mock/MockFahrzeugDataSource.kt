package com.acme.rentmyride.datasource.mock

import com.acme.rentmyride.datasource.FahrzeugDataSource
import com.acme.rentmyride.entity.Fahrzeug
import org.springframework.stereotype.Repository
import java.io.File
import java.util.*

// Mock Klasse die für das Abrufen von Daten und das Speichern von Daten zuständig ist

@Repository
// @Repository für Klassen, die für das Abrufen von Daten und das Speichern von Daten zuständig sind
class MockFahrzeugDataSource : FahrzeugDataSource {
    val bildpfadPorsche = "src/main/resources/porsche-model.png"
    val bildpfadBMW = "src/main/resources/BmwM1.jpg"
    val bildpfadC63 = "src/main/resources/c63amg.jpg"
    val bildpfadDacia = "src/main/resources/Dacia.jpg"
    val bildpfadFiat = "src/main/resources/FiatPunto.JPG"


    // Bild convertieren in Bytes und dann als string umwandeln
    private fun converPicture(bildpfad: String): String {
        val fileContent: ByteArray = File(bildpfad).readBytes()
        return Base64.getEncoder().encodeToString(fileContent)
    }

    // im frontent zum decoden
    /*
    val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
    val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length)
    imageView.setImageBitmap(decodedImage)
    */


    // Mockdaten
    val fahrzeuge = mutableListOf(
        Fahrzeug(
            marke = "BMW",
            name = "M1 Power",
            ps = 205,
            preis = 120,
            standort = "Karlsruhe",
            ausstattung = "sehr gut",
            zeitraum = "30.06.2023 - 14.07.2023",
            fotoURL = converPicture(bildpfadBMW),
            id = 5
        ),
        Fahrzeug(
            marke = "Porsche",
            name = "911",
            ps = 605,
            preis = 1220,
            standort = "Karlsruhe",
            ausstattung = "sehr gut",
            zeitraum = "30.06.2023 - 14.07.2023",
            fotoURL = converPicture(bildpfadPorsche),
            id = 1
        ),
        Fahrzeug(
            marke = "Benz",
            name = "C63 AMG",
            ps = 305,
            preis = 300,
            standort = "Karlsruhe",
            ausstattung = "sehr gut",
            zeitraum = "30.06.2023 - 14.07.2023",
            fotoURL = converPicture(bildpfadC63),
            id = 2
        ),
        Fahrzeug(
            marke = "Fiat",
            name = "Punto",
            ps = 99,
            preis = 12,
            standort = "Karlsruhe",
            ausstattung = "sehr gut",
            zeitraum = "30.06.2023 - 14.07.2023",
            fotoURL = converPicture(bildpfadFiat),
            id = 3
        ),
        Fahrzeug(
            marke = "Dacia",
            name = "M",
            ps = 5,
            preis = 120,
            standort = "Karlsruhe",
            ausstattung = "gut",
            zeitraum = "30.06.2023 - 14.07.2023",
            fotoURL = converPicture(bildpfadDacia),
            id = 4
        ),
        Fahrzeug(
            marke = "Unbekannt",
            name = "Unbekannt",
            ps = 555,
            preis = 999,
            standort = "Karlsruhe",
            ausstattung = "gut",
            zeitraum = "30.06.2023 - 14.07.2023",
            fotoURL = "Unbekannt",
            id = 6
        )
    )

    // Mock funktionen für das laden, speichern und löschen der Daten aus dem Repository
    override fun getFahrzeuge(): Collection<Fahrzeug> {
        return fahrzeuge
    }

    override fun getFahrzeugeOrderByMarke(): Collection<Fahrzeug> {
        return fahrzeuge.sortedBy { it.marke }
    }

    override fun getFahrzeugeOrderByName(): Collection<Fahrzeug> {
        return fahrzeuge.sortedBy { it.name }
    }

    override fun getFahrzeugeOrderByPs(): Collection<Fahrzeug> {
        return fahrzeuge.sortedBy { it.ps }
    }

    override fun getFahrzeug(id: Int): Fahrzeug {
        for (f in fahrzeuge) {
            if (id == f.id)
                return f
        }
        throw NoSuchElementException("Kein Fahrzeug mit der Fahrzeugnummer $id gefunden.")
    }

    override fun addFahrzeug(fahrzeug: Fahrzeug): String {
        val neuesFahrzeug = Fahrzeug(
            marke = fahrzeug.marke,
            name = fahrzeug.name,
            ps = fahrzeug.ps,
            preis = fahrzeug.preis,
            standort = fahrzeug.standort,
            ausstattung = fahrzeug.ausstattung,
            zeitraum = fahrzeug.zeitraum,
            fotoURL = fahrzeug.fotoURL,
            id = fahrzeug.id
        )
        fahrzeuge.add(neuesFahrzeug)
        return neuesFahrzeug.id.toString()
    }

    override fun deleteFahrzeug(id: Int): String {
        val fahrzeug = fahrzeuge.firstOrNull { it.id == id }
            ?: throw NoSuchElementException("Kein Fahrzeug mit dieser Fahrzeugnummer vorhanden")

        fahrzeuge.remove(fahrzeug)

        return "ok Brudi"
    }


}