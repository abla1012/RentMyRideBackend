package com.acme.rentmyride.restController

import com.acme.rentmyride.entity.FahrzeugDTO
import com.acme.rentmyride.entity.KategorieTyp
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
import java.math.BigDecimal

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
                jsonPath("$[1].beschreibung") { value("Auto2")}
            }
    }

    @Test
    fun `should return Fahrzeug with the specific UUID`() {
        //given
        val fahrzeugnummer = "00000000-0000-0000-0000-000000000000"

        //when / then
        mockMvc.get("/api/fahrzeuge/$fahrzeugnummer")
            .andDo { print() }
            .andExpect { status { isOk() } }


    }

    @Test
    fun `should not find Fahrzeug with the specific UUID`() {
        //given
        val fahrzeugnummer = "90000000-0000-0000-0000-000000000000"

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
            val newFahrzeug = FahrzeugDTO(
                beschreibung = "Auto1",
                kategorie = KategorieTyp.PKW,
                anzahlTueren = 3,
                anzahlSitze =4,
                hatKlimaanlage = true,
                preisProTag = BigDecimal(12),
                bestizer_fk = 1,
                bild = "neues Bild"
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