package com.wuyangnju.mytxkv.cli.impl.cmdhandler

import com.wuyangnju.mytxkv.kv.ITransactionalKeyValueStore

class CountCmdHandler(private val txkv: ITransactionalKeyValueStore): ICmdHandler {
    override fun matches(args: List<String>): Boolean {
        return args[0].lowercase() == "count"
    }

    override fun validate(args: List<String>): Boolean {
        return args.size == 2
    }

    override fun process(args: List<String>): String? {
        return txkv.count(args[1]).toString()
    }
}