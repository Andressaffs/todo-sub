package br.com.andressa.model

import java.util.*

data class Todo(

        val id: UUID? = null,
        val date: String? = "",
        val description: String? ="",
        val done: Boolean = false
)
