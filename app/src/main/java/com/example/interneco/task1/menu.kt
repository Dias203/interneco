package com.example.interneco.task1

import com.example.interneco.task1.StudentData.listStudent

object Menu {
    fun mainMenu() {
        var input: Int
        do {
            // Hiển thị menu cho người dùng
            println("Nhập lựa chọn của bạn:")
            println("1. Hiển thị danh sách sinh viên")
            println("2. Hiển thị danh sách sinh viên có GPA >= 8")
            println("3. Phân loại theo tuổi")
            println("4. Tổng GPA sinh viên")
            println("5 -> Nhập sinh viên từ bàn phím")
            println("6 -> Sắp xếp sinh viên theo GPA")
            println("7 -> Số lượng sinh viên theo loại học lực")
            println("Nhấn phím khác để thoát")

            // Đọc giá trị từ bàn phím
            val userInput = readlnOrNull()
            input = userInput?.toIntOrNull() ?: -1 // nếu null thì giá trị input sẽ là -1

            // Xử lý từng trường hợp
            when (input) {
                1 -> {
                    Handler.displayStudentList()
                }

                2 -> {
                    Handler.displayGpaMoreThan8()
                }

                3 -> {
                    Handler.displayAgeClassification()
                }

                4 -> {
                    Handler.sumOfGpa()
                }

                5 -> {
                    Handler.addStudent()
                }

                6 -> {
                    Handler.arrangeStudent()
                }
                7 -> Handler.studentClassification()
                else -> {
                    println("Thoát chương trình")
                }
            }
        } while (input in 1..7)
    }
}