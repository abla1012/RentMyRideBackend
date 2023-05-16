package com.acme.rentmyride.restController

import com.acme.rentmyride.entity.Fahrzeug
import com.acme.rentmyride.service.FahrzeugReadService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/fahrzeuge")
class FahrzeugGetController(private val service: FahrzeugReadService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFOund(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping
    fun getFahrzeuge(): Collection<Fahrzeug> {
        return service.getFahrzeuge()
    }

    @GetMapping("/{fahrzeugnummer}")
    fun getFahrzeug(@PathVariable fahrzeugnummer: String): Fahrzeug {
        return service.getFahrzeug(fahrzeugnummer)
    }
}