package br.com.andressa.service

import br.com.andressa.model.Todo
import br.com.andressa.repository.TodoRepository
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Singleton

@Singleton
class TodoServiceImpl(private val todoRepository: TodoRepository): TodoService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun addTodo(todo: Todo): Todo {
        logger.info("chegou no service $todo")
        return todoRepository.saveCql(todo)
    }

    override fun updateTodo(id: UUID, todo: Todo): Todo {
        return this.todoRepository.updateCql(id, todo)
    }

    override fun deleteTodo(id: UUID) {
        return this.todoRepository.deleteCql(id)
    }
}