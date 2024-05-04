package com.wuyangnju.mytxkv.cli.impl.cmdhandler

import com.wuyangnju.mytxkv.kv.TransactionalKeyValueStoreType
import com.wuyangnju.mytxkv.kv.createTransactionalKeyValueStore
import org.junit.jupiter.api.Assertions.assertFalse
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNull
import kotlin.test.assertTrue

class SetCmdHandlerTest {
    private lateinit var cmdHandler: ICmdHandler

    @BeforeTest fun beforeTest() {
        cmdHandler = SetCmdHandler(createTransactionalKeyValueStore(TransactionalKeyValueStoreType.InMemory))
    }

    @Test fun testMatches() {
        assertTrue(cmdHandler.matches(listOf("set", "foo", "bar")))
        assertFalse(cmdHandler.matches(listOf("foo")))
    }

    @Test fun testValidate() {
        assertTrue(cmdHandler.validate(listOf("set", "foo", "bar")))
        assertFalse(cmdHandler.validate(listOf("set")))
    }

    @Test fun testAlertMsg() {
        assertNull(cmdHandler.alertMsg(listOf("set", "foo", "bar")))
    }

    @Test fun testProcess() {
        assertNull(cmdHandler.process(listOf("delete", "foo", "bar")))
    }
}