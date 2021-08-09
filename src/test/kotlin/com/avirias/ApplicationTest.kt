package com.avirias

import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import com.avirias.plugins.*

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ routes() }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello World!", response.content)
            }
        }
    }
}