package com.example.interneco.task2.model

// Data class
data class User(
    val id: String,
    var name: String,
    var email: String

) {
    object Singleton {
        private var nextId = 1
        fun generateId() : String = "U-${nextId++}"
    }

    override fun toString(): String {
        return "[$id] - $name - $email"
    }


}