package com.jrapmund.imad

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*
import kotlin.random.Random

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

var active = 10

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    Functions.initializeCompanyDSL()

    routing {
        get("/stores") {
            println("The /stores get service was invoked.")

            var storeNames = mutableListOf<Pair<Int, String>>()

            for(store in Company.stores)
                storeNames.add(Pair(store.id, store.name))

            val jsonStores = Json.encodeToString(storeNames)
            call.respondText (
                jsonStores,
                status= HttpStatusCode.OK,
                contentType=ContentType.Application.Json
            )

        }
        post("/stores"){
            println("The /stores post service was invoked.")
        }
        get("/stores/departments/{storeID}") {
            println("The /stores/departments get service was invoked.")
            println("id requested : ${call.parameters["storeID"]}")

            val sid = call.parameters["storeID"]?.toInt()?:-1

            val store = Company.find {
                it.id == sid
            }[0]

            val departmentDetails = mutableListOf<Pair<String, Int>>()

            for(department in store.departments) {
                departmentDetails.add(Pair(department.name, department.aisle))
            }

            val responseData = Json.encodeToString(departmentDetails)

            call.respondText (
                responseData,
                status= HttpStatusCode.OK,
                contentType=ContentType.Text.Plain
            )
        }
        post("/stores/departments"){
            println("The /stores/departments post service was invoked.")
        }

        get("/newnumber") {
            println("The /newnumber service was invoked.")
            active = Random.nextInt(100)
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

