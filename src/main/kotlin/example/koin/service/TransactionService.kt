package example.koin.service

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

open class TransactionService {
    fun <T> executeQuery(sqlExecuter: () -> T): T{
        Database.connect(
            url = "jdbc:mysql://127.0.0.1:13308/exposed_local",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "exposer",
            password = "secret"
        )
        return transaction {
            return@transaction sqlExecuter()
        }
    }
}