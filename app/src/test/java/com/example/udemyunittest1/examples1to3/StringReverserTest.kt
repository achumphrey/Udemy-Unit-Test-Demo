package com.example.udemyunittest1.examples1to3

import com.example.udemyunittest1.examples1to3.examples1to3.StringReverser
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class StringReverserTest {

    lateinit var SUT: StringReverser

    @Before
    @Throws(Exception::class)
    fun setup() {
        SUT = StringReverser()
    }

    @Test
    @Throws(Exception::class)
    fun reverse_emptyString_emptyStringReturned() {
        val result = SUT.reverse("")
        assertThat<String>(result, `is`<String>(""))
    }

    @Test
    @Throws(Exception::class)
    fun reverse_singleCharacter_sameStringReturned() {
        val result = SUT.reverse("a")
        assertThat<String>(result, `is`<String>("a"))
    }

    @Test
    @Throws(Exception::class)
    fun reverse_longString_reversedStringReturned() {
        val result = SUT.reverse("Vasiliy Zukanov")
        assertThat<String>(result, `is`<String>("vonakuZ yilisaV"))
    }

    @After
    fun tearDown() {
    }
}