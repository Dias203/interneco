package com.example.interneco.task2.system.manager

import com.example.interneco.task2.utils.borrowBook
import com.example.interneco.task2.utils.returnBook
/**
 * BorrowManager: Lớp quản lý việc mượn trả sách trong thư viện
 * - Kế thừa từ LibraryManager
 * - Quản lý danh sách sách được mượn bởi mỗi người dùng
 *
 * Chức năng chính:
 * 1. Mượn sách: Kiểm tra người dùng và sách tồn tại, sau đó thêm vào danh sách mượn
 * 2. Trả sách: Kiểm tra điều kiện và xóa sách khỏi danh sách mượn
 * 3. Hiển thị danh sách sách đã mượn của một người dùng
 * 4. Kiểm tra trạng thái mượn của sách
 * 5. Thống kê số lượng sách được mượn
 */
class BorrowManager : LibraryManager() {
    // Lưu trữ sách được mượn với cấu trúc: userId -> List<bookId>
    private val borrowedBooks = mutableMapOf<String, MutableList<String>>()

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
            println("Lỗi: Không tìm thấy người dùng với ID = $userId!")
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

    // Phương thức lấy tổng số sách đang được mượn
    fun getTotalBorrowedBooks(): Int {
        return borrowedBooks.values.sumOf { it.size }
    }

    //safe call & elvis operator
    // Phương thức lấy tổng số sách đang được mượn của một người dùng
    fun getUserBorrowedBooksCount(userId: String): Int {
        return borrowedBooks[userId]?.size ?: 0
    }
}