package com.example.interneco.task2.model

import com.example.interneco.task2.utils.book.BookIdGenerator.generateId


// abstract class
abstract class Book(
    val title: String,
    private val author: String,
    var year: Int,
    private val genre: String,
    val id : String = generateId()
) {

    // abstract function
    abstract fun getType(): String

    // override
    override fun toString(): String {
        return "[$id] - $title - $author - $year - $genre - ${getType()}"
    }

}