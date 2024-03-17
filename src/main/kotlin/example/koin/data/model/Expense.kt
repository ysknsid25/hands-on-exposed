package example.koin.data.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object Expense : Table("EXPENSE") {
    val id = integer("ID") //ID
    val employeeId = integer("EMPLOYEE_ID") //部署ID
    val expense = integer("EXPENSE") //経費
    val propose = varchar("PROPOSE", 256) //目的
    val createdAt = datetime("CREATED_AT") //作成日時
    val updatedAt = datetime("UPDATED_AT") //更新日時
}