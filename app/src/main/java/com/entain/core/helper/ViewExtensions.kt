package com.entain.core.helper

fun getDateTime(time: Long): String {
    val secMilSec: Long = 1000
    val minMilSec = 60 * secMilSec
    val hourMilSec = 60 * minMilSec
    val dayMilSec = 24 * hourMilSec
    val minutes = (time % dayMilSec % hourMilSec / minMilSec).toInt()
    val seconds = (time % dayMilSec % hourMilSec % minMilSec / secMilSec).toInt()

    return String.format("%02dm%02ds", minutes, seconds)
}

fun diffTime(differentInMil: Long): Long {
    var min: Long = 0
    val difference: Long
    try {
        difference = (differentInMil) / 1000
        val hours = difference % (24 * 3600) / 3600 // Calculating Hours
        val minute = difference % 3600 / 60 // Calculating minutes if there is any minutes difference
        min = minute + hours * 60 // This will be our final minutes. Multiplying by 60 as 1 hour contains 60 mins
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    return min
}


