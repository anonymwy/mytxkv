package com.wuyangnju.mytxkv.cli.impl

import com.wuyangnju.mytxkv.cli.ICli
import com.wuyangnju.mytxkv.kv.ITransactionalKeyValueStore

class InteractiveCliImpl(txkv: ITransactionalKeyValueStore): ICli {
    companion object {
        val SPACE_PATTERN = "\\s+".toRegex()

        val HELP_MSG = """
usage:
    SET <key> <value> // store the value for key
    GET <key> // return the current value for key
    DELETE <key> // remove the entry for key
    COUNT <value> // return the number of keys that have the given value
    BEGIN // start a new transaction
    COMMIT // complete the current transaction
    ROLLBACK // revert to state prior to BEGIN call
""".trim()
    }

    private val cmdDispatcher = createDefaultCmdDispatcher(txkv)

    override fun run() {
        while (true) {
            print("> ")

            var cmd: String
            try {
                cmd = readln()
            } catch (e: RuntimeException) {
                println("bye")
                break
            }

            val args = cmd.trim().split(SPACE_PATTERN)

            val cmdHandler = cmdDispatcher.dispatch(args)
            if (cmdHandler == null) {
                println(HELP_MSG)
                continue
            }
            if (!cmdHandler.validate(args)) {
                println(HELP_MSG)
                continue
            }

            val alertMsg = cmdHandler.alertMsg(args)
            if (alertMsg != null) {
                print(alertMsg)
                val confirm = readln()
                if (!(confirm.trim().lowercase() == "y" || confirm.trim().lowercase() == "yes")) {
                    continue
                }
            }

            val res = cmdHandler.process(args)
            if (res != null) {
                println(res)
            }
        }
    }
}