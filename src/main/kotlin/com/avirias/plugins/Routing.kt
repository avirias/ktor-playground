package com.avirias.plugins

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.routes() {
    routing {
        userRoute()
        adminRoute()
    }
}

fun Route.userRoute() {
    route("/user") {
        get("/all") {
            call.respond("this is all user")
        }
        post {
            // TODO: 09/08/21 Will update
            call.respond("created")
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
            call.respond("created admin")
        }

    }
}

