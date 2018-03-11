package com.github.tronghn.webfluxdemo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@Configuration
class ApiRoutes(val apiHandler: ApiHandler) {

    @Bean
    fun apiRouter() {
        router {
            ("/api" and accept(APPLICATION_JSON)).nest {
                "/posts".nest {
                    GET("/", apiHandler::fetchPosts)
                    GET("/stream", apiHandler::streamPosts)
                }
                "/albums".nest {
                    GET("/", apiHandler::fetchAlbums)
                    GET("/stream", apiHandler::streamAlbums)
                }
            }
        }
    }
}