package example.koin.data.record

import java.time.LocalDateTime

data class DepartmentRecord(
    val departmentId: Int,
    val departmentName: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
