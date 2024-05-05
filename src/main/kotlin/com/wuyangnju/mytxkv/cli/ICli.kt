package com.wuyangnju.mytxkv.cli

import com.wuyangnju.mytxkv.cli.impl.InteractiveCliImpl
import com.wuyangnju.mytxkv.kv.ITransactionalKeyValueStore

/**
 * Cli focuses on handling user input/output.
 */
interface ICli {
    fun run()
}

fun createInteractiveCli(txkv: ITransactionalKeyValueStore): ICli {
    return InteractiveCliImpl(txkv)
}