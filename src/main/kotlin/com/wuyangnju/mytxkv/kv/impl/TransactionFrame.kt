package com.wuyangnju.mytxkv.kv.impl

/**
 * A TransactionFrame contains all key-value data within a certain transaction.
 */
interface ITransactionFrame {
    fun set(key: String, value: String)
    fun get(key: String): String?
    fun delete(key: String)
    fun count(value: String): Int

    fun copy(): ITransactionFrame
}

fun createInMemTransactionFrame(): ITransactionFrame {
    return InMemTransactionFrameImpl()
}

class InMemTransactionFrameImpl : ITransactionFrame {
    private var map: MutableMap<String, String> = mutableMapOf()
    private var inverseMap: MutableMap<String, MutableSet<String>> = mutableMapOf()

    override fun set(key: String, value: String) {
        map[key] = value
        if (!inverseMap.contains(value)) {
            inverseMap[value] = mutableSetOf()
        }
        inverseMap[value]!!.add(key)
    }

    override fun get(key: String): String? {
        if (!map.contains(key)) {
            return null
        }
        return map[key]
    }

    override fun delete(key: String) {
        val value = map.remove(key) ?: return
        inverseMap[value]!!.remove(key)
        if (inverseMap[value]!!.isEmpty()) {
            inverseMap.remove(value)
        }
    }

    override fun count(value: String): Int {
        if (!inverseMap.contains(value)) {
            return 0
        }
        return inverseMap[value]!!.size
    }

    override fun copy(): ITransactionFrame {
        val ret = InMemTransactionFrameImpl()
        map.forEach { (k, v) -> ret.map[k] = v }
        inverseMap.forEach { (k, v) -> ret.inverseMap[k] = v.toMutableSet() }
        return ret
    }
}