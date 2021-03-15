package com.jrapmund.imad

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

var active = 10

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    routing {
        get("/newnumber") {
            println("The /newnumber service was invoked.")
            call.respondText (
                active.toString(),
                status= HttpStatusCode.OK,
                contentType=ContentType.Text.Plain
            )
        }
        post("/numbercompare") {
            println("The /numbercompare service was invoked.")

            val params = call.receiveText()
            val submitted = Json.decodeFromString<String>(params)

            println("The value submitted is [$submitted].")

            val compareResponse = if (Functions.compareFunction(active, submitted) {x, y -> x == y}) "Your entry is correct!" else "You've entered an incorrect value."

            call.respondText (
                compareResponse,
                status= HttpStatusCode.OK,
                contentType=ContentType.Text.Plain
            )
        }
    }

}

