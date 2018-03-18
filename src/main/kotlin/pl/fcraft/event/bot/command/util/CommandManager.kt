package pl.fcraft.event.bot.command.util

import java.util.*

class CommandManager {

    val commandMap = HashMap<String, Command>()

    fun add(command: Command) {
        commandMap[command.name.toLowerCase()] = command
    }

    fun get(name: String) : Command? {
        val entry = commandMap.entries.stream()
                .filter({entry -> entry.key == name || entry.value.aliases.contains(name)})
                .findFirst()
        if (entry.isPresent)
            return entry.get().value
        return null
    }

}