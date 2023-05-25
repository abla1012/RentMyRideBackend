package com.acme.rentmyride.config

import com.acme.rentmyride.restController.FahrzeugGetController.Companion.API_PATH
import com.acme.rentmyride.security.AuthController
import com.acme.rentmyride.security.AuthController.Companion.AUTH_PATH
import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.disable
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.*
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeRequests {
                /*
                 authorize("/api/fahrzeuge", permitAll)
                 authorize(anyRequest, authenticated)
                 nur requests an /api/fahrzeuge werden durchgelassen
                 ggf permitALl durch hasRole tauschen
                */

                val fahrzeugIdPath = "$API_PATH/*"

                // public endpoints
                authorize(GET, API_PATH, permitAll)
                authorize(GET, fahrzeugIdPath, permitAll)

                // für schreiben sollte man hier authenticated nutzen   -> (beim Request: basic auth admin p)
                authorize(POST, API_PATH, permitAll)

                // für schreiben sollte man hier authenticated nutzen   -> (beim Request: basic auth admin p)
                authorize(DELETE, fahrzeugIdPath, permitAll)

                // Login
                authorize(POST, "$AUTH_PATH", permitAll)

                // private endpoints nur mit PW zugänglich!
                authorize(anyRequest, permitAll)

            }
            formLogin { disable() }
            httpBasic { }

            csrf { disable() }
        }
        return http.build()
    }

    //https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/in-memory.html
    @Bean
    fun users(): UserDetailsService {
        val user = User.builder()
            .username("user")
            .password("p") //"{bcrypt}$2a$10\$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
            .roles("USER")
            .build()
        val admin = User.builder()
            .username("admin")
            .password("p")  //"{bcrypt}$2a$10\$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
            .roles("USER", "ADMIN")
            .build()
        return InMemoryUserDetailsManager(user, admin)
    }
}