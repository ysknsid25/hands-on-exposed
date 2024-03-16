package example.koin.controller

import example.koin.service.SelectService
import io.ktor.server.application.*
import io.ktor.server.response.*

class ExposedController(
    private val selectService: SelectService,
) {
    suspend fun getInorin(call: ApplicationCall) {
        val message = selectService.getInorin()
        call.respondText(message)
    }

    suspend fun getAllEmployees(call: ApplicationCall) {
        val message = selectService.getAllEmployees()
        call.respondText(message)
    }
}