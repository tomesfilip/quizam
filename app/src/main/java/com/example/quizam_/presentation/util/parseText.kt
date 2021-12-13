package com.example.quizam_.presentation.util

import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY

fun parseText(txt: String): String {
    return Html.fromHtml(txt, FROM_HTML_MODE_LEGACY).toString()
}