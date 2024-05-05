package com.wuyangnju.mytxkv.cli.impl.cmdhandler

/**
 * CmdHandler handles user command.
 */
interface ICmdHandler {
    // decide whether this CmdHandler is able to handle the command
    fun matches(args: List<String>): Boolean

    // validate the command, e.g. number of arguments
    fun validate(args: List<String>): Boolean

    // return alert msg if this command needs confirmation. by default, it returns null for no need.
    fun alertMsg(args: List<String>): String? {
        return null
    }

    // process the command by calling TransactionalKeyValueStore
    fun process(args: List<String>): String?

    companion object {
        const val KEY_NOT_SET_MSG = "key not set"
        const val NO_TRANSACTION = "no transaction"
        const val DELETE_ALERT = "Are you sure you want to delete %s? (default: n|no) [y|yes, n|no] "
        const val COMMIT_ALERT = "Are you sure you want to commit? (default: n|no) [y|yes, n|no] "
        const val ROLLBACK_ALERT = "Are you sure you want to rollback? (default: n|no) [y|yes, n|no] "
    }
}