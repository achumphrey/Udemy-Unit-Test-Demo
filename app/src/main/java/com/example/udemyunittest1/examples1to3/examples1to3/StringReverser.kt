package com.example.udemyunittest1.examples1to3.examples1to3

class StringReverser {

    fun reverse(string: String): String {
        val sb = StringBuilder()
        for (i in string.length - 1 downTo 0) {
            sb.append(string[i])
        }
        return sb.toString()
    }
}