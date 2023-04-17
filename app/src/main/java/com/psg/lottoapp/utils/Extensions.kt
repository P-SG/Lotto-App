package com.psg.lottoapp.utils

import com.psg.lottoapp.R

fun Int?.toColor(): Int {
    return when (this) {
        in 1..10 -> R.color.num1
        in 11..20 -> R.color.num2
        in 21..30 -> R.color.num3
        in 31..40 -> R.color.num4
        in 41..45 -> R.color.num5
        else -> R.color.black
    }
}

fun String.toText() = this.replace("-", ".")

