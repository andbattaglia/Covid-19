package com.battagliandrea.domain.ext

import java.text.SimpleDateFormat
import java.util.*

/**
 * Pattern: dd/MM/yyyy
 */
fun Date.ddMMyyyy(): String{
    val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: yyyy-MM-ddThh:mm:ss
 */
fun String.parseDate(): Date{
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.parse(this)
}