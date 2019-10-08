package com.example.udemyunittest1.examples1to3.examples1to3

class Interval (private var mStart: Int,
                private var mEnd: Int) {

    fun Interval(start: Int, end: Int) {
        require(start < end) { "invalid interval range" }
        mStart = start
        mEnd = end
    }

    fun getStart(): Int {
        return mStart
    }

    fun getEnd(): Int {
        return mEnd
    }
}