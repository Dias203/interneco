package com.example.interneco.task2.model

// class, inheritance
class EBook(
    title: String,
    author: String,
    year: Int,
    genre: String,
    private var sizeMB: Double
) : Book(title, author, year, genre) {

    // override function
    override fun getType() = "Sách điện tử"

    override fun toString(): String {
        return "${super.toString()} - kích thước: $sizeMB MB"
    }

    override fun updateBookDetails(title: String, author: String, publishYear: Int): Boolean {
        // Cập nhật thông tin chung

        this.title = title
        this.author = author
        this.year = publishYear

        return true
    }

    fun updateSize(sizeMB: Double): Boolean {
        this.sizeMB = sizeMB
        return true
    }


}