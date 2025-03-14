package com.example.interneco.task2.model


// abstract class
abstract class Book(
    val title: String,
    private val author: String,
    private val year: Int,
    private val genre: String,
    val id : String = generateId()
) {
    // companion object
    companion object {
        private var nextId: Int = 1
        fun generateId() : String{
            return "B-${nextId++}"
        }
    }

    // loại sách
    // abstract function
    abstract fun getType(): String

    // override
    override fun toString(): String {
        return "[$id] - $title - $author - $year - $genre - ${getType()}"
    }

}