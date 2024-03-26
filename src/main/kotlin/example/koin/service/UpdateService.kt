package example.koin.service

import example.koin.accessor.UpdateDataAccessor

class UpdateService(
    private val updateDataAccessor: UpdateDataAccessor
): TransactionService() {
    fun updateEmployee(
        id: Int,
        departmentId: Int,
        enrollmentStatus: Int,
        firstName: String,
        lastName: String,
    ): Int {
        val resultCount = executeQuery {
            updateDataAccessor.updateEmployee(id, departmentId, enrollmentStatus, firstName, lastName)
        }
        return resultCount
    }

    fun updatePartner(
        id: Int,
        departmentId: Int,
        enrollmentStatus: Int,
        firstName: String,
        lastName: String,
    ): Int {
        val resultCount = executeQuery {
            updateDataAccessor.updatePartner(id, departmentId, enrollmentStatus, firstName, lastName)
        }
        return resultCount
    }
}