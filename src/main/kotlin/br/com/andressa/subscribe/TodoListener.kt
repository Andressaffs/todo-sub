package br.com.andressa.subscribe


import br.com.andressa.model.EventTodo
import br.com.andressa.model.Todo
import br.com.andressa.service.TodoService
import io.micronaut.nats.annotation.NatsListener
import io.micronaut.nats.annotation.Subject
import org.slf4j.LoggerFactory

@NatsListener
class TodoListener(private val todoService: TodoService) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Subject("todo.scheduled")
    fun receiveTodo(eventTodo: EventTodo) {

        logger.info("objeto chegou $eventTodo")
        val todo = eventTodo.todo

        if (eventTodo.event.name.equals("SAVETODO_TODO")) {
            todoService.addTodo(todo)
            logger.info("salvo")
        }

        else if (eventTodo.event.name.equals("UPDATETODO_TODO")){
            todoService.updateTodo(todo.id!!, todo)
            logger.info("atualizado")
        }

        else if (eventTodo.event.name.equals("DELETETODO_TODO")){
            logger.info("deletado")
            todoService.deleteTodo(todo.id!!)
        }

    }
}