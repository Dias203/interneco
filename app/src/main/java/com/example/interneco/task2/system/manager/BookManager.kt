package com.example.interneco.task2.system.manager

import com.example.interneco.task2.model.Book
import com.example.interneco.task2.model.EBook
import com.example.interneco.task2.model.PhysicalBook

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

    fun updatePhysicalBook(bookId: String, title: String, author: String, publishYear: Int, pages: Int) {
        val book = findBookById(bookId)

        if (book == null) {
            println("Không tìm thấy sách với ID: $bookId")
            return
        }

        if (book !is PhysicalBook) {
            println("Lỗi: Sách với ID $bookId không phải là sách giấy!")
            return
        }

        // Gọi phương thức chung
        val baseResult = book.updateBookDetails(title, author, publishYear)

        // Gọi phương thức riêng cho sách giấy
        val pagesResult: Boolean = book.updatePages(pages)
        if (pagesResult) {
            println("Đã cập nhật số trang thành: $pages")
        }

        if (baseResult && pagesResult) {
            println("Cập nhật thông tin sách thành công!")
            println("Thông tin sách sau khi cập nhật: $book")
        } else {
            println("Có lỗi khi cập nhật thông tin sách!")
        }
    }

    // Cập nhật sách điện tử, tận dụng phương thức updateBook
    fun updateEBook(bookId: String, title: String, author: String, publishYear: Int, sizeMB: Double) {
        val book = findBookById(bookId)

        if (book == null) {
            println("Không tìm thấy sách với ID: $bookId")
            return
        }

        if (book !is EBook) {
            println("Lỗi: Sách với ID $bookId không phải là sách điện tử!")
            return
        }

        // Gọi phương thức chung
        val baseResult = book.updateBookDetails(title, author, publishYear)

        // Gọi phương thức riêng cho sách điện tử
        val sizeResult: Boolean = book.updateSize(sizeMB)
        if (sizeResult) {
            println("Đã cập nhật kích thước thành: $sizeMB MB")
        }

        if (baseResult && sizeResult) {
            println("Cập nhật thông tin sách thành công!")
            println("Thông tin sách sau khi cập nhật: $book")
        } else {
            println("Có lỗi khi cập nhật thông tin sách!")
        }
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