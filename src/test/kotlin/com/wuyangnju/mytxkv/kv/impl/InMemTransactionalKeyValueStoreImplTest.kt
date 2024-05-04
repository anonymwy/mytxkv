package com.wuyangnju.mytxkv.kv.impl

import com.wuyangnju.mytxkv.kv.ITransactionalKeyValueStore
import com.wuyangnju.mytxkv.kv.TransactionalKeyValueStoreType
import com.wuyangnju.mytxkv.kv.createTransactionalKeyValueStore
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class InMemTransactionalKeyValueStoreImplTest {
    private lateinit var transactionalKeyValueStore: ITransactionalKeyValueStore

    @BeforeTest fun beforeTest() {
        transactionalKeyValueStore = createTransactionalKeyValueStore(TransactionalKeyValueStoreType.InMemory)
    }

    @Test fun `Set and get a value`() {
        transactionalKeyValueStore.set("foo", "123")
        assertEquals("123", transactionalKeyValueStore.get("foo"))
    }

    @Test fun `Delete a value`() {
        transactionalKeyValueStore.delete("foo")
        assertNull(transactionalKeyValueStore.get("foo"))
    }

    @Test fun `Count the number of occurrences of a value`() {
        transactionalKeyValueStore.set("foo", "123")
        transactionalKeyValueStore.set("bar", "456")
        transactionalKeyValueStore.set("baz", "123")
        assertEquals(2, transactionalKeyValueStore.count("123"))
        assertEquals(1, transactionalKeyValueStore.count("456"))
    }

    @Test fun `Commit a transaction`() {
        transactionalKeyValueStore.set("bar", "123")
        assertEquals("123", transactionalKeyValueStore.get("bar"))
        transactionalKeyValueStore.begin()
        transactionalKeyValueStore.set("foo", "456")
        assertEquals("123", transactionalKeyValueStore.get("bar"))
        transactionalKeyValueStore.delete("bar")
        assertTrue(transactionalKeyValueStore.commit())
        assertNull(transactionalKeyValueStore.get("bar"))
        assertFalse(transactionalKeyValueStore.rollback())
        assertEquals("456", transactionalKeyValueStore.get("foo"))
    }

    @Test fun `Rollback a transaction`() {
        transactionalKeyValueStore.set("foo", "123")
        transactionalKeyValueStore.set("bar", "abc")
        transactionalKeyValueStore.begin()
        transactionalKeyValueStore.set("foo", "456")
        assertEquals("456", transactionalKeyValueStore.get("foo"))
        transactionalKeyValueStore.set("bar", "def")
        assertEquals("def", transactionalKeyValueStore.get("bar"))
        assertTrue(transactionalKeyValueStore.rollback())
        assertEquals("123", transactionalKeyValueStore.get("foo"))
        assertEquals("abc", transactionalKeyValueStore.get("bar"))
        assertFalse(transactionalKeyValueStore.commit())
    }

    @Test fun `Nested transactions`() {
        transactionalKeyValueStore.set("foo", "123")
        transactionalKeyValueStore.set("bar", "456")
        transactionalKeyValueStore.begin()
        transactionalKeyValueStore.set("foo", "456")
        transactionalKeyValueStore.begin()
        assertEquals(2, transactionalKeyValueStore.count("456"))
        assertEquals("456", transactionalKeyValueStore.get("foo"))
        transactionalKeyValueStore.set("foo", "789")
        assertEquals("789", transactionalKeyValueStore.get("foo"))
        assertTrue(transactionalKeyValueStore.rollback())
        assertEquals("456", transactionalKeyValueStore.get("foo"))
        transactionalKeyValueStore.delete("foo")
        assertNull(transactionalKeyValueStore.get("foo"))
        assertTrue(transactionalKeyValueStore.rollback())
        assertEquals("123", transactionalKeyValueStore.get("foo"))
    }
}