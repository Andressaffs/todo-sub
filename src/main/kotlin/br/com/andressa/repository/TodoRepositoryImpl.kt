package br.com.andressa.repository

import br.com.andressa.model.Todo
import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.SimpleStatement
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Singleton

@Singleton
class TodoRepositoryImpl(private val cqlSession: CqlSession): TodoRepository {

//    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun saveCql(todo: Todo): Todo {
//        logger.info("antes de salvar $todo")
        cqlSession.execute(
                SimpleStatement.newInstance(
                        "INSERT INTO todo(id, date, description, done) values (?,?,?,?)",
                        UUID.randomUUID(),
                        todo.date,
                        todo.description,
                        todo.done
                )
        )
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