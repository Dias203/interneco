package com.example.interneco.task2.system

import com.example.interneco.task2.model.Book
import com.example.interneco.task2.model.User

open class LibraryManager {
    private val bookManager = BookManager()
    private val userManager = UserManager()

    // Thêm sách
    fun addBook(book: Book) {
        bookManager.addBook(book)
    }

    // Thêm người dùng
    fun addUser(user: User) {
        userManager.addUser(user)
    }

    // Lấy tất cả người dùng
    fun getAllUsers(): List<User> {
        return userManager.getAllUsers()
    }

    // Hiển thị sách
    fun displayBooks() {
        bookManager.displayBooks()
    }

    // Hiển thị người dùng
    fun displayUsers() {
        userManager.displayUsers()
    }

    // Tìm sách theo ID
    fun findBookById(bookId: String): Book? {
        return bookManager.findBookById(bookId)
    }

    // Tìm sách theo tiêu đề
    fun searchBookByTile(title: String): List<Book> {
        return bookManager.searchBookByTile(title)
    }

    // Tìm người dùng theo ID
    fun findUserById(userId: String): User? {
        return userManager.findUserById(userId)
    }

    // Tìm người dùng theo tên
    fun findUserByName(userName: String): List<User> {
        return userManager.findUserByName(userName)
    }

    // Các phương thức ảo để BorrowManager ghi đè
    open fun borrowBook(userId: String, bookId: String): Boolean {
        return false // Mặc định trả về false, BorrowManager sẽ ghi đè
    }

    open fun returnBook(userId: String, bookId: String): Boolean {
        return false // Mặc định trả về false
    }

    open fun displayBorrowedBooks(userId: String) {
        println("Chưa có thông tin sách mượn.")
    }
}