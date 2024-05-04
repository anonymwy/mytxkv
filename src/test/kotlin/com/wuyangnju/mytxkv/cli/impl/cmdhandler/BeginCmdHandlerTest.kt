package com.wuyangnju.mytxkv.cli.impl.cmdhandler

import com.wuyangnju.mytxkv.kv.TransactionalKeyValueStoreType
import com.wuyangnju.mytxkv.kv.createTransactionalKeyValueStore
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNull
import kotlin.test.assertTrue

class BeginCmdHandlerTest {
    private lateinit var cmdHandler: ICmdHandler

    @BeforeTest fun beforeTest() {
        cmdHandler = BeginCmdHandler(createTransactionalKeyValueStore(TransactionalKeyValueStoreType.InMemory))
    }

    @Test fun testMatches() {
        assertTrue(cmdHandler.matches(listOf("begin")))
        assertFalse(cmdHandler.matches(listOf("foo")))
    }

    @Test fun testValidate() {
        assertTrue(cmdHandler.validate(listOf("begin")))
        assertFalse(cmdHandler.validate(listOf("begin", "foo")))
    }

    @Test fun testAlertMsg() {
        assertNull(cmdHandler.alertMsg(listOf("begin")))
    }

    @Test fun testProcess() {
        assertDoesNotThrow { cmdHandler.process(listOf("begin")) }
    }
}