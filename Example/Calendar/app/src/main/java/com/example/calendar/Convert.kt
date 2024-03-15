package com.example.calendar


fun Int.toHour(): String {
    return if(this < 10) "0$this" else "$this"
}


fun Int.toMonth(): String {
    return if(this < 10) "0$this" else "$this"
}

fun Int.toDayOfWeekName(): String {
    return when(this) {
        1 -> "월"
        2 -> "화"
        3 -> "수"
        4 -> "목"
        5 -> "금"
        6 -> "토"
        7 -> "일"
        else -> ""
    }
}
