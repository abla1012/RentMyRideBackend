package com.acme.rentmyride.datasource.mock

import com.acme.rentmyride.datasource.FahrzeugDataSource
import com.acme.rentmyride.entity.Fahrzeug
import org.springframework.stereotype.Repository
import java.io.File
import java.util.*


//@Repository for classes that are responsible for retrieving data and storing data
@Repository
class MockFahrzeugDataSource : FahrzeugDataSource {
    val bildpfad =
        "src/main/resources/porsche-model.png"

    var fileContent: ByteArray = File(bildpfad).readBytes()
    var encodedString = Base64.getEncoder().encodeToString(fileContent)

    // im frontent zum decoden
    /*
    val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
    val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length)
    imageView.setImageBitmap(decodedImage)
    */


    val fahrzeuge = mutableListOf(
        Fahrzeug(
            marke = "BMW",
            name = "M1 Power",
            ps = 205,
            preis = 120,
            standort = "Karlsruhe",
            ausstattung = "sehr gut",
            zeitraum = "30.06.2023 - 14.07.2023",
            fotoURL = encodedString,
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
            fotoURL = encodedString,
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
            fotoURL = encodedString,
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
            fotoURL = encodedString,
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
            fotoURL = encodedString,
            id = 4
        )
    )
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