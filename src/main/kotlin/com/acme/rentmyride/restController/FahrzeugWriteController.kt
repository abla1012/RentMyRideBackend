package com.acme.rentmyride.restController

import com.acme.rentmyride.entity.FahrzeugDTO
import com.acme.rentmyride.service.FahrzeugWriteService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/fahrzeuge")
class FahrzeugWriteController(private val service : FahrzeugWriteService) {

    /* Wenn man Validierung macht wiift der service die illegal argument exception
     @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
     */

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFOund(e: NoSuchElementException) : ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)


    @PostMapping(consumes = [APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun addFahrzeug(@RequestBody fahrzeug: FahrzeugDTO) : UUID {
        return service.addFahrzeug(fahrzeug)
    }

    @DeleteMapping("/{fahrzeugnummer}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteFahrzeug(@PathVariable fahrzeugnummer : String) : Unit {
        return service.deleteFahrzeug(fahrzeugnummer)
    }



}