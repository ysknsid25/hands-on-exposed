package example.koin.accessor

import example.koin.data.model.Employee
import example.koin.data.model.Partner
import org.jetbrains.exposed.sql.insert
import java.time.LocalDateTime

class InsertDataAccessor {
    fun insertEmployee(
        id: Int,
        departmentId: Int,
        enrollmentStatus: Int,
        firstName: String,
        lastName: String,
    ): Int{
        return Employee.insert {
            it[employeeId] = id
            it[Employee.departmentId] = departmentId
            it[Employee.enrollmentStatus] = enrollmentStatus
            it[Employee.firstName] = firstName
            it[Employee.lastName] = lastName
            it[createdAt] = LocalDateTime.now()
            it[updatedAt] = LocalDateTime.now()
        }.insertedCount
    }

    fun insertPartner(
        id: Int,
        departmentId: Int,
        enrollmentStatus: Int,
        firstName: String,
        lastName: String,
    ): Int{
        return Partner.insert {
            it[Partner.partnerId] = id
            it[Partner.departmentId] = departmentId
            it[Partner.enrollmentStatus] = enrollmentStatus
            it[Partner.firstName] = firstName
            it[Partner.lastName] = lastName
            it[createdAt] = LocalDateTime.now()
            it[updatedAt] = LocalDateTime.now()
        }.insertedCount
    }
}