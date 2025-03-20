package com.example.interneco.task2.model

// Data class
data class User(
    val id: String,
    var name: String,
    var email: String

) {
    override fun toString(): String {
        return "[$id] - $name - $email"
    }
}