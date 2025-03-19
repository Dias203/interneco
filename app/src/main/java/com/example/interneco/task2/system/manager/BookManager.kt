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
interface BookManager {
    val books: MutableList<Book>

    // Thêm sách
    fun addBook(book: Book)

    fun updatePhysicalBook(bookId: String, publishYear: Int, pages: Int)

    // Cập nhật sách điện tử, tận dụng phương thức updateBook
    fun updateEBook(bookId: String, publishYear: Int, sizeMB: Double)


    // Xóa sách
    fun deleteBook(bookId: String): Boolean

    // Hiển thị danh sách sách
    fun displayBooks()

    // Tìm sách theo ID
    fun findBookById(bookId: String): Book?

    // Tìm kiếm sách theo tiêu đề
    fun searchBookByTitle(title: String): List<Book>

}