package com.example.interneco.task2.model

class PhysicalBook(
    title: String,
    author: String,
    year: Int,
    genre: String,
    private var page: Int
) : Book(title, author, year, genre) {

    override fun getType() = "Sách giấy"

    override fun toString(): String {
        return "${super.toString()} - $page trang"
    }

    override fun updateBookDetails(publishYear: Int): Boolean {
        this.year = publishYear
        return true
    }

    // Default method
    // Phương thức cập nhật thông tin riêng của sách giấy
    fun updatePages(pages: Int): Boolean {
        this.page = pages
        return true
    }


}