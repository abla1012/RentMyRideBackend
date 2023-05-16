package com.acme.rentmyride.datasource.mock

import com.acme.rentmyride.datasource.FahrzeugDataSource
import com.acme.rentmyride.entity.Fahrzeug
import com.acme.rentmyride.entity.FahrzeugDTO
import com.acme.rentmyride.entity.KategorieTyp
import org.apache.tomcat.util.http.fileupload.FileUtils
import org.springframework.stereotype.Repository
import java.io.File
import java.math.BigDecimal
import java.util.*


//@Repository for classes that are responsible for retrieving data and storing data
@Repository
class MockFahrzeugDataSource : FahrzeugDataSource {
    val bildpfad = "C:/Users/hpqtu/Downloads/Projekt_ebusiness/rentMyRideBackend/rentmyride/src/main/resources/porsche-model.png"

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
            fahrzeugnummer = UUID.fromString("00000000-0000-0000-0000-000000000000"),
            beschreibung = "Auto1",
            kategorie = KategorieTyp.PKW,
            anzahlTueren = 3,
            anzahlSitze = 4,
            hatKlimaanlage = true,
            preisProTag = BigDecimal(12),
            bestizer_fk = 1,
            bild = encodedString,
        ),
        Fahrzeug(
            fahrzeugnummer = UUID.fromString("00000000-0000-0000-0000-000000000001"),
            beschreibung = "Auto2",
            kategorie = KategorieTyp.PKW,
            anzahlTueren = 5,
            anzahlSitze = 5,
            hatKlimaanlage = true,
            preisProTag = BigDecimal(51),
            bestizer_fk = 1,
            bild = encodedString
        ),
        Fahrzeug(
            fahrzeugnummer = UUID.fromString("00000000-0000-0000-0000-000000000002"),
            beschreibung = "Sprinter",
            kategorie = KategorieTyp.NUTZFAHRZEUG,
            anzahlTueren = 4,
            anzahlSitze = 3,
            hatKlimaanlage = true,
            preisProTag = BigDecimal(72),
            bestizer_fk = 2,
            bild = encodedString
        )
    )

    override fun getFahrzeuge(): Collection<Fahrzeug> {
        return fahrzeuge
    }

    override fun getFahrzeug(fahrzeugnummer: String): Fahrzeug {
        for (f in fahrzeuge) {
            if (UUID.fromString(fahrzeugnummer) == f.fahrzeugnummer)
                return f
        }
        throw NoSuchElementException("Kein Fahrzeug mit der Fahrzeugnummer $fahrzeugnummer gefunden.")
    }

    override fun addFahrzeug(fahrzeug: FahrzeugDTO): UUID {
        val neuesFahrzeug = Fahrzeug(
            fahrzeugnummer = UUID.randomUUID(),
            beschreibung = fahrzeug.beschreibung,
            kategorie = fahrzeug.kategorie,
            anzahlTueren = fahrzeug.anzahlTueren,
            anzahlSitze = fahrzeug.anzahlSitze,
            hatKlimaanlage = fahrzeug.hatKlimaanlage,
            preisProTag = fahrzeug.preisProTag,
            bestizer_fk = fahrzeug.bestizer_fk,
            bild = fahrzeug.bild
        )
        fahrzeuge.add(neuesFahrzeug)
        return neuesFahrzeug.fahrzeugnummer
    }

    override fun deleteFahrzeug(fahrzeugnummer: String) {
        val fahrzeugnummerUUID = UUID.fromString(fahrzeugnummer)
        val fahrzeug = fahrzeuge.firstOrNull { it.fahrzeugnummer == fahrzeugnummerUUID }
            ?: throw NoSuchElementException("Kein Fahrzeug mit dieser Fahrzeugnummer vorhanden")

        fahrzeuge.remove(fahrzeug)
    }


}