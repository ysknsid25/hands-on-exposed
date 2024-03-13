package example.koin.accessor

import example.koin.data.model.Employee
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select

class SelectDataAccessor {
    fun selectEmployeeById(id: Int): ResultRow {
        return Employee.slice(
            Employee.firstName,
            Employee.lastName,
        ).select(
            (Employee.employeeId eq id)
        ).first()
    }
}