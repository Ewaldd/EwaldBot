package pl.xewald.ewald.bot.command

import net.dv8tion.jda.core.entities.*
import net.dv8tion.jda.core.events.channel.text.TextChannelCreateEvent
import net.dv8tion.jda.core.events.user.UserNameUpdateEvent
import org.w3c.dom.css.RGBColor
import pl.xewald.ewald.bot.EventBot
import pl.xewald.ewald.bot.command.util.Command
import pl.xewald.ewald.bot.util.basicEmbedBuilder
import java.awt.Color

class HelpCommand(val bot: EventBot) : Command(
        "help",
        "Pomoc.",
        listOf("pomoc")
) {

    override fun execute(member: Member?, channel: MessageChannel, args: Array<String>) {
        val commandMap = bot.commandManager.commandMap
        val embed = basicEmbedBuilder("Pomoc")
        val builder = StringBuilder()
        for ((name, command) in commandMap) {
            builder.append("`!$name - ${command.description}`\n")
        }
        embed.setDescription(builder.toString())
        embed.setColor(Color.RED)
        val messageChannel: MessageChannel
        if (member != null) {
            messageChannel = member.user.openPrivateChannel().complete()
            channel.sendMessage(member.asMention + ", pomoc została wysłana w prywatnej wiadomości.").queue()
        } else
            messageChannel = channel
        messageChannel.sendMessage(embed.build()).queue()
    }

}