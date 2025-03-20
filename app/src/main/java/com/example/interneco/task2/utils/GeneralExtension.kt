package com.example.interneco.task2.utils

/**
 * Hàm mở rộng inline để hiển thị tất cả các phần tử trong danh sách.
 * @param info Hàm để trích xuất thông tin cần hiển thị từ phần tử trong danh sách
 */
inline fun <T> MutableList<T>.displayAll(info: (T) -> String) {
    this.forEach {
        println(info(it))
    }
}