package com.example.interneco.task2.system.manager
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
interface BorrowManager{
    // Lưu trữ sách được mượn với cấu trúc: userId -> List<bookId>
    val borrowedBooks: MutableMap<String, MutableList<String>>

    // Phương thức cho mượn sách
    fun borrowBook(userId: String, bookId: String)

    // Phương thức trả sách
    fun returnBook(userId: String, bookId: String)

    // Hiển thị sách đã mượn
    fun displayBorrowedBooks(userId: String)

    // Phương thức lấy tổng số sách đang được mượn
    fun getTotalBorrowedBooks(): Int

    //safe call & elvis operator
    // Phương thức lấy tổng số sách đang được mượn của một người dùng
    fun getUserBorrowedBooksCount(userId: String): Int
}