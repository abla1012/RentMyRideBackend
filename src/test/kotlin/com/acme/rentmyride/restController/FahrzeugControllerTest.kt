package com.acme.rentmyride.restController

import com.acme.rentmyride.entity.Fahrzeug
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

//SpringBootTest ist ein integration test (baut die gesamte Anwendung)
@SpringBootTest
@AutoConfigureMockMvc
class FahrzeugControllerTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val objectMapper : ObjectMapper
) {
    //MockMvc: faked ein hhtprequest

    //ObjectMapper: Jackson (Serilisation für die transformation von JSON)

    val baseURL = "/api/fahrzeuge"
    
    @Test
    fun `should return all Fahrzeuge`() {
        //when/then
        mockMvc.get("/api/fahrzeuge")
            .andDo{ print() }
            .andExpect{
                status { isOk() }
                jsonPath("$[1].marke") { value("Porsche")}
            }
    }

    @Test
    fun `should return Fahrzeug with the specific ID`() {
        //given
        val fahrzeugnummer = "1"

        //when / then
        mockMvc.get("/api/fahrzeuge/$fahrzeugnummer")
            .andDo { print() }
            .andExpect { status { isOk() } }


    }

    @Test
    fun `should not find Fahrzeug with the specific ID`() {
        //given
        val fahrzeugnummer = "99"

        //when / then
        mockMvc.get("/api/fahrzeuge/$fahrzeugnummer")
            .andDo { print() }
            .andExpect { status { isNotFound() } }


    }

    @Nested
    @DisplayName("POST /api/fahrzeuge")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewFahrzeug {
        @Test
        fun `should add a new Fahrzeug`() {
            //given
            val newFahrzeug = Fahrzeug(
                marke = "BMW",
                name = "M1 Power",
                ps = 205,
                preis = 120,
                standort = "Karlsruhe",
                ausstattung = "sehr gut",
                zeitraum = "30.06.2023 - 14.07.2023",
                fotoURL = "encodedString",
                id = 5
            )
            
            //when /then
            mockMvc.post(baseURL) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newFahrzeug)
            }
                .andDo { print() }
                .andExpect { status { isCreated() } }

        }
        
            
            

    }






















}