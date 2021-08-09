package com.avirias.plugins

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import kotlinx.serialization.Serializable
import kotlin.math.log
import kotlin.random.Random

fun Application.routes() {
    routing {
        userRoute()
        adminRoute()
    }
}

@OptIn(KtorExperimentalLocationsAPI::class)
fun Route.userRoute() {
    route("/user") {
        get<Listing> { listing ->
            logDebug("Page is ${listing.page} and count is ${listing.count}")
            call.respond("Ram,Shyam,Mohan".split(",").map {
                User(it, Random.nextInt())
            }.toList())
        }
        post {
            val user = call.receive<User>()
            logDebug("user created with detail: $user")
            call.response.status(HttpStatusCode.Accepted)
        }
        get("/{id}") {
            call.respond("user with id is ${call.parameters["id"]}")
        }
    }
}


fun Route.adminRoute() {
    route("/admin") {
        get("/secret") {
            call.respond("this is secret message")
        }
        post {

            logDebug("logger attached")
            call.respond("created admin")
        }

    }
}


fun PipelineContext<Unit, ApplicationCall>.logDebug(message: String) {
    call.application.environment.log.debug(message)
}


@Serializable
data class User(
    val name: String,
    val age: Int
)

@OptIn(KtorExperimentalLocationsAPI::class)
@Location("/user")
data class Listing(val page: Int = 0, val count: Int = 20)
