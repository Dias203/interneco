package com.example.interneco.task2.system

import com.example.interneco.task2.model.EBook
import com.example.interneco.task2.model.PhysicalBook
import com.example.interneco.task2.model.User

val library = BorrowManager() // Sử dụng BorrowManager thay vì LibraryManager

fun addBookIntoLibrary() {
    var input: Int?

    do {
        println("Chọn loại sách muốn thêm:")
        println("1. Sách giấy")
        println("2. Sách điện tử")
        input = readlnOrNull()?.toIntOrNull()
        if (input != null && input != 1 && input != 2) {
            println("Lựa chọn không hợp lệ! Vui lòng chọn lại.")
        }
    } while (input == null || (input != 1 && input != 2))

    if (input == 1) {
        print("Nhập tên sách: ")
        val title = readlnOrNull()
        print("Nhập tên tác giả: ")
        val author = readlnOrNull()
        print("Nhập năm xuất bản: ")
        val year = readlnOrNull()?.toIntOrNull()
        print("Nhập thể loại sách: ")
        val genre = readlnOrNull()
        print("Nhập số trang: ")
        val page = readlnOrNull()?.toIntOrNull()

        if (title.isNullOrBlank() || author.isNullOrBlank() || genre.isNullOrBlank() || year == null || page == null) {
            println("Lỗi: Vui lòng nhập đầy đủ và chính xác thông tin sách.")
        } else {
            val book = PhysicalBook(title, author, year, genre, page)
            library.addBook(book)
        }
    }
    else{
        print("Nhập tên sách: ")
        val title = readlnOrNull()
        print("Nhập tên tác giả: ")
        val author = readlnOrNull()
        print("Nhập năm xuất bản: ")
        val year = readlnOrNull()?.toIntOrNull()
        print("Nhập thể loại sách: ")
        val genre = readlnOrNull()
        print("Nhập kích thước (MB) của sách: ")
        val sizeMB = readlnOrNull()?.toDoubleOrNull()

        if (title.isNullOrBlank() || author.isNullOrBlank() || genre.isNullOrBlank() || year == null || sizeMB == null) {
            println("Lỗi: Vui lòng nhập đầy đủ và chính xác thông tin sách.")
        } else {
            val book = EBook(title, author, year, genre, sizeMB)
            library.addBook(book)
        }
    }
}

fun addUserIntoSystem() {
    var isValid: Boolean
    do {
        print("Nhập tên người dùng: ")
        val name = readlnOrNull()
        print("Nhập email người dùng: ")
        val email = readlnOrNull()

        isValid = true

        if (name.isNullOrBlank() || email.isNullOrBlank()) {
            println("Lỗi: Vui lòng nhập đầy đủ thông tin!")
            isValid = false
        } else if (!email.matches(Regex("^[^@\\s]+@gmail\\.com\$"))) {
            println("Lỗi: Email phải có định dạng hợp lệ và kết thúc bằng @gmail.com.")
            isValid = false
        } else if (library.getAllUsers().any { it.email == email }) {
            println("Email đã tồn tại! Vui lòng nhập lại.")
            isValid = false
        }

        if (isValid) {
            val user = User(User.generateId(), name!!, email!!)
            library.addUser(user)
            println("Người dùng '${user.name}' đã được thêm thành công!")
        }
    } while (!isValid)
}

fun findUserByName() {
    do {
        print("Nhập tên người dùng: ")
        val userName = readlnOrNull()

        if (userName.isNullOrBlank()) {
            println("Lỗi: Vui lòng nhập đầy đủ thông tin!")
        } else {
            library.findUserByName(userName) // Không cần toString(), hàm đã in kết quả
        }
    } while (userName.isNullOrBlank())
}

fun findBookByTitle() {
    do {
        print("Nhập tên sách: ")
        val bookTitle = readlnOrNull()

        if (bookTitle.isNullOrBlank()) {
            println("Lỗi: Vui lòng nhập đầy đủ thông tin!")
        } else {
            library.searchBookByTile(bookTitle) // Không cần toString(), hàm đã in kết quả
        }
    } while (bookTitle.isNullOrBlank())
}

fun displayBooks() {
    library.displayBooks()
}

fun displayUsers() {
    library.displayUsers()
}

fun borrowBook() {
    do {
        print("Nhập UserID: ")
        val userID = readlnOrNull()
        print("Nhập BookID: ")
        val bookID = readlnOrNull()

        if (userID.isNullOrBlank() || bookID.isNullOrBlank()) {
            println("Lỗi: Vui lòng nhập đầy đủ thông tin!")
        } else {
            library.borrowBook(userID, bookID)
        }
    } while (userID.isNullOrBlank() || bookID.isNullOrBlank())
}

fun returnBook() {
    do {
        print("Nhập UserID: ")
        val userID = readlnOrNull()
        print("Nhập BookID: ")
        val bookID = readlnOrNull()

        if (userID.isNullOrBlank() || bookID.isNullOrBlank()) {
            println("Lỗi: Vui lòng nhập đầy đủ thông tin!")
        } else {
            library.returnBook(userID, bookID)
        }
    } while (userID.isNullOrBlank() || bookID.isNullOrBlank())
}

fun displayBorrowedBooks() {
    do {
        print("Nhập UserID: ")
        val userID = readlnOrNull()

        if (userID.isNullOrBlank()) {
            println("Lỗi: Vui lòng nhập đầy đủ thông tin!")
        } else {
            library.displayBorrowedBooks(userID)
        }
    } while (userID.isNullOrBlank())
}

fun printMenu() {
    println("\n===== HỆ THỐNG QUẢN LÝ THƯ VIỆN =====")
    println("1. Thêm sách mới")
    println("2. Thêm người dùng mới")
    println("3. Tìm kiếm người dùng theo tên")
    println("4. Tìm kiếm sách theo tên")
    println("5. Hiển thị danh sách sách")
    println("6. Hiển thị danh sách người dùng")
    println("7. Mượn sách")
    println("8. Trả sách")
    println("9. Hiển thị sách đã mượn của người dùng")
    println("0. Thoát chương trình")
    print("Nhập lựa chọn của bạn: ")
}

fun main() {
    var running = true

    while (running) {
        printMenu()

        val choice = readlnOrNull()?.toIntOrNull()

        when (choice) {
            1 -> addBookIntoLibrary()
            2 -> addUserIntoSystem()
            3 -> findUserByName()
            4 -> findBookByTitle()
            5 -> displayBooks()
            6 -> displayUsers()
            7 -> borrowBook()
            8 -> returnBook()
            9 -> displayBorrowedBooks()
            0 -> {
                println("Đang thoát chương trình...")
                running = false
            }
            else -> {
                println("Lựa chọn không hợp lệ! Đang thoát chương trình...")
                running = false
            }
        }

        if (running) {
            println("\nNhấn Enter để tiếp tục...")
            readlnOrNull()
        }
    }

    println("Cảm ơn bạn đã sử dụng hệ thống quản lý thư viện!")
}