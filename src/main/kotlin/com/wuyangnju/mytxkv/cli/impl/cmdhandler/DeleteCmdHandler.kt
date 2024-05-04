package com.wuyangnju.mytxkv.cli.impl.cmdhandler

import com.wuyangnju.mytxkv.cli.impl.cmdhandler.ICmdHandler.Companion.DELETE_ALERT
import com.wuyangnju.mytxkv.kv.ITransactionalKeyValueStore

class DeleteCmdHandler(private val txkv: ITransactionalKeyValueStore): ICmdHandler {
    override fun matches(args: List<String>): Boolean {
        return args[0].lowercase() == "delete"
    }

    override fun validate(args: List<String>): Boolean {
        return args.size == 2
    }

    override fun alertMsg(args: List<String>): String? {
        return DELETE_ALERT.format(args[1])
    }

    override fun process(args: List<String>): String? {
        txkv.delete(args[1])
        return null
    }
}