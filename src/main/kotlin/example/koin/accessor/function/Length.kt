package example.koin.accessor.function

import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.QueryBuilder

class Length(
    private val expression: Expression<String>
): Expression<Int>() {
    override fun toQueryBuilder(queryBuilder: QueryBuilder) = queryBuilder {
        append("LENGTH(")
        append(expression)
        append(")")
    }
}