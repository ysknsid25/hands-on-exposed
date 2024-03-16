package example.koin.accessor

import example.koin.data.model.Employee
import example.koin.data.model.Partner
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
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
}