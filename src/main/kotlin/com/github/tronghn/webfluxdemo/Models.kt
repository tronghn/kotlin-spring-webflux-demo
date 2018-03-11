package com.github.tronghn.webfluxdemo

data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)

data class Album(
    val userId: Int,
    val id: Int,
    val title: String
)

data class Comment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)

data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

data class CommentStripped(
    val name: String,
    val email: String,
    val body: String
)

data class PhotoStripped(
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

data class PostsResponse(
    val postId: Int,
    val userId: Int,
    val title: String,
    val comments: List<CommentStripped>
)

data class AlbumsResponse(
    val albumId: Int,
    val userId: Int,
    val title: String,
    val photos: List<PhotoStripped>
)