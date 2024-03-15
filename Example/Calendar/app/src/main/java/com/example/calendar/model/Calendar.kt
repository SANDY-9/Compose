package com.example.calendar.model

import java.time.LocalDate

data class Calendar(
    val year: Int = LocalDate.now().year,
    val month: Int = LocalDate.now().monthValue,
    val weeks: List<List<Date>> = emptyList()
)