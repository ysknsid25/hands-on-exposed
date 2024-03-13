package example.koin.data.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object Department : Table("DEPARTMENT") {
    val departmentId = integer("DEPARTMENT_ID") //部署ID
    val departmentName = varchar("DEPARTMENT_NAME", 32) //部署名
    val createdAt = datetime("CREATED_AT") //作成日時
    val updatedAt = datetime("UPDATED_AT") //更新日時
}