package com.avirias

import com.avirias.plugins.routes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.LoggerFactory

fun main() {
    embeddedServer(Netty, environment = applicationEngineEnvironment {
        log = LoggerFactory.getLogger("ktor.application")
        developmentMode = true

        module {
            install(CORS)
            install(CallLogging)
            routes()
        }
        connector {
            port = 8080
        }
    }).start(wait = true)
}
