package com.wuyangnju.mytxkv.cli.impl.cmdhandler

interface ICmdHandler {
    fun matches(args: List<String>): Boolean
    fun validate(args: List<String>): Boolean
    fun alertMsg(args: List<String>): String? {
        return null
    }
    fun process(args: List<String>): String?

    companion object {
        const val KEY_NOT_SET_MSG = "key not set"
        const val NO_TRANSACTION = "no transaction"
        const val DELETE_ALERT = "Are you sure you want to delete %s? (default: n|no) [y|yes, n|no] "
        const val COMMIT_ALERT = "Are you sure you want to commit? (default: n|no) [y|yes, n|no] "
        const val ROLLBACK_ALERT = "Are you sure you want to rollback? (default: n|no) [y|yes, n|no] "
    }
}