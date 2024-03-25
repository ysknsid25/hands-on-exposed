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

    suspend fun getEmployeeBySorted(call: ApplicationCall){
        val message = selectService.getEmployeeBySorted()
        call.respondText(message)
    }

    suspend fun getPartnerBySorted(call: ApplicationCall){
        val message = selectService.getPartnerBySorted()
        call.respondText(message)
    }

    suspend fun getHowManyApplyExpenseByEmployee(call: ApplicationCall){
        val message = selectService.getHowManyApplyExpenseByEmployee()
        call.respondText(message)
    }

    suspend fun getHowMuchExpenseByEmployee(call: ApplicationCall){
        val message = selectService.getHowMuchExpenseByEmployee()
        call.respondText(message)
    }

    suspend fun getEmployeeLimitOffset(call: ApplicationCall){
        val message = selectService.getEmployeeLimitOffset()
        call.respondText(message)
    }

    suspend fun getPartnerLimitOffset(call: ApplicationCall){
        val message = selectService.getPartnerLimitOffset()
        call.respondText(message)
    }

    suspend fun getEmployeeNameAndDepartment(call: ApplicationCall){
        val message = selectService.getEmployeeNameAndDepartment()
        call.respondText(message)
    }

    suspend fun getPartnerNameAndDepartment(call: ApplicationCall){
        val message = selectService.getPartnerNameAndDepartment()
        call.respondText(message)
    }

    suspend fun hasExpenseEmployeeNames(call: ApplicationCall){
        val message = selectService.hasExpenseEmployeeNames()
        call.respondText(message)
    }

    suspend fun hasExpenseEmployeeNamesWithBetween(call: ApplicationCall){
        val message = selectService.hasExpenseEmployeeNamesWithBetween()
        call.respondText(message)
    }

    suspend fun getAllEmployeeTypeAndNames(call: ApplicationCall){
        val message = selectService.getAllEmployeeTypeAndNames()
        call.respondText(message)
    }

    suspend fun getAllEmployeeTypeAndNamesDistinct(call: ApplicationCall){
        val message = selectService.getAllEmployeeTypeAndNamesDistinct()
        call.respondText(message)
    }

    suspend fun getHasExpenseEmployeeIdAndNames(call: ApplicationCall){
        val message = selectService.getHasExpenseEmployeeIdAndNames()
        call.respondText(message)
    }

    suspend fun getOverExpenseEmployeeIdAndNames(call: ApplicationCall){
        val message = selectService.getOverExpenseEmployeeIdAndNames()
        call.respondText(message)
    }

    suspend fun getExistsOverExpenseEmployeeIdAndNames(call: ApplicationCall){
        val message = selectService.getExistsOverExpenseEmployeeIdAndNames()
        call.respondText(message)
    }

    suspend fun getLatestEmployeeIdByDepartmentId(call: ApplicationCall){
        val message = selectService.getLatestEmployeeIdByDepartmentId()
        call.respondText(message)
    }

    suspend fun getEmployeeNamesAndEnrollmentStatus(call: ApplicationCall){
        val message = selectService.getEmployeeNamesAndEnrollmentStatus()
        call.respondText(message)
    }

    suspend fun getPartnerNamesAndEnrollmentStatus(call: ApplicationCall){
        val message = selectService.getPartnerNamesAndEnrollmentStatus()
        call.respondText(message)
    }

    suspend fun getConcatEmployeeNames(call: ApplicationCall){
        val message = selectService.getConcatEmployeeNames()
        call.respondText(message)
    }

    suspend fun getConcatPartnerNames(call: ApplicationCall){
        val message = selectService.getConcatPartnerNames()
        call.respondText(message)
    }

    suspend fun getEmployeeFirstNameStrByte(call: ApplicationCall){
        val message = selectService.getEmployeeFirstNameStrByte()
        call.respondText(message)
    }

    suspend fun getEmployeeFirstNameCharLength(call: ApplicationCall){
        val message = selectService.getEmployeeFirstNameCharLength()
        call.respondText(message)
    }
}