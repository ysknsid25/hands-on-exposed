package example.koin.service

import example.koin.accessor.SelectDataAccessor
import example.koin.data.model.Employee

class SelectService(
    private val selectDataAccessor: SelectDataAccessor
): TransactionService() {
    fun getInorin(): String{
        val message = executeQuery {
            val resultRow = selectDataAccessor.selectEmployeeById(1)
            "社員の名前は「${resultRow[Employee.lastName]} ${resultRow[Employee.firstName]}」です！"
        }
        return message
    }
}