package com.acme.rentmyride.security

import org.springframework.stereotype.Service


@Service
class UserValidator {

    /**
     * Validierung des Users
     */
    fun validatePassword(name: String, password: String)
    {
        if (name == "admin" && password == "p") return
        if (name == "user" && password == "p") return

        return throw Exception("Falsches Passwort")
    }
}