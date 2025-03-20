package com.example.interneco.task2.utils.user

object UserIdGenerator {
    private var nextId = 1
    fun generateId(): String = "U-${nextId++}"
}
