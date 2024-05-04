package com.wuyangnju.mytxkv.cli.impl.cmdhandler

import com.wuyangnju.mytxkv.cli.impl.cmdhandler.ICmdHandler.Companion.KEY_NOT_SET_MSG
import com.wuyangnju.mytxkv.kv.ITransactionalKeyValueStore

class GetCmdHandler(private val txkv: ITransactionalKeyValueStore): ICmdHandler {
    override fun matches(args: List<String>): Boolean {
        return args[0].lowercase() == "get"
    }

    override fun validate(args: List<String>): Boolean {
        return args.size == 2
    }

    override fun process(args: List<String>): String? {
        val value = txkv.get(args[1]) ?: return KEY_NOT_SET_MSG
        return value
    }
}