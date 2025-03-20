package com.example.interneco.task2.utils.book

object BookIdGenerator {
    private var nextId = 1
    fun generateId(): String = "B-${nextId++}"
}