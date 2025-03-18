package com.example.interneco.task2.utils

//Singleton
object Menu {
    fun mainMenu() {
        println("\n===== HỆ THỐNG QUẢN LÝ THƯ VIỆN =====")
        println("1. Quản lý sách")
        println("2. Quản lý người dùng")
        println("0. Thoát chương trình")
        print("Nhập lựa chọn của bạn: ")
    }

    fun booksMenu() {
        println("\n===== QUẢN LÝ SÁCH =====")
        println("1. Thêm sách mới")
        println("2. Cập nhật thông tin sách")
        println("3. Tìm kiếm sách theo tên")
        println("5. Hiển thị danh sách sách")
        println("6. Xóa sách")
        println("7. Mượn sách")
        println("8. Trả sách")
        println("9. Số lượng sách đang được mượn")
        println("0. Thoát chương trình")
        print("Nhập lựa chọn của bạn: ")
    }


    fun usersMenu() {
        println("\n===== QUẢN LÝ NGƯỜI DÙNG =====")
        println("1. Thêm người dùng mới")
        println("2. Cập nhật thông tin người dùng")
        println("3. Tìm kiếm người dùng theo tên")
        println("4. Xóa người dùng")
        println("5. Hiển thị danh sách người dùng")
        println("6. Hiển thị sách đã mượn của người dùng")
        println("0. Thoát chương trình")
        print("Nhập lựa chọn của bạn: ")
    }
}