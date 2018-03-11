package com.github.tronghn.webfluxdemo

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@Service
class ApiService {
    // TODO: Error handling
    fun fetch(path: String): WebClient.ResponseSpec {
        val client = WebClient.create("http://jsonplaceholder.typicode.com")
        return client.get().uri(path).retrieve()
    }

    fun fetchComments(postId: Int): Flux<Comment> = fetch("posts/$postId/comments").bodyToFlux()

    fun fetchPhotos(albumId: Int): Flux<Photo> = fetch("albums/$albumId/photos").bodyToFlux()

    fun fetchPosts(): Mono<List<PostsResponse>> = fetch("/posts")
            .bodyToFlux<Post>()
            .flatMap { post -> fetchComments(post.id)
                    .map { comment ->
                        CommentStripped(email = comment.email, body = comment.body,
                                name = comment.name)
                    }
                    .collectList()
                    .zipWith(post.toMono()) }
            .map { result ->
                PostsResponse(
                        postId = result.t2.id,
                        userId = result.t2.userId,
                        title = result.t2.title,
                        comments = result.t1
                )
            }
            .collectList()

    fun fetchAlbums(): Mono<List<AlbumsResponse>> = fetch("/albums")
            .bodyToFlux<Album>()
            .flatMap { album -> fetchPhotos(album.id)
                    .map { photo ->
                        PhotoStripped(title = photo.title, url = photo.url,
                                thumbnailUrl = photo.thumbnailUrl)
                    }
                    .collectList()
                    .zipWith(album.toMono())
            }
            .map { result ->
                AlbumsResponse(
                        albumId = result.t2.id,
                        userId = result.t2.userId,
                        title = result.t2.title,
                        photos = result.t1)
            }
            .collectList()
}