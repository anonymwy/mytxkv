package com.wuyangnju.mytxkv.cli.impl

import com.wuyangnju.mytxkv.cli.impl.cmdhandler.BeginCmdHandler
import com.wuyangnju.mytxkv.cli.impl.cmdhandler.CommitCmdHandler
import com.wuyangnju.mytxkv.cli.impl.cmdhandler.CountCmdHandler
import com.wuyangnju.mytxkv.cli.impl.cmdhandler.DeleteCmdHandler
import com.wuyangnju.mytxkv.cli.impl.cmdhandler.GetCmdHandler
import com.wuyangnju.mytxkv.cli.impl.cmdhandler.RollbackCmdHandler
import com.wuyangnju.mytxkv.cli.impl.cmdhandler.SetCmdHandler
import com.wuyangnju.mytxkv.kv.TransactionalKeyValueStoreType
import com.wuyangnju.mytxkv.kv.createTransactionalKeyValueStore
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class CmdDispatcherTest {
    private lateinit var cmdDispatcher: ICmdDispatcher

    @BeforeTest fun beforeTest() {
        cmdDispatcher = createDefaultCmdDispatcher(createTransactionalKeyValueStore(TransactionalKeyValueStoreType.InMemory))
    }

    @Test fun testDispatch() {
        val params = mapOf(
            listOf("set", "k", "v") to SetCmdHandler::class.java,
            listOf("get", "k") to GetCmdHandler::class.java,
            listOf("delete", "k") to DeleteCmdHandler::class.java,
            listOf("count", "v") to CountCmdHandler::class.java,
            listOf("begin") to BeginCmdHandler::class.java,
            listOf("commit") to CommitCmdHandler::class.java,
            listOf("rollback") to RollbackCmdHandler::class.java,
        )

        params.forEach { (k, v) -> assertEquals(v, cmdDispatcher.dispatch(k)!!::class.java) }

        assertNull(cmdDispatcher.dispatch(listOf("foo", "bar")))
    }
}