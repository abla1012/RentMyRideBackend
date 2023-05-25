package com.acme.rentmyride.security

import com.acme.rentmyride.security.AuthController.Companion.AUTH_PATH
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.TEXT_PLAIN_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.naming.AuthenticationException


@RestController
@RequestMapping("$AUTH_PATH")
class AuthController(private val service: UserValidator) {
    @ExceptionHandler(Exception::class)
    fun handleWrongPassword(e: Exception): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.UNAUTHORIZED)


    /**
     * "Einloggen" bei _Basic Authentication_.
     * @param username= admin
     * @param passwort= p
     * @return Response mit der Collection der Rollen oder Statuscode 401.
     */
    @PostMapping(produces = [TEXT_PLAIN_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun login(@RequestHeader name:String, @RequestHeader password:String) {
        return service.validatePassword(name, password)
    }


    companion object {
        /**
         * Pfad für Authentifizierung und Autorisierung
         */
        const val AUTH_PATH = "/api/auth"
    }
}
