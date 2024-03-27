package example.koin.accessor

import example.koin.data.model.Employee
import example.koin.data.model.Expense
import example.koin.data.model.Partner
import org.jetbrains.exposed.sql.*
import java.time.LocalDateTime

class UpdateDataAccessor {
    fun updateEmployee(
        targetId: Int,
        departmentId: Int,
        enrollmentStatus: Int,
        firstName: String,
        lastName: String,
    ): Int{
        return Employee.update({Employee.employeeId eq targetId}){
                it[Employee.departmentId] = departmentId
                it[Employee.enrollmentStatus] = enrollmentStatus
                it[Employee.firstName] = firstName
                it[Employee.lastName] = lastName
                it[createdAt] = LocalDateTime.now()
                it[updatedAt] = LocalDateTime.now()
        }
    }

    fun updatePartner(
        targetId: Int,
        departmentId: Int,
        enrollmentStatus: Int,
        firstName: String,
        lastName: String,
    ): Int{
        return Partner.update({Partner.partnerId eq targetId}){
            it[Partner.departmentId] = departmentId
            it[Partner.enrollmentStatus] = enrollmentStatus
            it[Partner.firstName] = firstName
            it[Partner.lastName] = lastName
            it[createdAt] = LocalDateTime.now()
            it[updatedAt] = LocalDateTime.now()
        }
    }

    fun updateApplyExpenseEmployee(): Int{
        val existsExpense = exists(Expense.slice(intLiteral(1)).select { Expense.employeeId eq Employee.employeeId})
        return Employee.update({ existsExpense }){
            it[Employee.firstName] = Concat(" ", Employee.firstName, stringLiteral("(申請中)"))
            it[Employee.createdAt] = LocalDateTime.now()
            it[Employee.updatedAt] = LocalDateTime.now()
        }
    }
}