package com.example.interneco.task2
import com.example.interneco.task2.system.handle.BookHandler
import com.example.interneco.task2.system.manager.BorrowManager
import com.example.interneco.task2.system.handle.UserHandler
import com.example.interneco.task2.utils.Menu

fun main() {
    val library = BorrowManager()
    val bookHandler = BookHandler(library)
    val userHandler = UserHandler(library)

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