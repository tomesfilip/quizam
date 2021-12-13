package com.example.quizam_.presentation.util

fun parseText(txt: String): String {
    var parsedTxt: String = txt
    parsedTxt = parsedTxt
        .replace("""&quot;|&#039;|&rdquo;|&ldquo;|&rsquo;|&lsquo;""".toRegex(), "'")
    parsedTxt = parsedTxt.replace("""&amp;""".toRegex(), "&")
    parsedTxt = parsedTxt.replace("""&hellip;""".toRegex(), "...")
    parsedTxt = parsedTxt.replace("""&ntilde;""".toRegex(), "ñ")
    parsedTxt = parsedTxt.replace("""&ntilde;""".toRegex(), "á")

    return parsedTxt
}