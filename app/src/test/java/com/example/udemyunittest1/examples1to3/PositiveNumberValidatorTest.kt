package com.example.udemyunittest1.examples1to3

import com.example.udemyunittest1.examples1to3.examples1to3.PositiveNumberValidator
import org.junit.After
import org.junit.Before

import org.hamcrest.CoreMatchers.`is`

import org.junit.Assert.*
import org.junit.Test

class PositiveNumberValidatorTest {

    lateinit var SUT: PositiveNumberValidator

    @Before
    fun setUp() {
        SUT = PositiveNumberValidator()
    }

    @Test
    fun checkNegativeNumber_isPositive_isFalse() {
        val result = SUT.isPositive(-1)
        assertThat(result, `is`(false))
    }

    @Test
    fun checkZeroNumber_isPositive_isFalse() {
        val result = SUT.isPositive(0)
        assertThat(result, `is`(false))
    }

    @Test
    fun checkForPositiveNumber_isPositive_isTrue() {
        val result = SUT.isPositive(1)
        assertThat(result, `is`(true))
    }


    @After
    fun tearDown() {
    }
}