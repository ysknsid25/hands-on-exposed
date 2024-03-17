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

    suspend fun getPartnerNameById(call: ApplicationCall){
        val id = call.parameters["partnerId"]?.toIntOrNull() ?: -1
        val message = selectService.selectPartnerById(id)
        call.respondText(message)
    }

    suspend fun getAllPartnersNamesByLikeKeyword(call: ApplicationCall){
        val keyword = call.parameters["lastName"] ?: ""
        val message = selectService.getAllPartnersNamesByLikeKeyword(keyword)
        call.respondText(message)
    }

    suspend fun getEmployeeNameOfGeneralOrAccounting(call: ApplicationCall){
        val message = selectService.getEmployeeNameOfGeneralOrAccounting()
        call.respondText(message)
    }
}