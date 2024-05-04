package com.wuyangnju.mytxkv.cli.impl.cmdhandler

import com.wuyangnju.mytxkv.cli.impl.cmdhandler.ICmdHandler.Companion.NO_TRANSACTION
import com.wuyangnju.mytxkv.cli.impl.cmdhandler.ICmdHandler.Companion.ROLLBACK_ALERT
import com.wuyangnju.mytxkv.kv.TransactionalKeyValueStoreType
import com.wuyangnju.mytxkv.kv.createTransactionalKeyValueStore
import org.junit.jupiter.api.Assertions.assertFalse
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RollbackCmdHandlerTest {
    private lateinit var cmdHandler: ICmdHandler

    @BeforeTest fun beforeTest() {
        cmdHandler = RollbackCmdHandler(createTransactionalKeyValueStore(TransactionalKeyValueStoreType.InMemory))
    }

    @Test fun testMatches() {
        assertTrue(cmdHandler.matches(listOf("rollback")))
        assertFalse(cmdHandler.matches(listOf("foo")))
    }

    @Test fun testValidate() {
        assertTrue(cmdHandler.validate(listOf("rollback")))
        assertFalse(cmdHandler.validate(listOf("rollback", "foo")))
    }

    @Test fun testAlertMsg() {
        assertEquals(ROLLBACK_ALERT, cmdHandler.alertMsg(listOf("rollback")))
    }

    @Test fun testProcess() {
        assertEquals(NO_TRANSACTION, cmdHandler.process(listOf("rollback")))
    }
}