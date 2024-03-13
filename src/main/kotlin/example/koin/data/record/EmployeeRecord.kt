package example.koin.data.record

import java.time.LocalDateTime

data class EmployeeRecord(
    val employeeId: Int,
    val departmentId: Int,
    val enrollmentStatus: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
