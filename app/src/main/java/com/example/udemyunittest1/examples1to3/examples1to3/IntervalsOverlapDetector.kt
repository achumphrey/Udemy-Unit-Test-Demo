package com.example.udemyunittest1.examples1to3.examples1to3

class IntervalsOverlapDetector {

    fun isOverlap(interval1: Interval, interval2: Interval): Boolean {
        return interval1.getEnd() > interval2.getStart() && interval1.getStart() < interval2.getEnd()
    }
}