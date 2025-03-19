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
interface UserManager {
    val users: MutableList<User>

    // Thêm người dùng
    fun addUser(user: User)

    // Cập nhật thông tin người dùng
    fun updateUser(userId: String, userNewName: String, userNewEmail: String)

    // Xóa người dùng
    fun deleteUser(userId: String) : Boolean

    // Lấy tất cả người dùng
    fun getAllUsers(): List<User>

    // Hiển thị danh sách người dùng
    fun displayUsers()

    // Tìm người dùng theo ID
    fun findUserById(userId: String): User?

    // Tìm người dùng theo tên
    fun findUserByName(userName: String): List<User>

}