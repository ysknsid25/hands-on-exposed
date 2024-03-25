package example.koin.accessor

import example.koin.data.model.Department
import example.koin.data.model.Employee
import example.koin.data.model.Expense
import example.koin.data.model.Partner
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.between
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import org.jetbrains.exposed.sql.SqlExpressionBuilder.lessEq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.SqlExpressionBuilder.neq

class SelectDataAccessor {

    private val innerJoinExpenseEmployee =
        Employee.join(Expense, JoinType.INNER, Employee.employeeId, Expense.employeeId)

    fun selectEmployeeById(id: Int): ResultRow {
        return Employee.slice(
            Employee.firstName,
            Employee.lastName,
        ).select(
            (Employee.employeeId eq id)
        ).first()
    }

    fun selectEmployeeAll(): List<ResultRow> {
        return Employee.selectAll().toList()
    }

    fun selectPartnerAll(): List<ResultRow> {
        return Partner.selectAll().toList()
    }

    fun selectEmployeeAllNames(): List<ResultRow> {
        return Employee.slice(
            Employee.firstName,
            Employee.lastName,
        ).selectAll().toList()
    }

    fun selectPartnerAllNames(): List<ResultRow> {
        return Partner.slice(
            Partner.firstName,
            Partner.lastName,
        ).selectAll().toList()
    }

    fun selectPartnerById(id: Int): ResultRow? {
        return Partner.slice(
            Partner.firstName,
            Partner.lastName,
        ).select(
            (Partner.partnerId eq id)
        ).firstOrNull()
    }

    fun selectPartnerByLikeKeyword(keyword: String): List<ResultRow> {
        return Partner.slice(
            Partner.firstName,
            Partner.lastName,
        ).select(
            (Partner.lastName like "%${keyword}%")
        ).toList()
    }

    fun selectEmployeeNameOfGeneralOrAccounting(): List<ResultRow> {
        return Employee.slice(
            Employee.firstName,
            Employee.lastName,
        ).select(
            Employee.departmentId inList listOf(1, 2)
        ).toList()
//        return Employee.slice(
//            Employee.firstName,
//            Employee.lastName,
//        ).select(
//            Employee.departmentId neq 3
//        ).toList()
//        val whereCondition = listOf(
//            Employee.departmentId eq 1,
//            Employee.departmentId eq 2
//        ).compoundOr()
//        return Employee.slice(
//            Employee.firstName,
//            Employee.lastName,
//        ).select(
//            whereCondition
//        ).toList()
    }

    fun selectEmployeeBySorted(): List<ResultRow> {
        return Employee.slice(
            Employee.firstName,
            Employee.lastName,
        ).selectAll().orderBy(
            Pair(Employee.departmentId, SortOrder.DESC),
            Pair(Employee.lastName, SortOrder.ASC),
        ).toList()
    }

    fun selectPartnerBySorted(): List<ResultRow> {
        return Partner.slice(
            Partner.firstName,
            Partner.lastName,
        ).selectAll().orderBy(
            Pair(Partner.departmentId, SortOrder.DESC),
            Pair(Partner.lastName, SortOrder.ASC),
        ).toList()
    }

    fun selectHowManyApplyExpenseByEmployee(): List<ResultRow> {
        return Expense.slice(
            Expense.employeeId,
            Expense.employeeId.count(),
        ).selectAll().groupBy(Expense.employeeId).toList()
    }

    fun selectHowMuchExpenseByEmployee(): List<ResultRow> {
        return Expense.slice(
            Expense.employeeId,
            Expense.expense.sum(),
        ).selectAll().groupBy(Expense.employeeId).toList()
    }

    fun selectEmployeeLimitOffset(): List<ResultRow> {
        return Employee.slice(
            Employee.firstName,
            Employee.lastName,
        ).selectAll().limit(3, offset = 1).toList()
    }

    fun selectPartnerLimitOffset(): List<ResultRow> {
        return Partner.slice(
            Partner.firstName,
            Partner.lastName,
        ).selectAll().limit(3, offset = 1).toList()
    }

    fun joinEmployeeDepartmentAll(): List<ResultRow> {
        return Employee.join(Department, JoinType.INNER, Employee.departmentId, Department.departmentId)
            .slice(
                Department.departmentName,
                Employee.firstName,
                Employee.lastName,
            )
            .selectAll()
            .toList()
    }

    fun joinPartnerDepartmentAll(): List<ResultRow> {
        return Partner.join(Department, JoinType.INNER, Partner.departmentId, Department.departmentId)
            .slice(
                Department.departmentName,
                Partner.firstName,
                Partner.lastName,
            )
            .selectAll()
            .toList()
    }

    fun selectHasExpenseEmployeeNames(): List<ResultRow> {
        return innerJoinExpenseEmployee.slice(
            Employee.firstName,
            Employee.lastName
        ).selectAll().withDistinct().toList()
    }

    fun selectHasExpenseEmployeeNamesWithBetween(): List<ResultRow> {
        //別解
//        return innerJoinExpenseEmployee.slice(
//            Employee.firstName,
//            Employee.lastName
//        ).select(
//            listOf(
//                Expense.expense greaterEq 1000,
//                Expense.expense lessEq 2000,
//            ).compoundAnd()
//        ).withDistinct().toList()
        return innerJoinExpenseEmployee.slice(
            Employee.firstName,
            Employee.lastName
        ).select(
            Expense.expense.between(1000, 2000)
        ).withDistinct().toList()
    }

    fun unionAllEmployeeAndPartner(): List<ResultRow> {
        val employeeDirectedQuery = Employee.slice(
            EMPLOYEE_TYPE,
            Employee.firstName,
            Employee.lastName
        ).selectAll()
        val partnerDirectedQuery = Partner.slice(
            PARTNER_TYPE,
            Partner.firstName,
            Partner.lastName
        ).selectAll()
        return employeeDirectedQuery.unionAll(partnerDirectedQuery).toList()
    }

    fun unionEmployeeAndPartner(): List<ResultRow> {
        val employeeDirectedQuery = Employee.slice(
            EMPLOYEE_TYPE,
            Employee.firstName,
            Employee.lastName
        ).selectAll()
        val partnerDirectedQuery = Partner.slice(
            PARTNER_TYPE,
            Partner.firstName,
            Partner.lastName
        ).selectAll()
        return employeeDirectedQuery.union(partnerDirectedQuery).toList()
    }

    fun hasExpenseEmployeeIdAndNames(): List<ResultRow> {
        val hasExpenseEmployeeId = Expense.slice(
            Expense.employeeId,
            Expense.employeeId.count()
        ).selectAll().groupBy(Expense.employeeId).having { Expense.employeeId.count() greater 0 }
            .alias("hasExpenseEmployeeId")

        return Employee.join(
            hasExpenseEmployeeId,
            JoinType.INNER,
            Employee.employeeId,
            hasExpenseEmployeeId[Expense.employeeId]
        ).slice(
            Employee.employeeId,
            Employee.firstName,
            Employee.lastName,
        ).selectAll().toList()
    }

    fun overExpenseEmployeeIdAndNames(): List<ResultRow> {
        val hasExpenseEmployeeId = Expense.slice(
            Expense.employeeId
        ).selectAll().groupBy(Expense.employeeId).having { Expense.expense.sum() greaterEq 2500 }
            .alias("overExpenseEmployeeId")

        return Employee.join(
            hasExpenseEmployeeId,
            JoinType.INNER,
            Employee.employeeId,
            hasExpenseEmployeeId[Expense.employeeId]
        ).slice(
            Employee.employeeId,
            Employee.firstName,
            Employee.lastName,
        ).selectAll().toList()
    }

    fun existsOverExpenseEmployeeIdAndNames(): List<ResultRow> {
        val hasExpenseEmployeeId = Expense.slice(
            Expense.employeeId
        ).selectAll().groupBy(Expense.employeeId).having { Expense.expense.sum() greaterEq 2500 }
            .alias("overExpenseEmployeeId")

        return Employee.slice(
            Employee.employeeId,
            Employee.firstName,
            Employee.lastName,
        ).select {
            exists(
                hasExpenseEmployeeId.select {
                    hasExpenseEmployeeId[Expense.employeeId] eq Employee.employeeId
                }
            )
        }.toList()
    }

    fun latestEmployeeIdByDepartmentId(): List<ResultRow> {
        return Department.join(
            latestEmployeeIdByDepartmentId,
            JoinType.INNER,
            latestEmployeeIdByDepartmentId[Employee.departmentId],
            Department.departmentId
        ).slice(
            Department.departmentName,
            latestEmployeeIdByDepartmentId[EMPLOYTT_ID_MAX]
        ).selectAll().toList()
    }

    fun selectEmployeeNamesAndEnrollmentStatus(): List<ResultRow> {
        return Employee.slice(
            Employee.firstName,
            Employee.lastName,
            caseEnrollmentStatus,
        ).selectAll().toList()
    }

    fun selectPartnerNamesAndEnrollmentStatus(): List<ResultRow> {
        return Partner.slice(
            Partner.firstName,
            Partner.lastName,
            casePartnerEnrollmentStatus,
        ).selectAll().toList()
    }

    fun selectConcatEmployeeNames(): List<ResultRow> {
        return Employee.slice(employeeNameConcat).selectAll().toList()
    }

    fun selectConcatPartnerNames(): List<ResultRow> {
        return Partner.slice(partnerNameConcat).selectAll().toList()
    }

    companion object {
        val EMPLOYEE_TYPE = LiteralOp(ShortColumnType(), 1.toShort())
        val PARTNER_TYPE = LiteralOp(ShortColumnType(), 2.toShort())

        val EMPLOYTT_ID_MAX = Employee.employeeId.max().alias("maxEmployeeId")
        val latestEmployeeIdByDepartmentId = Employee.slice(
            Employee.departmentId,
            EMPLOYTT_ID_MAX,
        ).selectAll().groupBy(Employee.departmentId).alias("latestEmployeeIdByDepartmentId")

        val caseEnrollmentStatus = Expression.build {
            Case().When(Employee.enrollmentStatus eq 0, stringLiteral("在籍中"))
                .When(Employee.enrollmentStatus eq 1, stringLiteral("休職中"))
                .When(Employee.enrollmentStatus eq 2, stringLiteral("退職済"))
                .Else(stringLiteral("その他"))
        }
        val casePartnerEnrollmentStatus = Expression.build {
            Case().When(Partner.enrollmentStatus eq 0, stringLiteral("在籍中"))
                .When(Partner.enrollmentStatus eq 1, stringLiteral("休職中"))
                .When(Partner.enrollmentStatus eq 2, stringLiteral("退職済"))
                .Else(stringLiteral("その他"))
        }

        val employeeNameConcat = Concat(" ", Employee.lastName, Employee.firstName)
        val partnerNameConcat = Concat(" ", Partner.lastName, Partner.firstName)

    }
}