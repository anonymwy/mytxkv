package com.wuyangnju.mytxkv.kv.impl

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class TransactionFrameTest {
    private lateinit var transactionFrame: ITransactionFrame

    @BeforeTest fun beforeTest() {
        transactionFrame = InMemTransactionFrameImpl()
    }

    @Test fun `Set and get a value`() {
        transactionFrame.set("foo", "123")
        assertEquals("123", transactionFrame.get("foo"))
    }

    @Test fun `Delete a value`() {
        transactionFrame.delete("foo")
        assertNull(transactionFrame.get("foo"))
    }

    @Test fun `Count the number of occurrences of a value`() {
        transactionFrame.set("foo", "123")
        transactionFrame.set("bar", "456")
        transactionFrame.set("baz", "123")
        assertEquals(2, transactionFrame.count("123"))
        assertEquals(1, transactionFrame.count("456"))
    }
}