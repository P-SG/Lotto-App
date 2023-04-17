package com.psg.data.utils

import android.annotation.SuppressLint
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.toCalDate() = runCatching {
    val sdf = SimpleDateFormat("yyyy-MM-dd 00:00:00")
    val date = sdf.parse("$this 00:00:00")
    val today = Calendar.getInstance()
    val calDate = (today.time.time - date.time) / (60 * 60 * 24 * 1000)
    Timber.d("날짜차이:${(calDate / 7)}주지남")
    return@runCatching (calDate / 7).toInt()
}.getOrThrow()


@SuppressLint("SimpleDateFormat")
fun LocalDate.toCalDate() = runCatching {
    val sdf = SimpleDateFormat("yyyy-MM-dd 00:00:00")
    val date = sdf.parse("$this 00:00:00")
    val today = Calendar.getInstance()
    val calDate = (today.time.time - date.time) / (60 * 60 * 24 * 1000)
    Timber.d("날짜차이:${(calDate / 7)}주지남")
    return@runCatching (calDate / 7).toInt()
}.getOrThrow()