package com.example.interneco.task2.system.manager

import com.example.interneco.task2.model.Book
import com.example.interneco.task2.model.User

/**
 * Lớp quản lý thư viện tổng thể.
 * Kết hợp BookManager và UserManager để quản lý sách và người dùng.
 * Cung cấp phương thức ảo cho các hoạt động mượn/trả sách để lớp con ghi đè.
 */
open class LibraryManager {
    private val bookManager = BookManager()
    private val userManager = UserManager()

    /*=================================BOOKS========================================*/

    // Thêm sách
    fun addBook(book: Book) {
        bookManager.addBook(book)
    }

    fun updatePhysicalBook(bookId: String, title: String, newAuthor: String, newPublishYear: Int, newPage: Int) {
        bookManager.updatePhysicalBook(bookId, title, newAuthor, newPublishYear, newPage)
    }
    fun updateEBook(bookId: String, title: String, newAuthor: String, newPublishYear: Int, newSize: Double) {
        bookManager.updateEBook(bookId, title, newAuthor, newPublishYear, newSize)
    }

    // Xóa sách
    fun deleteBook(bookId: String): Boolean {
        return bookManager.deleteBook(bookId)
    }

    // Hiển thị danh sách sách
    fun displayBooks() {
        bookManager.displayBooks()
    }

    // Tìm sách theo ID
    fun findBookById(bookId: String): Book? {
        return bookManager.findBookById(bookId)
    }

    // Tìm sách theo tiêu đề
    fun searchBookByTitle(title: String): List<Book> {
        return bookManager.searchBookByTitle(title)
    }

    // Lấy tất cả sách
    fun getAllBooks(): List<Book> {
        return bookManager.getAllBooks()
    }

    /*=================================USERS========================================*/

    // Thêm người dùng
    fun addUser(user: User) {
        userManager.addUser(user)
    }

    // Cập nhật thông tin người dùng
    fun updateUser(userId: String, userName: String, userEmail: String) {
        userManager.updateUser(userId, userName, userEmail)
    }

    // Xóa người dùng
    fun deleteUser(userId: String): Boolean {
        return userManager.deleteUser(userId)
    }

    // Lấy tất cả người dùng
    fun getAllUsers(): List<User> {
        return userManager.getAllUsers()
    }

    // Hiển thị danh sách người dùng
    fun displayUsers() {
        userManager.displayUsers()
    }

    // Tìm người dùng theo ID
    fun findUserById(userId: String): User? {
        return userManager.findUserById(userId)
    }

    // Tìm người dùng theo tên
    fun findUserByName(userName: String): List<User> {
        return userManager.findUserByName(userName)
    }

    /*==============================BORROW BOOKS========================================*/

    /**
     * Cho mượn sách - phương thức ảo để lớp con ghi đè
     * @param userId ID của người dùng muốn mượn sách
     * @param bookId ID của sách muốn mượn
     * @return true nếu mượn thành công, false nếu thất bại
     */
    open fun borrowBook(userId: String, bookId: String): Boolean {
        return false // Mặc định trả về false, BorrowManager sẽ ghi đè
    }

    /**
     * Trả sách - phương thức ảo để lớp con ghi đè
     * @param userId ID của người dùng trả sách
     * @param bookId ID của sách được trả
     * @return true nếu trả thành công, false nếu thất bại
     */
    open fun returnBook(userId: String, bookId: String): Boolean {
        return false // Mặc định trả về false
    }

    /**
     * Hiển thị sách đã mượn - phương thức ảo để lớp con ghi đè
     * @param userId ID của người dùng cần xem sách đã mượn
     */
    open fun displayBorrowedBooks(userId: String) {
        println("Chưa có thông tin sách mượn.")
    }
}