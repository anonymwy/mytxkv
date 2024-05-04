package com.wuyangnju.mytxkv.cli.impl.cmdhandler

import com.wuyangnju.mytxkv.cli.impl.cmdhandler.ICmdHandler.Companion.KEY_NOT_SET_MSG
import com.wuyangnju.mytxkv.kv.TransactionalKeyValueStoreType
import com.wuyangnju.mytxkv.kv.createTransactionalKeyValueStore
import org.junit.jupiter.api.Assertions.assertFalse
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class GetCmdHandlerTest {
    private lateinit var cmdHandler: ICmdHandler

    @BeforeTest fun beforeTest() {
        cmdHandler = GetCmdHandler(createTransactionalKeyValueStore(TransactionalKeyValueStoreType.InMemory))
    }

    @Test fun testMatches() {
        assertTrue(cmdHandler.matches(listOf("get", "foo")))
        assertFalse(cmdHandler.matches(listOf("foo")))
    }

    @Test fun testValidate() {
        assertTrue(cmdHandler.validate(listOf("get", "foo")))
        assertFalse(cmdHandler.validate(listOf("get")))
    }

    @Test fun testAlertMsg() {
        assertNull(cmdHandler.alertMsg(listOf("get", "foo")))
    }

    @Test fun testProcess() {
        assertEquals(KEY_NOT_SET_MSG, cmdHandler.process(listOf("get", "foo")))
    }
}