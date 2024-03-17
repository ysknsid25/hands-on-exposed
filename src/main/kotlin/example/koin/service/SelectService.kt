package example.koin.service

import example.koin.accessor.SelectDataAccessor
import example.koin.data.model.Employee
import example.koin.data.model.Expense
import example.koin.data.model.Partner
import org.jetbrains.exposed.sql.Count
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.count
import org.jetbrains.exposed.sql.selectAll

class SelectService(
    private val selectDataAccessor: SelectDataAccessor
): TransactionService() {
    fun getInorin(): String{
        val message = executeQuery {
            val resultRow = selectDataAccessor.selectEmployeeById(1)
            "社員の名前は「${resultRow[Employee.lastName]} ${resultRow[Employee.firstName]}」です！"
        }
        return message
    }

    fun getAllEmployees(): String {
        val employeeNames = executeQuery {
            val resultRows = selectDataAccessor.selectEmployeeAll()
            resultRows.joinToString {
                "${it[Employee.lastName]} ${it[Employee.firstName]}"
            }
        }
        return employeeNames
    }

    fun getAllPartners(): String {
        val employeeNames = executeQuery {
            val resultRows = selectDataAccessor.selectPartnerAll()
            resultRows.joinToString {
                "${it[Partner.lastName]} ${it[Partner.firstName]}"
            }
        }
        return employeeNames
    }

    fun getAllEmployeesNames(): String {
        val employeeNames = executeQuery {
            val resultRows = selectDataAccessor.selectEmployeeAllNames()
            resultRows.joinToString {
                "${it[Employee.lastName]} ${it[Employee.firstName]}"
            }
        }
        return employeeNames
    }

    fun getAllPartnersNames(): String {
        val partnerNames = executeQuery {
            val resultRows = selectDataAccessor.selectPartnerAllNames()
            resultRows.joinToString {
                "${it[Partner.lastName]} ${it[Partner.firstName]}"
            }
        }
        return partnerNames
    }

    fun selectPartnerById(id: Int): String {
        return executeQuery {
            val resultRow = selectDataAccessor.selectPartnerById(id)
            if(resultRow == null){
                "該当するパートナーが見つかりません。"
            }else{
                "パートナーの名前は「${resultRow[Partner.lastName]} ${resultRow[Partner.firstName]}」です！"
            }
        }
    }

    fun getAllPartnersNamesByLikeKeyword(keyword: String): String {
        val partnerNames = executeQuery {
            val resultRows = selectDataAccessor.selectPartnerByLikeKeyword(keyword)
            if(resultRows.isEmpty()){
                "該当するパートナーが見つかりません。"
            }else{
                resultRows.joinToString {
                    "${it[Partner.lastName]} ${it[Partner.firstName]}"
                }
            }
        }
        return partnerNames
    }

    fun getEmployeeNameOfGeneralOrAccounting(): String {
        val partnerNames = executeQuery {
            val resultRows = selectDataAccessor.selectEmployeeNameOfGeneralOrAccounting()
            resultRows.joinToString {
                "${it[Employee.lastName]} ${it[Employee.firstName]}"
            }
        }
        return partnerNames
    }

    fun getEmployeeBySorted(): String {
        val partnerNames = executeQuery {
            val resultRows = selectDataAccessor.selectEmployeeBySorted()
            resultRows.joinToString {
                "${it[Employee.lastName]} ${it[Employee.firstName]}"
            }
        }
        return partnerNames
    }

    fun getPartnerBySorted(): String {
        val partnerNames = executeQuery {
            val resultRows = selectDataAccessor.selectPartnerBySorted()
            resultRows.joinToString {
                "${it[Partner.lastName]} ${it[Partner.firstName]}"
            }
        }
        return partnerNames
    }

    fun getHowManyApplyExpenseByEmployee():String{
        val howManyApplyExpenseByEmployee = executeQuery {
            val resultRows = selectDataAccessor.selectHowManyApplyExpenseByEmployee()
            resultRows.joinToString {
                "${it[Expense.employeeId]}: ${it[Expense.employeeId.count()]}"
            }
        }
        return howManyApplyExpenseByEmployee
    }
}