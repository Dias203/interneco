    package com.example.interneco.task2
    import com.example.interneco.task2.model.Book
    import com.example.interneco.task2.model.User
    import com.example.interneco.task2.system.handle.BookHandler
    import com.example.interneco.task2.system.handle.UserHandler
    import com.example.interneco.task2.system.manager.LibraryManager
    import com.example.interneco.task2.utils.Menu

    fun main() {
        // Khởi tạo các danh sách/map cần thiết
        val users = mutableListOf<User>()
        val books = mutableListOf<Book>()
        val borrowedBooks = mutableMapOf<String, MutableList<String>>()

        // Khởi tạo LibraryManager với các tham số yêu cầu
        val libraryManager = LibraryManager(users, books, borrowedBooks)
        val bookHandler = BookHandler(libraryManager)
        val userHandler = UserHandler(libraryManager)

        var running = true

        while (running) {
            Menu.mainMenu()
            val choice = readlnOrNull()?.toIntOrNull()

            when (choice) {
                1 -> bookHandler.handleBooksMenu()
                2 -> userHandler.handleUsersMenu()
                0 -> {
                    println("Đang thoát chương trình...")
                    running = false
                }
                else -> {
                    println("Lựa chọn không hợp lệ!")
                }
            }
        }
        println("Cảm ơn bạn đã sử dụng hệ thống quản lý thư viện!")
    }

