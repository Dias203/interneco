package com.example.interneco.task1

import com.example.interneco.task1.StudentData.listStudent

fun displayStudentList() {
    for (item in listStudent) {
        println(item.displayInfo())
    }
}
fun displayGpaMoreThan8() {
    val studentScholarship = listStudent.filter { it.gpa >= 8.0 }
    studentScholarship.forEach { println(it.displayInfo()) }
}

fun displayAgeClassification() {
    listStudent.forEach { student: Student ->
        when {
            student.age < 18 -> println("ID: ${student.id}, Name: ${student.name}, Age: ${student.age} - Vị thành niên")
            student.age <= 22 -> println("ID: ${student.id}, Name: ${student.name}, Age: ${student.age} - Sinh viên")
            else -> println("ID: ${student.id}, Name: ${student.name}, Age: ${student.age} - Đã tốt nghiệp hoặc đi làm")
        }
    }
}

fun enterNewGpaForStudent() {
    println("Nhập điểm hợp lệ cho từng sinh viên")
    listStudent.forEach { student: Student ->
        var newGpa: Float
        do {
            println("Nhập điểm cho từng sinh viên")
            val input = readlnOrNull()
            newGpa = input?.toFloat()!!
            if(newGpa !in 0f..10f) {
                println("Lỗi, yêu cầu nhập lại")
            }
        } while (newGpa !in 0f..10f)
        student.gpa = newGpa
    }
}

fun sumOfGpa(){
    val sumGpa = listStudent.sumOf { it.gpa.toDouble() }
    println("Tổng điểm GPA của tất cả sinh viên là: $sumGpa")
}

fun displayIdAndName() {
    for (item in listStudent) {
        println("ID: ${item.id} - Name: ${item.name}")
    }

    // or
    /*
    val studentToName = listStudent.associate { it.id to it.name } // return a map
    studentToName.forEach { (id, name) ->
        println("ID: $id - Name: $name")
    }

     */
}

fun displayFirstAndLast() {
    val firstStudent = listStudent.first()
    val lastStudent = listStudent.last()
    println("First Student: ${firstStudent.displayInfo()}")
    println("Last Student: ${lastStudent.displayInfo()}")
}

fun menu() {
    var input: Int
    do {
        // Hiển thị menu cho người dùng
        println("Nhập lựa chọn của bạn:")
        println("1 -> Nhập sinh viên từ bàn phím")
        println("2 -> Sắp xếp sinh viên theo GPA")
        println("3 -> Số lượng sinh viên theo loại học lực")
        println("Nhấn phím khác để thoát")

        // Đọc giá trị từ bàn phím
        val userInput = readlnOrNull()
        input = userInput?.toIntOrNull() ?: -1 // nếu null thì giá trị input sẽ là -1

        // Xử lý từng trường hợp
        when (input) {
            1 -> {
                println("Nhập thông tin sinh viên mới:")
                var id : Int
                do {
                    print("ID: ")
                    id = readlnOrNull()?.toIntOrNull() ?: -1
                    if (id == -1 || listStudent.any { it.id == id }) {
                        println("ID không hợp lệ hoặc đã tồn tại, vui lòng thử lại.")
                    } else {
                        break // Thoát vòng lặp nếu id hợp lệ
                    }
                } while (true)
                print("Tên: ")
                val name = readlnOrNull()
                print("Tuổi: ")
                val age = readlnOrNull()?.toInt()!!
                print("GPA: ")
                val gpa = readlnOrNull()?.toFloat()!!
                print("Gender: ")
                val gender = readlnOrNull()!!.toCharArray()[0]
                val scholarship = gpa >= 8.0

                if (listStudent.none{it.id == id}  && age > 0) {
                    name?.let { Student(id, it, age, gpa, gender, scholarship) }?.let { listStudent.add(it) }
                    println("Thêm sinh viên thành công!")
                } else {
                    println("Dữ liệu nhập không hợp lệ hoặc ID đã tồn tại, vui lòng thử lại.")
                }
            }

            2 -> {
                println("Sắp xếp sinh viên theo GPA:")
                val sortedStudentGpa = listStudent.sortedBy { it.gpa }
                sortedStudentGpa.forEach { println(it.displayInfo()) }
            }
            3 -> {
                println("Số lượng sinh viên theo loại học lực:")
                var kem = 0
                var yeu = 0
                var tb = 0
                var tbk = 0
                var kha = 0
                var gioi = 0
                var xuatsac = 0
                for (student in listStudent) {
                    when {
                        student.gpa < 4 -> kem++
                        student.gpa < 5 -> yeu++
                        student.gpa < 6 -> tb++
                        student.gpa < 7 -> tbk++
                        student.gpa < 8 -> kha++
                        student.gpa < 9 -> gioi++
                        else -> xuatsac++
                    }
                }
                println("Kém: $kem, Yếu: $yeu, Trung bình: $tb, Trung bình khá: $tbk, Khá: $kha, Giỏi: $gioi, Xuất sắc: $xuatsac")

            }
            else -> {
                println("Thoát chương trình")
            }
        }
    } while (input in 1..3)
}





fun main() {

    /*//2
    displayGpaMoreThan8()

    //3
    displayAgeClassification()


    //4

    println("Trước khi nhập điểm!")
    displayStudentList()

    enterNewGpaForStudent()

    println("Sau khi nhập điểm!")
    displayStudentList()


    //5
    sumOfGpa()
    displayIdAndName()
    displayFirstAndLast()


    //7
    displayStudentList()*/

    menu()


}