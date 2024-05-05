package com.wuyangnju.mytxkv

import com.wuyangnju.mytxkv.cli.createInteractiveCli
import com.wuyangnju.mytxkv.kv.TransactionalKeyValueStoreType
import com.wuyangnju.mytxkv.kv.createTransactionalKeyValueStore

fun main() {
    /* To extend this solution, a config (from cmd or file) should be used to
        decide which implementation of data store or user interface to instantiate. */
    val txkv = createTransactionalKeyValueStore(TransactionalKeyValueStoreType.InMemory)
    createInteractiveCli(txkv).run()
}