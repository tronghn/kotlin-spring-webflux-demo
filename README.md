# kotlin-spring-webflux-demo

Basic Kotlin application using [reactive streams](http://www.reactive-streams.org/) to expose a simple, non-blocking 
REST API using data from [JSONPlaceholder](https://jsonplaceholder.typicode.com/) in the background as a dummy repository.

## Technologies & tools
- [Kotlin](https://kotlinlang.org/)
- [Spring Boot 2](https://github.com/spring-projects/spring-boot)
- [Spring WebFlux](https://docs.spring.io/spring/docs/5.0.0.BUILD-SNAPSHOT/spring-framework-reference/html/web-reactive.html) 
(which uses [Reactor](https://projectreactor.io/) for the reactive support)
- [Gradle](https://gradle.org/)

## Build
`./gradlew build`

This also runs the [Kotlinter](https://github.com/jeremymailen/kotlinter-gradle) Gradle plugin, which uses 
[ktlint](https://github.com/shyiko/ktlint), a Kotlin code style checker. 

Add the `--stacktrace` option to debug in case of linting failures.

## Run
`./gradlew bootRun`

The application should be available at [http://localhost:8080/]()

## API endpoints
- http://localhost:8080/api/posts - get all posts with accompanying comments
- http://localhost:8080/api/albums - get all albums with accompanying photos

Streaming JSON - same as above, but instead returns an item every second as a stream rather than a single response.
- http://localhost:8080/api/posts/stream
- http://localhost:8080/api/albums/stream