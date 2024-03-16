package example.koin.service

import example.koin.accessor.SelectDataAccessor
import example.koin.data.model.Employee
import example.koin.data.model.Partner

class SelectService(
    private val selectDataAccessor: SelectDataAccessor
): TransactionService() {
    fun getInorin(): String{
        val resultRow = executeQuery {
            selectDataAccessor.selectEmployeeById(1)
        }
        return "社員の名前は「${resultRow[Employee.lastName]} ${resultRow[Employee.firstName]}」です！"
    }

    fun getAllEmployees(): String {
        val resultRows = executeQuery {
            selectDataAccessor.selectEmployeeAll()
        }
        val employeeNames = resultRows.joinToString {
            "${it[Employee.lastName]} ${it[Employee.firstName]}"
        }
        return employeeNames
    }

    fun getAllPartners(): String {
        val resultRows = executeQuery {
            selectDataAccessor.selectPartnerAll()
        }
        val employeeNames = resultRows.joinToString {
            "${it[Partner.lastName]} ${it[Partner.firstName]}"
        }
        return employeeNames
    }

    fun getAllEmployeesNames() : String {
        val resultRows = executeQuery {
            selectDataAccessor.selectEmployeeAllNames()
        }
        val employeeNames = resultRows.joinToString {
            "${it[Employee.lastName]} ${it[Employee.firstName]}"
        }
        return employeeNames
    }
}