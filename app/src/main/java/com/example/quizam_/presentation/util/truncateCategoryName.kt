package com.example.quizam_.presentation.util

fun truncateCategoryName(name: String): String {
    return if (!name.contains(":")) name else name.split(":")[1]
}