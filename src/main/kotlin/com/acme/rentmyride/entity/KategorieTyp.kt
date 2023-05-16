package com.acme.rentmyride.entity

import com.fasterxml.jackson.annotation.JsonValue

enum class KategorieTyp(val value : String) {
    PKW("P"),
    NUTZFAHRZEUG("N");

    @JsonValue
    override fun toString() = value
}