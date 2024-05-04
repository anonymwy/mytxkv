package com.wuyangnju.mytxkv.cli.impl.cmdhandler

import com.wuyangnju.mytxkv.kv.ITransactionalKeyValueStore

class BeginCmdHandler(private val txkv: ITransactionalKeyValueStore): ICmdHandler {
    override fun matches(args: List<String>): Boolean {
        return args[0].lowercase() == "begin"
    }

    override fun validate(args: List<String>): Boolean {
        return args.size == 1
    }

    override fun process(args: List<String>): String? {
        txkv.begin()
        return null
    }
}