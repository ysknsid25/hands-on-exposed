package example.koin.accessor

import example.koin.data.model.Department
import example.koin.data.model.Employee
import example.koin.data.model.Expense
import example.koin.data.model.Partner
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.between
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import org.jetbrains.exposed.sql.SqlExpressionBuilder.lessEq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.SqlExpressionBuilder.neq

class SelectDataAccessor {

    private val innerJoinExpenseEmployee = Employee.join(Expense, JoinType.INNER, Employee.employeeId, Expense.employeeId)

    fun selectEmployeeById(id: Int): ResultRow {
        return Employee.slice(
            Employee.firstName,
            Employee.lastName,
        ).select(
            (Employee.employeeId eq id)
        ).first()
    }

    fun selectEmployeeAll(): List<ResultRow> {
        return Employee.selectAll().toList()
    }

    fun selectPartnerAll(): List<ResultRow> {
        return Partner.selectAll().toList()
    }

    fun selectEmployeeAllNames(): List<ResultRow> {
        return Employee.slice(
            Employee.firstName,
            Employee.lastName,
        ).selectAll().toList()
    }

    fun selectPartnerAllNames(): List<ResultRow> {
        return Partner.slice(
            Partner.firstName,
            Partner.lastName,
        ).selectAll().toList()
    }

    fun selectPartnerById(id: Int): ResultRow? {
        return Partner.slice(
            Partner.firstName,
            Partner.lastName,
        ).select(
            (Partner.partnerId eq id)
        ).firstOrNull()
    }

    fun selectPartnerByLikeKeyword(keyword: String): List<ResultRow> {
        return Partner.slice(
            Partner.firstName,
            Partner.lastName,
        ).select(
            (Partner.lastName like "%${keyword}%")
        ).toList()
    }

    fun selectEmployeeNameOfGeneralOrAccounting(): List<ResultRow> {
        return Employee.slice(
            Employee.firstName,
            Employee.lastName,
        ).select(
            Employee.departmentId inList listOf(1, 2)
        ).toList()
//        return Employee.slice(
//            Employee.firstName,
//            Employee.lastName,
//        ).select(
//            Employee.departmentId neq 3
//        ).toList()
//        val whereCondition = listOf(
//            Employee.departmentId eq 1,
//            Employee.departmentId eq 2
//        ).compoundOr()
//        return Employee.slice(
//            Employee.firstName,
//            Employee.lastName,
//        ).select(
//            whereCondition
//        ).toList()
    }

    fun selectEmployeeBySorted(): List<ResultRow> {
        return Employee.slice(
            Employee.firstName,
            Employee.lastName,
        ).selectAll().orderBy(
            Pair(Employee.departmentId, SortOrder.DESC),
            Pair(Employee.lastName, SortOrder.ASC),
        ).toList()
    }

    fun selectPartnerBySorted(): List<ResultRow> {
        return Partner.slice(
            Partner.firstName,
            Partner.lastName,
        ).selectAll().orderBy(
            Pair(Partner.departmentId, SortOrder.DESC),
            Pair(Partner.lastName, SortOrder.ASC),
        ).toList()
    }

    fun selectHowManyApplyExpenseByEmployee(): List<ResultRow>{
        return Expense.slice(
            Expense.employeeId,
            Expense.employeeId.count(),
        ).selectAll().groupBy(Expense.employeeId).toList()
    }

    fun selectHowMuchExpenseByEmployee(): List<ResultRow>{
        return Expense.slice(
            Expense.employeeId,
            Expense.expense.sum(),
        ).selectAll().groupBy(Expense.employeeId).toList()
    }

    fun selectEmployeeLimitOffset(): List<ResultRow> {
        return Employee.slice(
            Employee.firstName,
            Employee.lastName,
        ).selectAll().limit(3, offset = 1).toList()
    }

    fun selectPartnerLimitOffset(): List<ResultRow> {
        return Partner.slice(
            Partner.firstName,
            Partner.lastName,
        ).selectAll().limit(3, offset = 1).toList()
    }

    fun joinEmployeeDepartmentAll(): List<ResultRow> {
        return Employee.join(Department, JoinType.INNER, Employee.departmentId, Department.departmentId)
            .slice(
                Department.departmentName,
                Employee.firstName,
                Employee.lastName,
            )
            .selectAll()
            .toList()
    }

    fun joinPartnerDepartmentAll(): List<ResultRow> {
        return Partner.join(Department, JoinType.INNER, Partner.departmentId, Department.departmentId)
            .slice(
                Department.departmentName,
                Partner.firstName,
                Partner.lastName,
            )
            .selectAll()
            .toList()
    }

    fun selectHasExpenseEmployeeNames(): List<ResultRow>{
        return innerJoinExpenseEmployee.slice(
            Employee.firstName,
            Employee.lastName
        ).selectAll().withDistinct().toList()
    }

    fun selectHasExpenseEmployeeNamesWithBetween(): List<ResultRow>{
        //別解
//        return innerJoinExpenseEmployee.slice(
//            Employee.firstName,
//            Employee.lastName
//        ).select(
//            listOf(
//                Expense.expense greaterEq 1000,
//                Expense.expense lessEq 2000,
//            ).compoundAnd()
//        ).withDistinct().toList()
        return innerJoinExpenseEmployee.slice(
            Employee.firstName,
            Employee.lastName
        ).select(
            Expense.expense.between(1000, 2000)
        ).withDistinct().toList()
    }
}