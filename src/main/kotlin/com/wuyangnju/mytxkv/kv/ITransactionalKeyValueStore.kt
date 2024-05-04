package com.wuyangnju.mytxkv.kv

import com.wuyangnju.mytxkv.kv.impl.InMemTransactionalKeyValueStoreImpl

interface ITransactionalKeyValueStore {
    fun set(key: String, value: String)
    fun get(key: String): String?
    fun delete(key: String)
    fun count(value: String): Int
    fun begin()
    fun commit(): Boolean
    fun rollback(): Boolean
}

enum class TransactionalKeyValueStoreType {
    InMemory
}

fun createTransactionalKeyValueStore(type: TransactionalKeyValueStoreType): ITransactionalKeyValueStore {
    return when (type) {
        TransactionalKeyValueStoreType.InMemory -> InMemTransactionalKeyValueStoreImpl()
    }
}