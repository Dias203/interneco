/**
 * Các hàm mở rộng để quản lý việc mượn và trả sách
 * File này chứa các extension functions cho MutableMap để hỗ trợ quản lý mượn/trả sách
 */

package com.example.interneco.task2.utils

import com.example.interneco.task2.model.Book
import com.example.interneco.task2.model.User

/**
 * Extension function để mượn sách
 * @param userId ID của người dùng mượn sách
 * @param bookId ID của sách được mượn
 * @param user Đối tượng User mượn sách
 * @param book Đối tượng Book được mượn
 * @return true nếu mượn thành công, false nếu thất bại
 */
fun MutableMap<String, MutableList<String>>.borrowBook(userId: String, bookId: String, user: User, book: Book){
    val userBorrowedBooks = this.getOrPut(userId) { mutableListOf() }

    if (userBorrowedBooks.contains(bookId)) {
        println("Lỗi: ${user.name} đã mượn sách ${book.title} rồi!")
    }

    userBorrowedBooks.add(bookId)
    println("${user.name} đã mượn sách ${book.title} thành công!")
}

/**
 * Extension function để trả sách
 * @param userId ID của người dùng trả sách
 * @param bookId ID của sách được trả
 * @param user Đối tượng User trả sách
 * @param book Đối tượng Book được trả
 * @return true nếu trả thành công, false nếu thất bại
 */
fun MutableMap<String, MutableList<String>>.returnBook(userId: String, bookId: String, user: User, book: Book){
    val userBorrowedBooks = this[userId]

    if (userBorrowedBooks == null || !userBorrowedBooks.contains(bookId)) {
        println("Lỗi: ${user.name} chưa mượn sách ${book.title}!")
    }

    userBorrowedBooks?.remove(bookId)
    println("${user.name} đã trả sách ${book.title} thành công!")
}