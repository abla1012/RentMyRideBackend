package com.acme.rentmyride

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

// Klasse wird beim Start der Applikation aufgerufen. Alles wird Konfiguriert und Server startet.
@SpringBootApplication
class RentMyRideBackendApplication

fun main(args: Array<String>) {
    runApplication<RentMyRideBackendApplication>(*args)
}
