package com.wuyangnju.mytxkv

import com.wuyangnju.mytxkv.cli.createInteractiveCli
import com.wuyangnju.mytxkv.kv.TransactionalKeyValueStoreType
import com.wuyangnju.mytxkv.kv.createTransactionalKeyValueStore

fun main() {
    val txkv = createTransactionalKeyValueStore(TransactionalKeyValueStoreType.InMemory)
    createInteractiveCli(txkv).run()
}