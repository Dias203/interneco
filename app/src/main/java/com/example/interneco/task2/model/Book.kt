package com.example.interneco.task2.model

// abstract class
abstract class Book(
    val title: String,
    private val author: String,
    var year: Int,
    private val genre: String,
    val id : String = generateId()
) {
    // loại sách
    // abstract function
    abstract fun getType(): String

    // companion object
    companion object {
        private var nextId: Int = 1
        fun generateId() : String{
            return "B-${nextId++}"
        }
    }

    // override
    override fun toString(): String {
        return "[$id] - $title - $author - $year - $genre - ${getType()}"
    }

    // abstract function
    abstract fun updateBookDetails(publishYear: Int): Boolean
}