package example.koin.data.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object Employee : Table("EMPLOYEE") {
    val employeeId = integer("EMPLOYEE_ID") //社員ID
    val departmentId = integer("DEPARTMENT_ID") //部署ID
    val enrollmentStatus = integer("ENROLLMENT_STATUS") //在籍状況 0:在籍中 1:休職中 2:退職済
    val firstName = varchar("FIRST_NAME", 128) //ファーストネーム
    val lastName = varchar("LAST_NAME", 128) //ラストネーム
    val createdAt = datetime("CREATED_AT") //作成日時
    val updatedAt = datetime("UPDATED_AT") //更新日時
}