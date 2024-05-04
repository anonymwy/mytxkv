package com.wuyangnju.mytxkv.cli.impl.cmdhandler

import com.wuyangnju.mytxkv.cli.impl.cmdhandler.ICmdHandler.Companion.COMMIT_ALERT
import com.wuyangnju.mytxkv.cli.impl.cmdhandler.ICmdHandler.Companion.NO_TRANSACTION
import com.wuyangnju.mytxkv.kv.ITransactionalKeyValueStore

class CommitCmdHandler(private val txkv: ITransactionalKeyValueStore): ICmdHandler {
    override fun matches(args: List<String>): Boolean {
        return args[0].lowercase() == "commit"
    }

    override fun validate(args: List<String>): Boolean {
        return args.size == 1
    }

    override fun alertMsg(args: List<String>): String? {
        return COMMIT_ALERT
    }

    override fun process(args: List<String>): String? {
        val res = txkv.commit()
        if (!res) {
            return NO_TRANSACTION
        }
        return null
    }
}