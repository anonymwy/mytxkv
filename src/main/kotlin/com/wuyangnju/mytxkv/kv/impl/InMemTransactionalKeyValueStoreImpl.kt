package com.wuyangnju.mytxkv.kv.impl

import com.wuyangnju.mytxkv.kv.ITransactionalKeyValueStore

/**
 *  InMemTransactionalKeyValueStoreImpl uses a stack of TransactionFrame to support nested transactions.
 *  A List is used to mock stack, where list.add() equals stack-push and list.removeLast() equals stack-pop.
 */
class InMemTransactionalKeyValueStoreImpl : ITransactionalKeyValueStore {
    private val txStack = mutableListOf(createInMemTransactionFrame())

    override fun set(key: String, value: String) {
        txStack.last().set(key, value)
    }

    override fun get(key: String): String? {
        return txStack.last().get(key)
    }

    override fun delete(key: String) {
        txStack.last().delete(key)
    }

    override fun count(value: String): Int {
        return txStack.last().count(value)
    }

    override fun begin() {
        val lastTx = txStack.last()
        txStack.add(lastTx.copy())
    }

    override fun commit(): Boolean {
        if (txStack.size == 1) {
            return false
        }
        val lastTx = txStack.removeLast()
        txStack.removeLast()
        txStack.add(lastTx)
        return true
    }

    override fun rollback(): Boolean {
        if (txStack.size == 1) {
            return false
        }
        txStack.removeLast()
        return true
    }
}