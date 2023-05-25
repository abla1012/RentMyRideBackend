package com.acme.rentmyride.restController

import com.acme.rentmyride.entity.Fahrzeug
import com.acme.rentmyride.restController.FahrzeugGetController.Companion.API_PATH
import com.acme.rentmyride.service.FahrzeugReadService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(API_PATH)
class FahrzeugGetController(private val service: FahrzeugReadService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFOund(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping
    fun getFahrzeuge( @RequestParam queryParams: MultiValueMap<String, String>): Collection<Fahrzeug> {
        return service.getFahrzeuge(queryParams)
    }

    @GetMapping("/{fahrzeugnummer}")
    fun getFahrzeug(@PathVariable fahrzeugnummer: String): Fahrzeug {
        return service.getFahrzeug(fahrzeugnummer)
    }

    companion object {
        const val API_PATH = "/api/fahrzeuge"
    }
}