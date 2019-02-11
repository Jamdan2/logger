package org.sert2521.logger

class Logger(
    val name: String,
    val indent: String = "   ",
    val defaultLogType: LogType = LogType.DEFAULT,
    private val indentStack: String = ""
) {
    enum class LogType {
        DEFAULT, WARNING, ERROR
    }

    fun log(message: String, type: LogType = defaultLogType) {
        println(indentStack + message)
    }

    fun warning(message: String) = log(message, LogType.WARNING)

    fun error(message: String) = log(message, LogType.ERROR)

    fun logTree(head: String, type: LogType = defaultLogType, body: Logger.() -> Unit) {
        log(head, type)
        Logger(
            name = name,
            indent = indent,
            indentStack = indentStack + indent,
            defaultLogType = type
        ).body()
    }

    fun String.unaryPlus() = log(this, defaultLogType)

    fun String.invoke(body: Logger.() -> Unit) = logTree(this, body = body)
}
