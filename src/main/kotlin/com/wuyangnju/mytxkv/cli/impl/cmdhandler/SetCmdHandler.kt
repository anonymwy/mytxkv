package com.wuyangnju.mytxkv.cli.impl.cmdhandler

import com.wuyangnju.mytxkv.kv.ITransactionalKeyValueStore

class SetCmdHandler(private val txkv: ITransactionalKeyValueStore): ICmdHandler {
    override fun matches(args: List<String>): Boolean {
        return args[0].lowercase() == "set"
    }

    override fun validate(args: List<String>): Boolean {
        return args.size == 3
    }

    override fun process(args: List<String>): String? {
        txkv.set(args[1], args[2])
        return null
    }
}