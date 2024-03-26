package example.koin.accessor

import example.koin.data.model.Employee
import example.koin.data.model.Partner
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class DeleteDataAccessor {
    fun deleteEmployee(targetId: Int): Int{
        return Employee.deleteWhere { employeeId eq targetId }
    }

    fun deletePartner(targetId: Int): Int{
        return Partner.deleteWhere { partnerId eq targetId }
    }
}