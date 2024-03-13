package example.koin.service

import example.koin.accessor.InsertDataAccessor

class InsertService(
    private val insertDataAccessor: InsertDataAccessor
): TransactionService() {
}