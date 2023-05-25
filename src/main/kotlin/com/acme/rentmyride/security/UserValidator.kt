package com.acme.rentmyride.security

import org.springframework.stereotype.Service


@Service
class UserValidator {

    /**
     * Validierung eines Entity-Objekts der Klasse [CustomUser]
     *
     * @param user Das zu validierende CustomUser-Objekt
     * @return Eine Collection mit den Verletzungen der Constraints oder eine leere Collection
     */
    fun validatePassword(name: String, password: String)
    {
        if (name == "admin" && password == "p") return
        if (name == "user" && password == "p") return

        return throw Exception("Falsches Passwort")
    }
}