package com.example.interneco.task2.system.manager

import com.example.interneco.task2.model.Book
import com.example.interneco.task2.model.EBook
import com.example.interneco.task2.model.PhysicalBook
import com.example.interneco.task2.model.User
import com.example.interneco.task2.utils.book.borrowBook
import com.example.interneco.task2.utils.book.returnBook
import com.example.interneco.task2.utils.book.updateBookDetails
import com.example.interneco.task2.utils.book.updatePages
import com.example.interneco.task2.utils.book.updateSize
import com.example.interneco.task2.utils.displayAll

/**
 * Lớp quản lý thư viện tổng thể.
 * Kết hợp BookManager và UserManager để quản lý sách và người dùng.
 * Cung cấp phương thức ảo cho các hoạt động mượn/trả sách để lớp con ghi đè.
 */
class LibraryManager(
    override val users: MutableList<User>,
    override val books: MutableList<Book>,
    override val borrowedBooks: MutableMap<String, MutableList<String>>
) : UserManager, BookManager, BorrowManager {

    /*=================================BOOKS========================================*/

    // Thêm sách
    override fun addBook(book: Book) {
        books.add(book)
        println("Thêm sách thành công!")
    }

    override fun updatePhysicalBook(bookId: String, publishYear: Int, pages: Int) {
        when (val book = findBookById(bookId)) {
            null -> {
                println("Không tìm thấy sách với ID: $bookId")
            }

            !is PhysicalBook -> {
                println("Lỗi: Sách với ID $bookId không phải là sách giấy!")
            }

            else -> {
                // Tiếp tục logic xử lý
                // Sử dụng apply để cập nhật thông tin
                val baseResult: Boolean
                val pagesResult: Boolean

                book.apply {
                    baseResult = updateBookDetails(publishYear)
                    pagesResult = updatePages(pages)
                }

                if (baseResult && pagesResult) {
                    println("Cập nhật thông tin sách thành công!")
                    println("Thông tin sách sau khi cập nhật: $book")
                } else {
                    println("Có lỗi khi cập nhật thông tin sách!")
                }
            }
        }
    }


    override fun updateEBook(bookId: String, publishYear: Int, sizeMB: Double) {

        when (val book = findBookById(bookId)) {
            null -> {
                println("Không tìm thấy sách với ID: $bookId")
            }

            !is EBook -> {
                println("Lỗi: Sách với ID $bookId không phải là sách điện tử!")
            }

            else -> {
                val baseResult: Boolean
                val sizeResult: Boolean

                book.apply {
                    baseResult = updateBookDetails(publishYear)
                    sizeResult = updateSize(sizeMB)
                }

                if (baseResult && sizeResult) {
                    println("Cập nhật thông tin sách thành công!")
                    println("Thông tin sách sau khi cập nhật: $book")
                } else {
                    println("Có lỗi khi cập nhật thông tin sách!")
                }
            }
        }
    }


    override fun deleteBook(bookId: String): Boolean {
        return findBookById(bookId)?.let {
            books.remove(it)
            println("Xóa sách có ID: $bookId thành công")
            true
        } ?: run {
            println("Không tìm thấy sách với ID: $bookId")
            false
        }
    }

    // Hiển thị danh sách sách
    override fun displayBooks() {
        println("=== Danh sách sách (${books.size} cuốn) ===")
        if (books.isEmpty()) println("Không có sách trong thư viện!") else books.let {
            books.displayAll { it.toString() }
        }
    }

    // Tìm sách theo ID
    override fun findBookById(bookId: String): Book? {
        // gọi đến inline function
        return books.find { it.id == bookId }
    }

    // Tìm sách theo tiêu đề
    override fun searchBookByTitle(title: String): List<Book> {
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


    /*=================================USERS========================================*/

    // Thêm người dùng
    override fun addUser(user: User) {
        users.add(user)
        println("Thêm người dùng thành công!")
    }


    // Cập nhật thông tin người dùng
    override fun updateUser(userId: String, userNewName: String, userNewEmail: String) {
        findUserById(userId)?.let {
            it.apply {
                it.name = userNewName
                it.email = userNewEmail
            }
            println("Cập nhật thông tin người dùng thành công!")
            println("Thông tin người dùng sau khi cập nhật: $it")
        } ?: run {
            println("Không tìm thấy người dùng với ID: $userId")
        }
    }

    // Xóa người dùng
    override fun deleteUser(userId: String): Boolean {

        return findUserById(userId)?.let {
            users.remove(it)
            println("Xóa người dùng có ID: $userId thành công")
            true
        } ?: run {
            println("Không tìm thấy người dùng với ID: $userId")
            false
        }
    }

    // Lấy tất cả người dùng
    override fun getAllUsers(): List<User> {
        return users.toList()
    }

    // Hiển thị danh sách người dùng
    override fun displayUsers() {
        println("=== Danh sách người dùng (${users.size} người) ===")
        if (users.isEmpty()) println("Không có sách trong thư viện!") else users.let {
            users.displayAll { it.toString() }
        }
    }


    // Tìm người dùng theo ID
    override fun findUserById(userId: String): User? {
        // gọi đến inline function
        return users.find { it.id == userId }
    }

    // Tìm người dùng theo tên
    override fun findUserByName(userName: String): List<User> {
        val result = users.filter {
            it.name.contains(userName, ignoreCase = true)
        }

        println("=== Kết quả tìm kiếm cho '$userName' (${result.size} người) ===")
        if (result.isEmpty()) {
            println("Không tìm thấy kết quả nào!")
        } else {
            result.forEachIndexed { index, user ->
                println("${index + 1} - $user")
            }
        }

        return result
    }

    /*==============================BORROW BOOKS========================================*/

    /**
     * Cho mượn sách - phương thức ảo để lớp con ghi đè
     * @param userId ID của người dùng muốn mượn sách
     * @param bookId ID của sách muốn mượn
     * @return true nếu mượn thành công, false nếu thất bại
     */
    override fun borrowBook(userId: String, bookId: String) {
        val user = findUserById(userId)
        val book = findBookById(bookId)

        if (user == null) {
            println("Lỗi: Không tìm thấy người dùng với ID = $userId!")
        }

        if (book == null) {
            println("Lỗi: Không tìm thấy sách với ID = $bookId!")
        }

        return borrowedBooks.borrowBook(userId, bookId, user!!, book!!)
    }

    /**
     * Trả sách - phương thức ảo để lớp con ghi đè
     * @param userId ID của người dùng trả sách
     * @param bookId ID của sách được trả
     * @return true nếu trả thành công, false nếu thất bại
     */
    override fun returnBook(userId: String, bookId: String) {
        val user = findUserById(userId)
        val book = findBookById(bookId)

        if (user == null) {
            println("Lỗi: Không tìm thấy người dùng với ID = $userId!")
        }

        if (book == null) {
            println("Lỗi: Không tìm thấy sách với ID = $bookId!")
        }

        return borrowedBooks.returnBook(userId, bookId, user!!, book!!)
    }

    /**
     * Hiển thị sách đã mượn - phương thức ảo để lớp con ghi đè
     * @param userId ID của người dùng cần xem sách đã mượn
     */
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

    override fun getTotalBorrowedBooks(): Int {
        return borrowedBooks.values.let { values ->
            values.sumOf { it.size }
        }
    }

    // null-safety và default value
    override fun getUserBorrowedBooksCount(userId: String): Int {
        return borrowedBooks[userId]?.size ?: 0
    }
}