package example.koin.service

import example.koin.accessor.InsertDataAccessor
import example.koin.data.model.Employee

class InsertService(
    private val insertDataAccessor: InsertDataAccessor
): TransactionService() {
    fun insertEmployee(
        id: Int,
        departmentId: Int,
        enrollmentStatus: Int,
        firstName: String,
        lastName: String,
    ): Int {
        val resultCount = executeQuery {
            insertDataAccessor.insertEmployee(id, departmentId, enrollmentStatus, firstName, lastName)
        }
        return resultCount
    }

    fun insertPartner(
        id: Int,
        departmentId: Int,
        enrollmentStatus: Int,
        firstName: String,
        lastName: String,
    ): Int {
        val resultCount = executeQuery {
            insertDataAccessor.insertPartner(id, departmentId, enrollmentStatus, firstName, lastName)
        }
        return resultCount
    }
}