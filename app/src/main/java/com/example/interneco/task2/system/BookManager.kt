package com.example.interneco.task2.system

import com.example.interneco.task2.model.Book

class BookManager {
    private val books = mutableListOf<Book>()

    // Thêm sách
    fun addBook(book: Book) {
        books.add(book)
        println("Thêm sách thành công!")
    }

    // Hiển thị danh sách sách
    fun displayBooks() {
        println("=== Danh sách sách (${books.size} cuốn) ===")
        if (books.isEmpty()) {
            println("Không có sách trong thư viện!")
            return
        }

        for (item in books) {
            println(item.toString()) // Giả sử Book có toString() được định nghĩa
        }
    }

    // Tìm sách theo ID (nullable)
    fun findBookById(bookId: String): Book? {
        return books.find { it.id == bookId }
    }

    // Tìm kiếm sách theo tiêu đề
    fun searchBookByTile(title: String): List<Book> {
        val result = books.filter {
            it.title.contains(title, ignoreCase = true)
        }
        println("=== Kết quả tìm kiếm cho '$title' (${result.size} cuốn) ===")
        if (result.isEmpty()) {
            println("Không tìm thấy kết quả nào!")
        } else {
            result.forEachIndexed { index, book -> println("${index + 1} - $book") }
        }
        return result
    }
}