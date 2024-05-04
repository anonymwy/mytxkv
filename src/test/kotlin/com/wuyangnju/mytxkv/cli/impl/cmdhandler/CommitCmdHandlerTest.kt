package com.wuyangnju.mytxkv.cli.impl.cmdhandler

import com.wuyangnju.mytxkv.cli.impl.cmdhandler.ICmdHandler.Companion.COMMIT_ALERT
import com.wuyangnju.mytxkv.cli.impl.cmdhandler.ICmdHandler.Companion.NO_TRANSACTION
import com.wuyangnju.mytxkv.kv.TransactionalKeyValueStoreType
import com.wuyangnju.mytxkv.kv.createTransactionalKeyValueStore
import org.junit.jupiter.api.Assertions.assertFalse
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CommitCmdHandlerTest {
    private lateinit var cmdHandler: ICmdHandler

    @BeforeTest fun beforeTest() {
        cmdHandler = CommitCmdHandler(createTransactionalKeyValueStore(TransactionalKeyValueStoreType.InMemory))
    }

    @Test fun testMatches() {
        assertTrue(cmdHandler.matches(listOf("commit")))
        assertFalse(cmdHandler.matches(listOf("foo")))
    }

    @Test fun testValidate() {
        assertTrue(cmdHandler.validate(listOf("commit")))
        assertFalse(cmdHandler.validate(listOf("commit", "foo")))
    }

    @Test fun testAlertMsg() {
        assertEquals(COMMIT_ALERT, cmdHandler.alertMsg(listOf("commit")))
    }

    @Test fun testProcess() {
        assertEquals(NO_TRANSACTION, cmdHandler.process(listOf("commit")))
    }
}