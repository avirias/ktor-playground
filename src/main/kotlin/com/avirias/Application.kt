package com.avirias

import com.avirias.plugins.routes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory

fun main() {
    embeddedServer(Netty, environment = applicationEngineEnvironment {
        log = LoggerFactory.getLogger("ktor.application")
        developmentMode = true

        module {
            install(CORS)
            install(CallLogging)
            install(Locations)
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                })
            }
            install(StatusPages) {
                exception<SerializationException> { cause ->
                    cause.printStackTrace()
                    call.respond(HttpStatusCode.BadRequest, "Please provide all fields")
                }
            }
            routes()
        }
        connector {
            port = 8080
        }
    }).start(wait = true)
}
