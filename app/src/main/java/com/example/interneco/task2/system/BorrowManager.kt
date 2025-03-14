package com.example.interneco.task2.system

import com.example.interneco.task2.model.Book
import com.example.interneco.task2.model.User

class BorrowManager : LibraryManager() {
    private val borrowedBooks = mutableMapOf<String, MutableList<String>>() // Danh sách sách người dùng mượn

    // Phương thức cho mượn sách
    override fun borrowBook(userId: String, bookId: String): Boolean {
        val user = findUserById(userId)
        val book = findBookById(bookId)

        if (user == null) {
            println("Lỗi: Không tìm thấy người dùng với ID = $userId!")
            return false
        }
        if (book == null) {
            println("Lỗi: Không tìm thấy sách với ID = $bookId!")
            return false
        }

        return borrowedBooks.borrowBook(userId, bookId, user, book)
    }

    // Phương thức trả sách
    override fun returnBook(userId: String, bookId: String): Boolean {
        val user = findUserById(userId)
        val book = findBookById(bookId)

        if (user == null) {
            println("Lỗi: Không tìm thấy người dùng với ID = $userId!")
            return false
        }
        if (book == null) {
            println("Lỗi: Không tìm thấy sách với ID = $bookId!")
            return false
        }

        return borrowedBooks.returnBook(userId, bookId, user, book)
    }

    // Hiển thị sách đã mượn
    override fun displayBorrowedBooks(userId: String) {
        val user = findUserById(userId)

        if (user == null) {
            println("Lỗi: Không tìm thấy người dùng")
            return
        }

        val userBorrowedBooks = borrowedBooks[userId]

        println("=== SÁCH ĐÃ MƯỢN CỦA ${user.name} ===")
        if (userBorrowedBooks.isNullOrEmpty()) {
            println("${user.name} chưa mượn sách nào.")
            return
        }

        val borrowedBooksList = userBorrowedBooks.mapNotNull { bookId ->
            findBookById(bookId)
        }

        borrowedBooksList.forEachIndexed { index, book ->
            println("${index + 1} - $book")
        }
    }
}

// Extension functions
fun MutableMap<String, MutableList<String>>.borrowBook(userId: String, bookId: String, user: User, book: Book): Boolean {
    val userBorrowedBooks = this.getOrPut(userId) { mutableListOf() }

    if (userBorrowedBooks.contains(bookId)) {
        println("Lỗi: ${user.name} đã mượn sách ${book.title} rồi!")
        return false
    }

    userBorrowedBooks.add(bookId)
    println("${user.name} đã mượn sách ${book.title} thành công!")
    return true
}

fun MutableMap<String, MutableList<String>>.returnBook(userId: String, bookId: String, user: User, book: Book): Boolean {
    val userBorrowedBooks = this[userId]

    if (userBorrowedBooks == null || !userBorrowedBooks.contains(bookId)) {
        println("Lỗi: ${user.name} chưa mượn sách ${book.title}!")
        return false
    }

    userBorrowedBooks.remove(bookId)
    println("${user.name} đã trả sách ${book.title} thành công!")
    return true
}