package com.acme.rentmyride.restController

import com.acme.rentmyride.entity.Fahrzeug
import com.acme.rentmyride.restController.FahrzeugGetController.Companion.API_PATH
import com.acme.rentmyride.service.FahrzeugReadService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

// RestController Klasse für das entgegennehmen der GET Requests und deligieren an den Service und dann Antwort an den Client mit entspechenden Daten
@RestController
@RequestMapping(API_PATH)
class FahrzeugGetController(private val service: FahrzeugReadService) {

    private val logger = LoggerFactory.getLogger(FahrzeugGetController::class.java)

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFOund(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping
    fun getFahrzeuge(@RequestParam queryParams: MultiValueMap<String, String>): Collection<Fahrzeug> {
        //logger.debug("Hallo: QueryParams: ${queryParams}")
        logger.info("Hallo: QueryParams: ${queryParams}")
        return service.getFahrzeuge(queryParams)
    }

    @GetMapping("/{id}")
    fun getFahrzeug(@PathVariable id: Int): Fahrzeug {
        logger.info("Hallo: QueryParams: ${id}")
        return service.getFahrzeug(id)
    }

    companion object {
        const val API_PATH = "/api/fahrzeuge"
    }
}