package com.example.interneco.task2.system

import com.example.interneco.task2.model.User

class UserManager {
    private val users = mutableListOf<User>()

    // Thêm người dùng
    fun addUser(user: User) {
        users.add(user)
        println("Thêm người dùng thành công!")
    }

    // Lấy tất cả người dùng
    fun getAllUsers(): List<User> {
        return users
    }

    // Hiển thị danh sách người dùng
    fun displayUsers() {
        println("=== Danh sách người dùng (${users.size} người) ===")
        if (users.isEmpty()) {
            println("Không có người dùng nào!")
            return
        }
        users.forEachIndexed { index: Int, user: User ->
            println("${index + 1} - $user")
        }
    }

    // Tìm người dùng theo ID (nullable)
    fun findUserById(userId: String): User? {
        return users.find { it.id == userId }
    }

    // Tìm người dùng theo tên
    fun findUserByName(userName: String): List<User> {
        val result = users.filter {
            it.name.contains(userName, ignoreCase = true)
        }
        if (result.isEmpty()) {
            println("Không tìm thấy kết quả nào!")
        } else {
            result.forEachIndexed { index, user -> println("${index + 1} - $user") }
        }
        return result
    }
}