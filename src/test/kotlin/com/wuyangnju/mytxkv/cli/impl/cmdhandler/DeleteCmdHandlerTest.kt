package com.wuyangnju.mytxkv.cli.impl.cmdhandler

import com.wuyangnju.mytxkv.cli.impl.cmdhandler.ICmdHandler.Companion.DELETE_ALERT
import com.wuyangnju.mytxkv.kv.TransactionalKeyValueStoreType
import com.wuyangnju.mytxkv.kv.createTransactionalKeyValueStore
import org.junit.jupiter.api.Assertions.assertFalse
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class DeleteCmdHandlerTest {
    private lateinit var cmdHandler: ICmdHandler

    @BeforeTest fun beforeTest() {
        cmdHandler = DeleteCmdHandler(createTransactionalKeyValueStore(TransactionalKeyValueStoreType.InMemory))
    }

    @Test fun testMatches() {
        assertTrue(cmdHandler.matches(listOf("delete", "foo")))
        assertFalse(cmdHandler.matches(listOf("foo")))
    }

    @Test fun testValidate() {
        assertTrue(cmdHandler.validate(listOf("delete", "foo")))
        assertFalse(cmdHandler.validate(listOf("delete")))
    }

    @Test fun testAlertMsg() {
        assertEquals(DELETE_ALERT.format("foo"), cmdHandler.alertMsg(listOf("delete", "foo")))
    }

    @Test fun testProcess() {
        assertNull(cmdHandler.process(listOf("delete", "foo")))
    }
}