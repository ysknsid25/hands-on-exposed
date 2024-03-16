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

    suspend fun getAllPartners(call: ApplicationCall) {
        val message = selectService.getAllPartners()
        call.respondText(message)
    }

    suspend fun getAllEmployeesNames(call: ApplicationCall) {
        val message = selectService.getAllEmployeesNames()
        call.respondText(message)
    }

    suspend fun getAllPartnersNames(call: ApplicationCall) {
        val message = selectService.getAllPartnersNames()
        call.respondText(message)
    }
}