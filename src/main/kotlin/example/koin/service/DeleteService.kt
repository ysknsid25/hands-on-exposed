package example.koin.service

import example.koin.accessor.DeleteDataAccessor

class DeleteService(
    private val deleteDataAccessor: DeleteDataAccessor
): TransactionService() {
    fun deleteEmployee(
        id: Int,
    ): Int {
        val resultCount = executeQuery {
            deleteDataAccessor.deleteEmployee(id)
        }
        return resultCount
    }

    fun deletePartner(
        id: Int,
    ): Int {
        val resultCount = executeQuery {
            deleteDataAccessor.deletePartner(id)
        }
        return resultCount
    }
}