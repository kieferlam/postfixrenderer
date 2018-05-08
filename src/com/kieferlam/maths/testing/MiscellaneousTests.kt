package com.kieferlam.maths.testing

import com.kieferlam.postfix.syntax.Operator
import com.kieferlam.postfix.syntax.operator.PlusOperator
import org.junit.Test
import kotlin.test.assertEquals

class MiscTests {

    @Test
    fun classTest() {
        assertEquals(Operator.javaClass, Operator.javaClass)
        assertEquals(Operator.PLUS.javaClass, PlusOperator::class.java)
    }

}