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
        get("/allPartners"){
            exposedController.getAllPartners(call)
        }
        get("/allEmployeesNames"){
            exposedController.getAllEmployeesNames(call)
        }
        get("/allPartnersNames"){
            exposedController.getAllPartnersNames(call)
        }
        get("/partnerNameById"){
            exposedController.getPartnerNameById(call)
        }
        get("/partnerNameByLikeKeyword"){
            exposedController.getAllPartnersNamesByLikeKeyword(call)
        }
        get("/employeeNameOfGeneralOrAccounting"){
            exposedController.getEmployeeNameOfGeneralOrAccounting(call)
        }
        get("/employeeBySorted"){
            exposedController.getEmployeeBySorted(call)
        }
        get("/partnerBySorted"){
            exposedController.getPartnerBySorted(call)
        }
        get("/howManyApplyExpenseByEmployee"){
            exposedController.getHowManyApplyExpenseByEmployee(call)
        }
        get("/howMuchExpenseByEmployee"){
            exposedController.getHowMuchExpenseByEmployee(call)
        }
        get("/employeeLimitOffset"){
            exposedController.getEmployeeLimitOffset(call)
        }
        get("/partnerLimitOffset"){
            exposedController.getPartnerLimitOffset(call)
        }
        get("/employeeNameAndDepartment"){
            exposedController.getEmployeeNameAndDepartment(call)
        }
    }
}
