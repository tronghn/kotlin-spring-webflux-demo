package com.github.tronghn.webfluxdemo

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import java.time.Duration

@Component
class ApiHandler(val apiService: ApiService) {

    fun fetchPosts(req: ServerRequest): Mono<ServerResponse> = ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(apiService.fetchPosts())

    fun fetchAlbums(req: ServerRequest): Mono<ServerResponse> = ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(apiService.fetchAlbums())

    fun streamPosts(req: ServerRequest): Mono<ServerResponse> = ServerResponse.ok()
            .contentType(MediaType.APPLICATION_STREAM_JSON)
            .body(Flux.from(apiService.fetchPosts())
                    .flatMap { post -> post?.toFlux() }
                    .delayElements(Duration.ofSeconds(1)))

    fun streamAlbums(req: ServerRequest): Mono<ServerResponse> = ServerResponse.ok()
            .contentType(MediaType.APPLICATION_STREAM_JSON)
            .body(Flux.from(apiService.fetchAlbums())
                    .flatMap { album -> album?.toFlux() }
                    .delayElements(Duration.ofSeconds(1)))
}