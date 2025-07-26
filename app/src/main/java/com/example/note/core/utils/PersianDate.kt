package com.example.note.core.utils

import java.time.LocalDate

class PersianDate {

    fun gregorianToJalali(gy: Int, gm: Int, gd: Int): Triple<Int, Int, Int> {
        val g_d_m = intArrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        val gy = gy - 1600
        val gm = gm - 1
        val gd = gd - 1

        var g_day_no = 365 * gy + (gy + 3) / 4 - (gy + 99) / 100 + (gy + 399) / 400
        for (i in 0 until gm) g_day_no += g_d_m[i]
        if (gm > 1 && ((gy + 1600) % 4 == 0 && (gy + 1600) % 100 != 0 || (gy + 1600) % 400 == 0)) {
            g_day_no++
        }

        g_day_no += gd

        var j_day_no = g_day_no - 79

        val j_np = j_day_no / 12053
        j_day_no %= 12053

        var jy = 979 + 33 * j_np + 4 * (j_day_no / 1461)
        j_day_no %= 1461

        if (j_day_no >= 366) {
            jy += (j_day_no - 1) / 365
            j_day_no = (j_day_no - 1) % 365
        }

        val j_days_in_month = intArrayOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29)
        var jm = 0
        var i = 0
        while (i < 12 && j_day_no >= j_days_in_month[i]) {
            j_day_no -= j_days_in_month[i]
            i++
        }
        jm = i + 1
        val jd = j_day_no + 1

        return Triple(jy, jm, jd)
    }

    fun getTodayPersianDate(): String {
        val date = LocalDate.now()
        val (jy, jm, jd) = gregorianToJalali(date.year, date.monthValue, date.dayOfMonth)
        return "$jy/$jm/$jd"
    }

}


