package com.example.note.core.utils

import com.example.note.presentation.screen.setting.SortOrder

fun SortOrder.nameToDisplay(): String {
    return when (this) {
        SortOrder.DATE_DESC -> "جدیدترین"
        SortOrder.DATE_ASC -> "تاریخ"
        SortOrder.TITLE -> "نام"
    }
}