package com.wuyangnju.mytxkv.cli

import com.wuyangnju.mytxkv.cli.impl.InteractiveCliImpl
import com.wuyangnju.mytxkv.kv.ITransactionalKeyValueStore

interface ICli {
    fun run()
}

fun createInteractiveCli(txkv: ITransactionalKeyValueStore): ICli {
    return InteractiveCliImpl(txkv)
}