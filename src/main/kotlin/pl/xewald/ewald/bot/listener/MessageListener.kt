package pl.xewald.ewald.bot.listener

import net.dv8tion.jda.core.entities.Channel
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import pl.xewald.ewald.bot.Main

class MessageListener(val bot: Main) : ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        var message = event.message.contentRaw!!
        if (!message.startsWith('!'))
            return
        val splited = message.substring(1).split(" ")
        val command = bot.commandManager.get(splited[0])
        var args: Array<String> = emptyArray()
        if (splited.size > 1) {
            Array(splited.size - 1) { i -> splited[i + 1] }
        }
        command!!.execute(event.member, event.channel, args)
    }

}