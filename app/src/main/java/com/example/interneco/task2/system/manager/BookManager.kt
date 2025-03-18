package com.example.interneco.task2.system.manager

import com.example.interneco.task2.model.Book

/**
 * Lớp quản lý sách.
 * Cung cấp các phương thức cho các hoạt động quản lý sách.
 * Chức năng chính:
 * 1. Thêm sách
 * 2. Cập nhật thông tin sách
 * 3. Xóa sách
 * 4. Hiển thị danh sách sách
 * 5. Thống kê số lượng sách được mượn
 * 6. Tìm kiếm sách theo tên sách
 */
class BookManager {
    private val books = mutableListOf<Book>()

    // Thêm sách
    fun addBook(book: Book) {
        books.add(book)
        println("Thêm sách thành công!")
    }

    // Cập nhật thông tin sách
    fun updateBook(bookId: String, author: String? = null, publishYear: Int? = null) {
        val book = findBookById(bookId)

        if (book == null) {
            println("Không tìm thấy sách với ID: $bookId")
            return
        }

        // Cập nhật thông tin nếu được cung cấp
        if (author != null) {
            book.author = author
        }

        if (publishYear != null) {
            book.year = publishYear
        }

        println("Cập nhật thông tin sách thành công!")
        println("Thông tin sách sau khi cập nhật: $book")
    }

    // Xóa sách
    fun deleteBook(bookId: String): Boolean {
        val book = findBookById(bookId)

        if (book == null) {
            println("Không tìm thấy sách với ID: $bookId")
            return false
        }

        books.remove(book)
        println("Đã xóa sách có ID: $bookId thành công!")
        return true
    }

    // Hiển thị danh sách sách
    fun displayBooks() {
        println("=== Danh sách sách (${books.size} cuốn) ===")
        if (books.isEmpty()) {
            println("Không có sách trong thư viện!")
            return
        }

        books.forEachIndexed { index, book ->
            println("${index + 1} - $book")
        }
    }

    // Tìm sách theo ID
    fun findBookById(bookId: String): Book? {
        return books.find { it.id == bookId }
    }

    // Tìm kiếm sách theo tiêu đề
    fun searchBookByTitle(title: String): List<Book> {
        val result = books.filter {
            it.title.contains(title, ignoreCase = true)
        }

        println("=== Kết quả tìm kiếm cho '$title' (${result.size} cuốn) ===")
        if (result.isEmpty()) {
            println("Không tìm thấy kết quả nào!")
        } else {
            result.forEachIndexed { index, book ->
                println("${index + 1} - $book")
            }
        }

        return result.toList()
    }

    // Lấy tất cả sách
    fun getAllBooks(): List<Book> {
        return books.toList()
    }
}