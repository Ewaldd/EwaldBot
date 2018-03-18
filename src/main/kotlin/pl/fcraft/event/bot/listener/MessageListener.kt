package pl.fcraft.event.bot.listener

import net.dv8tion.jda.core.entities.Channel
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import pl.fcraft.event.bot.EventBot

class MessageListener(val bot: EventBot): ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        var message = event.message.contentRaw!!
        if (!message.startsWith('!'))
            return
        val splited = message.substring(1).split(" ")
        val command = bot.commandManager.get(splited[0])
        if (command == null) {
            val text = event.author.asMention + ", taka komenda nie istnieje!"
            event.channel.sendMessage(text).queue()
            return
        }
        var args: Array<String>
        if (splited.size > 1) {
            args = Array(splited.size - 1) {i -> splited[i + 1]}
        } else {
            args = emptyArray()
        }
        command.execute(event.member, event.channel, args)
    }

}