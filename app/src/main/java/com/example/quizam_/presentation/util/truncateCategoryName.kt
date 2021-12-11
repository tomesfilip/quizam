package com.example.quizam_.presentation.util

import android.text.TextUtils.replace

fun truncateCategoryName(name: String): String {
    return if (!name.contains(":")) name else name.split(":")[1]
}