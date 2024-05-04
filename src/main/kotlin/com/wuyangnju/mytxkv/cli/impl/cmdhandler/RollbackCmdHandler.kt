package com.wuyangnju.mytxkv.cli.impl.cmdhandler

import com.wuyangnju.mytxkv.cli.impl.cmdhandler.ICmdHandler.Companion.NO_TRANSACTION
import com.wuyangnju.mytxkv.cli.impl.cmdhandler.ICmdHandler.Companion.ROLLBACK_ALERT
import com.wuyangnju.mytxkv.kv.ITransactionalKeyValueStore

class RollbackCmdHandler(private val txkv: ITransactionalKeyValueStore): ICmdHandler {
    override fun matches(args: List<String>): Boolean {
        return args[0].lowercase() == "rollback"
    }

    override fun validate(args: List<String>): Boolean {
        return args.size == 1
    }

    override fun alertMsg(args: List<String>): String? {
        return ROLLBACK_ALERT
    }

    override fun process(args: List<String>): String? {
        val res = txkv.rollback()
        if (!res) {
            return NO_TRANSACTION
        }
        return null
    }
}