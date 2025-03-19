package com.example.interneco.task2.system.handle

import com.example.interneco.task2.model.User
import com.example.interneco.task2.system.manager.BorrowManager
import com.example.interneco.task2.utils.Menu

/**
 * UserHandler: Lớp xử lý thao tác người dùng trong hệ thống thư viện
 * - Quản lý menu và các chức năng liên quan đến người dùng
 * - Tương tác với BorrowManager để thực hiện các thao tác
 */
class UserHandler(private val library: BorrowManager) {

    fun handleUsersMenu() {
        var inUserMenu = true

        while (inUserMenu) {
            Menu.usersMenu()
            val userChoice = readlnOrNull()?.toIntOrNull()

            when (userChoice) {
                1 -> addUserIntoSystem()
                2 -> updateUser()
                3 -> findUserByName()
                4 -> deleteUser()
                5 -> displayUsers()
                6 -> displayBorrowedBooks()
                0 -> inUserMenu = false
                else -> println("Lựa chọn không hợp lệ!")
            }
        }
    }

    private fun addUserIntoSystem() {
        var isValid: Boolean
        do {
            isValid = true

            print("Nhập tên người dùng: ")
            val name = readlnOrNull()
            print("Nhập email người dùng: ")
            val email = readlnOrNull()

            if (name?.let { isInvalidName(it) } == true) {
                println("Lỗi: Tên không hợp lệ! Tên không được chứa các ký tự đặc biệt như !<>?=+@{}_$%")
                isValid = false
                continue
            }

            if (name.isNullOrBlank() || email.isNullOrBlank() && isInvalidName(name)) {
                println("Lỗi: Vui lòng nhập đầy đủ thông tin!")
                isValid = false
            } else if (email != null) {
                if (!email.matches(Regex("^[^@\\s]+@gmail\\.com\$"))) {
                    println("Lỗi: Email phải có định dạng hợp lệ và kết thúc bằng @gmail.com.")
                    isValid = false
                } else if (library.getAllUsers().any { it.email == email }) {
                    println("Email đã tồn tại! Vui lòng nhập lại.")
                    isValid = false
                }
            }

            if (isValid) {
                val user = User(User.Singleton.generateId(), name!!, email!!)
                library.addUser(user)
                println("Người dùng '${user.name}' đã được thêm thành công!")
            }
        } while (!isValid)
    }


    private fun findUserByName() {
        do {
            print("Nhập tên người dùng: ")
            val userName = readlnOrNull()

            if (userName.isNullOrBlank()) {
                println("Lỗi: Vui lòng nhập đầy đủ thông tin!")
            } else {
                library.findUserByName(userName)
            }
        } while (userName.isNullOrBlank())
    }

    private fun displayUsers() {
        library.displayUsers()
    }

    private fun displayBorrowedBooks() {
        do {
            print("Nhập UserID: ")
            val userID = readlnOrNull()

            if (userID.isNullOrBlank()) {
                println("Lỗi: Vui lòng nhập đầy đủ thông tin!")
            } else {
                println("$userID đang mượn ${library.getUserBorrowedBooksCount(userID)} cuốn!")
                library.displayBorrowedBooks(userID)
            }
        } while (userID.isNullOrBlank())
    }

    private fun updateUser() {
        print("Nhập UserID cần cập nhật: ")
        val userID = readlnOrNull()

        if (userID.isNullOrBlank()) {
            println("Lỗi: ID người dùng không được để trống!")
            return
        }

        val user = library.findUserById(userID)
        if (user == null) {
            println("Không tìm thấy người dùng với ID: $userID")
            return
        }

        println("Thông tin người dùng hiện tại: $user")
        println("Nhập thông tin mới:")

        print("Tên mới: ")
        val newName = readlnOrNull()

        // Kiểm tra tên hợp lệ
        if (!newName.isNullOrBlank() && isInvalidName(newName)) {
            println("Lỗi: Tên không hợp lệ! Tên không được chứa các ký tự đặc biệt như !<>?=+@{}_$%")
            return
        }

        print("Email mới (phải kết thúc bằng @gmail.com): ")
        val newEmail = readlnOrNull()

        // Kiểm tra email hợp lệ
        if (!newEmail.isNullOrBlank()) {
            if (!newEmail.matches(Regex("^[^@\\s]+@gmail\\.com\$"))) {
                println("Lỗi: Email phải có định dạng hợp lệ và kết thúc bằng @gmail.com.")
                return
            }

            // Kiểm tra email đã tồn tại
            if (library.getAllUsers().any { it.id != userID && it.email == newEmail }) {
                println("Email đã tồn tại! Vui lòng nhập lại.")
                return
            }
        }

        //not-null assertion
        library.updateUser(userID, newName!!, newEmail!!)
        println("Cập nhật người dùng thành công!")
    }

    private fun deleteUser() {
        print("Nhập UserID cần xóa: ")
        val userID = readlnOrNull()

        if (userID.isNullOrBlank()) {
            println("Lỗi: ID người dùng không được để trống!")
            return
        }

        val success = library.deleteUser(userID)
        if (success) {
            println("Xóa người dùng thành công!")
        } else {
            println("Không thể xóa người dùng với ID: $userID (Người dùng không tồn tại hoặc đang mượn sách)")
        }
    }


    private fun isInvalidName(name: String): Boolean {
        // Kiểm tra xem tên sau khi cắt khoảng trắng đầu/cuối có rỗng không
        val trimmedName = name.trim()

        // Kiểm tra nếu tên gốc bắt đầu bằng khoảng trắng (tức là có khoảng trắng ở đầu)
        val startsWithSpace = name.startsWith(" ")

        // Kiểm tra nếu tên chứa ký tự đặc biệt
        val containsSpecialChar = trimmedName.contains(Regex("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]+"))

        return trimmedName.isEmpty() || startsWithSpace || containsSpecialChar
    }

}