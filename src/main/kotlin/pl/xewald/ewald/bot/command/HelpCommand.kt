package pl.xewald.ewald.bot.command

import net.dv8tion.jda.core.entities.*
import org.w3c.dom.css.RGBColor
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.util.Command
import pl.xewald.ewald.bot.util.basicEmbedBuilder
import java.awt.Color

class HelpCommand(val bot: EwaldBot) : Command(
        "help",
        "Pomoc.",
        listOf("pomoc")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        val commandMap = bot.commandManager.commandMap
        val embed = basicEmbedBuilder("Pomoc")
        val builder = StringBuilder()
        for ((name, command) in commandMap) {
            builder.append("`!$name - ${command.description}`\n")
        }
        embed.setDescription(builder.toString())
        embed.setColor(Color.RED)
        channel.sendMessage(embed.build()).queue()
    }
}