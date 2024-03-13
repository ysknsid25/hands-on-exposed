package example.koin.service

import example.koin.accessor.UpdateDataAccessor

class UpdateService(
    private val updateDataAccessor: UpdateDataAccessor
): TransactionService() {
}