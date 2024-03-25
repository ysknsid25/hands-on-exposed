package example.koin.service

import example.koin.accessor.SelectDataAccessor
import example.koin.data.model.Department
import example.koin.data.model.Employee
import example.koin.data.model.Expense
import example.koin.data.model.Partner
import org.jetbrains.exposed.sql.*

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

    fun getHowManyApplyExpenseByEmployee(): String{
        val howManyApplyExpenseByEmployee = executeQuery {
            val resultRows = selectDataAccessor.selectHowManyApplyExpenseByEmployee()
            resultRows.joinToString {
                "${it[Expense.employeeId]}: ${it[Expense.employeeId.count()]}"
            }
        }
        return howManyApplyExpenseByEmployee
    }

    fun getHowMuchExpenseByEmployee(): String{
        val howMuchExpenseByEmployee = executeQuery {
            val resultRows = selectDataAccessor.selectHowMuchExpenseByEmployee()
            resultRows.joinToString {
                "${it[Expense.employeeId]}: ${it[Expense.expense.sum()]}"
            }
        }
        return howMuchExpenseByEmployee
    }

    fun getEmployeeLimitOffset(): String{
        val employeeNames = executeQuery {
            val resultRows = selectDataAccessor.selectEmployeeLimitOffset()
            resultRows.joinToString {
                "${it[Employee.lastName]} ${it[Employee.firstName]}"
            }
        }
        return employeeNames
    }

    fun getPartnerLimitOffset(): String{
        val partnerNames = executeQuery {
            val resultRows = selectDataAccessor.selectPartnerLimitOffset()
            resultRows.joinToString {
                "${it[Partner.lastName]} ${it[Partner.firstName]}"
            }
        }
        return partnerNames
    }

    fun getEmployeeNameAndDepartment(): String {
        val employeeNamesAndDepartment = executeQuery {
            val resultRows = selectDataAccessor.joinEmployeeDepartmentAll()
            resultRows.joinToString {
                "${it[Employee.lastName]} ${it[Employee.firstName]}: ${it[Department.departmentName]}"
            }
        }
        return employeeNamesAndDepartment
    }

    fun getPartnerNameAndDepartment(): String {
        val employeeNamesAndDepartment = executeQuery {
            val resultRows = selectDataAccessor.joinPartnerDepartmentAll()
            resultRows.joinToString {
                "${it[Partner.lastName]} ${it[Partner.firstName]}: ${it[Department.departmentName]}"
            }
        }
        return employeeNamesAndDepartment
    }

    fun hasExpenseEmployeeNames(): String{
        val employeeNames = executeQuery {
            val resultRows = selectDataAccessor.selectHasExpenseEmployeeNames()
            resultRows.joinToString {
                "${it[Employee.lastName]} ${it[Employee.firstName]}"
            }
        }
        return employeeNames
    }

    fun hasExpenseEmployeeNamesWithBetween(): String{
        val employeeNames = executeQuery {
            val resultRows = selectDataAccessor.selectHasExpenseEmployeeNamesWithBetween()
            resultRows.joinToString {
                "${it[Employee.lastName]} ${it[Employee.firstName]}"
            }
        }
        return employeeNames
    }

    fun getAllEmployeeTypeAndNames(): String{
        val employeeTypeAndNames = executeQuery {
            val resultRows = selectDataAccessor.unionAllEmployeeAndPartner()
            resultRows.joinToString {
                val employeeTypeName = EmployeeType.of(it[SelectDataAccessor.EMPLOYEE_TYPE])?.typeName ?: "不明"
                "${employeeTypeName}: ${it[Employee.lastName]} ${it[Employee.firstName]}"
            }
        }
        return employeeTypeAndNames
    }

    fun getAllEmployeeTypeAndNamesDistinct(): String{
        val employeeTypeAndNames = executeQuery {
            val resultRows = selectDataAccessor.unionEmployeeAndPartner()
            resultRows.joinToString {
                val employeeTypeName = EmployeeType.of(it[SelectDataAccessor.EMPLOYEE_TYPE])?.typeName ?: "不明"
                "${employeeTypeName}: ${it[Employee.lastName]} ${it[Employee.firstName]}"
            }
        }
        return employeeTypeAndNames
    }

    fun getHasExpenseEmployeeIdAndNames(): String{
        val employeeIdAndNames = executeQuery {
            val resultRows = selectDataAccessor.hasExpenseEmployeeIdAndNames()
            resultRows.joinToString {
                "${it[Employee.employeeId]}: ${it[Employee.lastName]} ${it[Employee.firstName]}"
            }
        }
        return employeeIdAndNames
    }

    fun getOverExpenseEmployeeIdAndNames(): String{
        val employeeIdAndNames = executeQuery {
            val resultRows = selectDataAccessor.overExpenseEmployeeIdAndNames()
            resultRows.joinToString {
                "${it[Employee.employeeId]}: ${it[Employee.lastName]} ${it[Employee.firstName]}"
            }
        }
        return employeeIdAndNames
    }

    fun getExistsOverExpenseEmployeeIdAndNames(): String{
        val employeeIdAndNames = executeQuery {
            val resultRows = selectDataAccessor.existsOverExpenseEmployeeIdAndNames()
            resultRows.joinToString {
                "${it[Employee.employeeId]}: ${it[Employee.lastName]} ${it[Employee.firstName]}"
            }
        }
        return employeeIdAndNames
    }

    fun getLatestEmployeeIdByDepartmentId(): String{
        val employeeIdAndNames = executeQuery {
            val resultRows = selectDataAccessor.latestEmployeeIdByDepartmentId()
            resultRows.joinToString {
                "${it[Department.departmentName]}: ${it[SelectDataAccessor.latestEmployeeIdByDepartmentId[SelectDataAccessor.EMPLOYTT_ID_MAX]]}"
            }
        }
        return employeeIdAndNames
    }

    fun getEmployeeNamesAndEnrollmentStatus(): String{
        val employeeNamesAndEnrollmentStatus = executeQuery {
            val resultRows = selectDataAccessor.selectEmployeeNamesAndEnrollmentStatus()
            resultRows.joinToString {
                "${it[Employee.lastName]} ${it[Employee.firstName]}: ${it[SelectDataAccessor.caseEnrollmentStatus]}"
            }
        }
        return employeeNamesAndEnrollmentStatus
    }

    fun getPartnerNamesAndEnrollmentStatus(): String{
        val partnerNamesAndEnrollmentStatus = executeQuery {
            val resultRows = selectDataAccessor.selectPartnerNamesAndEnrollmentStatus()
            resultRows.joinToString {
                "${it[Partner.lastName]} ${it[Partner.firstName]}: ${it[SelectDataAccessor.casePartnerEnrollmentStatus]}"
            }
        }
        return partnerNamesAndEnrollmentStatus
    }

    fun getConcatEmployeeNames(): String{
        val concatEmployeeNames = executeQuery {
            val resultRows = selectDataAccessor.selectConcatEmployeeNames()
            resultRows.joinToString {
                it[SelectDataAccessor.employeeNameConcat]
            }
        }
        return concatEmployeeNames
    }

    fun getConcatPartnerNames(): String{
        val concatPartnerNames = executeQuery {
            val resultRows = selectDataAccessor.selectConcatPartnerNames()
            resultRows.joinToString {
                it[SelectDataAccessor.partnerNameConcat]
            }
        }
        return concatPartnerNames
    }

    enum class EmployeeType(private val value: Short, val typeName: String) {
        EMPLOYEE(1, "社員"),
        PARTNER(2, "パートナー");

        companion object {
            fun of(value: Short): EmployeeType? {
                return EmployeeType.values().firstOrNull{
                    value == it.value
                }
            }
        }
    }

}