package br.com.andressa.repository

import br.com.andressa.model.Todo
import java.util.*


interface TodoRepository {

    fun saveCql(todo: Todo): Todo
    fun updateCql (id: UUID, todo: Todo): Todo
    fun deleteCql(id: UUID)
}