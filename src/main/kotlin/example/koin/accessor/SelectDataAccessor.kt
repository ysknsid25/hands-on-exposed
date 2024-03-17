package example.koin.accessor

import example.koin.data.model.Employee
import example.koin.data.model.Partner
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.SqlExpressionBuilder.neq
import org.jetbrains.exposed.sql.compoundOr
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class SelectDataAccessor {
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
}