package com.wuyangnju.mytxkv.cli.impl

import com.wuyangnju.mytxkv.cli.impl.cmdhandler.BeginCmdHandler
import com.wuyangnju.mytxkv.cli.impl.cmdhandler.CommitCmdHandler
import com.wuyangnju.mytxkv.cli.impl.cmdhandler.CountCmdHandler
import com.wuyangnju.mytxkv.cli.impl.cmdhandler.DeleteCmdHandler
import com.wuyangnju.mytxkv.cli.impl.cmdhandler.GetCmdHandler
import com.wuyangnju.mytxkv.cli.impl.cmdhandler.ICmdHandler
import com.wuyangnju.mytxkv.cli.impl.cmdhandler.RollbackCmdHandler
import com.wuyangnju.mytxkv.cli.impl.cmdhandler.SetCmdHandler
import com.wuyangnju.mytxkv.kv.ITransactionalKeyValueStore

interface ICmdDispatcher {
    fun dispatch(args: List<String>): ICmdHandler?
}

fun createDefaultCmdDispatcher(txkv: ITransactionalKeyValueStore): ICmdDispatcher {
    return DefaultCmdDispatcherImpl(txkv)
}

class DefaultCmdDispatcherImpl(txkv: ITransactionalKeyValueStore): ICmdDispatcher {
    private val cmdHandlerList = listOf(
        SetCmdHandler(txkv),
        GetCmdHandler(txkv),
        DeleteCmdHandler(txkv),
        CountCmdHandler(txkv),
        BeginCmdHandler(txkv),
        CommitCmdHandler(txkv),
        RollbackCmdHandler(txkv),
    )

    override fun dispatch(args: List<String>): ICmdHandler? {
        return cmdHandlerList.firstOrNull { it.matches(args) }
    }
}