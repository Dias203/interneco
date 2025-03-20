package com.example.interneco.task2.model

// class, inheritance
class EBook(
    title: String,
    author: String,
    year: Int,
    genre: String,
    var sizeMB: Double
) : Book(title, author, year, genre) {

    // Override function
    override fun getType() = "Sách điện tử"

    override fun toString(): String {
        return "${super.toString()} - kích thước: $sizeMB MB"
    }
}