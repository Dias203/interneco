package com.example.interneco.task2.model

class PhysicalBook(
    title: String,
    author: String,
    year: Int,
    genre: String,
    var page: Int
) : Book(title, author, year, genre) {

    override fun getType() = "Sách giấy"

    override fun toString(): String {
        return "${super.toString()} - $page trang"
    }

}