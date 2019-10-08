package com.example.udemyunittest1.examples1to3

import com.example.udemyunittest1.examples1to3.examples1to3.Interval
import com.example.udemyunittest1.examples1to3.examples1to3.IntervalsOverlapDetector
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class IntervalsOverlapDetectorTest {

    lateinit var SUT: IntervalsOverlapDetector

    @Before
    @Throws(Exception::class)
    fun setup() {
        SUT = IntervalsOverlapDetector()
    }

    // interval1 is before interval2

    @Test
    @Throws(Exception::class)
    fun isOverlap_interval1BeforeInterval2_falseReturned() {
        val interval1 = Interval(-1, 5)
        val interval2 = Interval(8, 12)
        val result = SUT.isOverlap(interval1, interval2)
        assertThat(result, `is`<Boolean>(false))
    }

    // interval1 overlaps interval2 on start

    @Test
    @Throws(Exception::class)
    fun isOverlap_interval1OverlapsInterval2OnStart_trueReturned() {
        val interval1 = Interval(-1, 5)
        val interval2 = Interval(3, 12)
        val result = SUT.isOverlap(interval1, interval2)
        assertThat(result, `is`<Boolean>(true))
    }
    // interval1 is contained within interval2

    @Test
    @Throws(Exception::class)
    fun isOverlap_interval1ContainedWithinInterval2_trueReturned() {
        val interval1 = Interval(-1, 5)
        val interval2 = Interval(-4, 12)
        val result = SUT.isOverlap(interval1, interval2)
        assertThat(result, `is`<Boolean>(true))
    }
    // interval1 contains interval2

    @Test
    @Throws(Exception::class)
    fun isOverlap_interval1ContainsInterval2_trueReturned() {
        val interval1 = Interval(-1, 5)
        val interval2 = Interval(0, 3)
        val result = SUT.isOverlap(interval1, interval2)
        assertThat(result, `is`<Boolean>(true))
    }
    // interval1 overlaps interval2 on end

    @Test
    @Throws(Exception::class)
    fun isOverlap_interval1OverlapsInterval2OnEnd_trueReturned() {
        val interval1 = Interval(-1, 5)
        val interval2 = Interval(-4, 4)
        val result = SUT.isOverlap(interval1, interval2)
        assertThat(result, `is`<Boolean>(true))
    }
    // interval1 is after interval2

    @Test
    @Throws(Exception::class)
    fun isOverlap_interval1AfterInterval2_falseReturned() {
        val interval1 = Interval(-1, 5)
        val interval2 = Interval(-10, -3)
        val result = SUT.isOverlap(interval1, interval2)
        assertThat(result, `is`<Boolean>(false))
    }

    @Test
    @Throws(Exception::class)
    fun isOverlap_interval1BeforeAdjacentInterval2_falseReturned() {
        val interval1 = Interval(-1, 5)
        val interval2 = Interval(5, 8)
        val result = SUT.isOverlap(interval1, interval2)
        assertThat(result, `is`<Boolean>(false))
    }

    @Test
    @Throws(Exception::class)
    fun isOverlap_interval1AfterAdjacentInterval2_falseReturned() {
        val interval1 = Interval(-1, 5)
        val interval2 = Interval(-3, -1)
        val result = SUT.isOverlap(interval1, interval2)
        assertThat(result, `is`<Boolean>(false))
    }

    @After
    fun tearDown() {
    }
}