package com.example.interneco.task2.system.manager

import com.example.interneco.task2.model.User
/**
 * Lớp quản lý người dùng.
 * Cung cấp các phương thức cho các hoạt động quản lý người dùng.
 * Chức năng chính:
 * 1. Thêm người dùng
 * 2. Cập nhật thông tin người dùng
 * 3. Xóa người dùng
 * 4. Hiển thị danh sách người dùng
 * 6. Tìm kiếm người dùng theo tên người dùng
 */
class UserManager {
    private val users = mutableListOf<User>()

    // Thêm người dùng
    fun addUser(user: User) {
        users.add(user)
        println("Thêm người dùng thành công!")
    }

    // Cập nhật thông tin người dùng
    fun updateUser(userId: String, userNewName: String, userNewEmail: String) {
        val user = findUserById(userId)

        if (user == null) {
            println("Không tìm thấy người dùng với ID: $userId")
            return
        }

        // Cập nhật thông tin
        user.name = userNewName
        user.email = userNewEmail

        println("Cập nhật thông tin người dùng thành công!")
        println("Thông tin người dùng sau khi cập nhật: $user")
    }

    // Xóa người dùng
    fun deleteUser(userId: String): Boolean {
        val user = findUserById(userId)

        if (user == null) {
            println("Không tìm thấy người dùng với ID: $userId")
            return false
        }

        users.remove(user)
        println("Đã xóa người dùng có ID: $userId thành công!")
        return true
    }

    // Lấy tất cả người dùng
    fun getAllUsers(): List<User> {
        return users.toList()
    }

    // Hiển thị danh sách người dùng
    fun displayUsers() {
        println("=== Danh sách người dùng (${users.size} người) ===")
        if (users.isEmpty()) {
            println("Không có người dùng nào!")
            return
        }

        users.forEachIndexed { index, user ->
            println("${index + 1} - $user")
        }
    }

    // Tìm người dùng theo ID
    fun findUserById(userId: String): User? {
        return users.find { it.id == userId }
    }

    // Tìm người dùng theo tên
    fun findUserByName(userName: String): List<User> {
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
}