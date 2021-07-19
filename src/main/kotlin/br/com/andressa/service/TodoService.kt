package br.com.andressa.service

import br.com.andressa.model.Todo
import java.util.*


interface TodoService {

    fun addTodo(todo: Todo): Todo
    fun updateTodo(id: UUID, todo: Todo): Todo
    fun deleteTodo(id: UUID)
}