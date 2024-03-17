package example.koin.service

import example.koin.accessor.SelectDataAccessor
import example.koin.data.model.Employee
import example.koin.data.model.Expense
import example.koin.data.model.Partner
import org.jetbrains.exposed.sql.count

class SelectService(
    private val selectDataAccessor: SelectDataAccessor
): TransactionService() {
    fun getInorin(): String {
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

    fun getAllEmployeesNames(): String {
        val resultRows = executeQuery {
            selectDataAccessor.selectEmployeeAllNames()
        }
        val employeeNames = resultRows.joinToString {
            "${it[Employee.lastName]} ${it[Employee.firstName]}"
        }
        return employeeNames
    }

    fun getAllPartnersNames(): String {
        val resultRows = executeQuery {
            selectDataAccessor.selectPartnerAllNames()
        }
        val partnerNames = resultRows.joinToString {
            "${it[Partner.lastName]} ${it[Partner.firstName]}"
        }
        return partnerNames
    }

    fun selectPartnerById(id: Int): String {
        val resultRow = executeQuery {
            selectDataAccessor.selectPartnerById(id)
        } ?: return "該当するパートナーが見つかりません。"
        return "パートナーの名前は「${resultRow[Partner.lastName]} ${resultRow[Partner.firstName]}」です！"
    }

    fun getAllPartnersNamesByLikeKeyword(keyword: String): String {
        val resultRows = executeQuery {
            selectDataAccessor.selectPartnerByLikeKeyword(keyword)
        }
        if(resultRows.isEmpty()){
            return "該当するパートナーが見つかりません。"
        }
        val partnerNames = resultRows.joinToString {
            "${it[Partner.lastName]} ${it[Partner.firstName]}"
        }
        return partnerNames
    }

    fun getEmployeeNameOfGeneralOrAccounting(): String {
        val resultRows = executeQuery {
            selectDataAccessor.selectEmployeeNameOfGeneralOrAccounting()
        }
        val employeeNames = resultRows.joinToString {
            "${it[Employee.lastName]} ${it[Employee.firstName]}"
        }
        return employeeNames
    }

    fun getEmployeeBySorted(): String {
        val resultRows = executeQuery {
            selectDataAccessor.selectEmployeeBySorted()
        }
        val employeeNames = resultRows.joinToString {
            "${it[Employee.lastName]} ${it[Employee.firstName]}"
        }
        return employeeNames
    }

    fun getPartnerBySorted(): String {
        val resultRows = executeQuery {
            selectDataAccessor.selectPartnerBySorted()
        }
        val partnerNames = resultRows.joinToString {
            "${it[Partner.lastName]} ${it[Partner.firstName]}"
        }
        return partnerNames
    }

    fun getHowManyApplyExpenseByEmployee(): String {
        val resultRows = executeQuery {
            selectDataAccessor.selectHowManyApplyExpenseByEmployee()
        }
        val howManyApply = resultRows.joinToString {
            "${it[Expense.employeeId]}: ${it[Expense.employeeId.count()]}"
        }
        return howManyApply
    }
}