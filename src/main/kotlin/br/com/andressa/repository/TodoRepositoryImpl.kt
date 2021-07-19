package br.com.andressa.repository

import br.com.andressa.model.Todo
import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.SimpleStatement
import com.datastax.oss.driver.api.querybuilder.QueryBuilder.insertInto
import com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Singleton

@Singleton
class TodoRepositoryImpl(private val cqlSession: CqlSession): TodoRepository {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun saveCql(todo: Todo): Todo {
        logger.info("antes de salvar $todo")
        cqlSession.execute(
                insertInto("todo")
                        .value("id", literal(UUID.randomUUID()))
                        .value("date", literal(todo.date))
                        .value("description", literal(todo.description))
                        .value("done", literal(todo.done))
                        .build()
//                SimpleStatement.newInstance(
//                        "INSERT INTO todo(id, date, description, done) values (?,?,?,?)",
//                        UUID.randomUUID(),
//                        todo.date,
//                        todo.description,
//                        todo.done
//                )
        )
        logger.info("salvou")
        return todo
    }

    override fun updateCql(id: UUID, todo: Todo): Todo {
        cqlSession.execute(
                SimpleStatement.newInstance("UPDATE todo.Todo SET date=?, description=?, done=? WHERE id=?",

                        todo.date,
                        todo.description,
                        todo.done,
                        id

                )
        )
        return todo
    }

    override fun deleteCql(id: UUID) {
        cqlSession.execute(
                SimpleStatement.newInstance("DELETE FROM todo.Todo WHERE id=?",
                        id
                )
        )
    }
}