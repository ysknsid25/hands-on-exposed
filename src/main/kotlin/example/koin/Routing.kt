package example.koin

import example.koin.controller.ExposedController
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val exposedController by inject<ExposedController>()
    routing {
        get("/") {
            call.respondText("Hello! hands on exposed!!")
        }
        get("/inorin"){
            exposedController.getInorin(call)
        }
        get("/allEmployees"){
            exposedController.getAllEmployees(call)
        }
    }
}
