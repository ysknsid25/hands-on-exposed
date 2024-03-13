package example.koin.service

import example.koin.accessor.DeleteDataAccessor

class DeleteService(
    private val deleteDataAccessor: DeleteDataAccessor
): TransactionService() {
}