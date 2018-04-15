package pl.xewald.ewald.bot.command.bot

import net.dv8tion.jda.core.entities.*
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.Command
import pl.xewald.ewald.bot.command.CommandCategory
import pl.xewald.ewald.bot.util.basicEmbedBuilder

class HelpCommand(val bot: EwaldBot) : Command(
        "help",
        CommandCategory.BOT,
        "Pomoc.",
        listOf("pomoc")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        val commandMap = bot.commandManager.commandMap
        val embed = basicEmbedBuilder("Pomoc")
        CommandCategory.values().forEach { category ->
            val builder = StringBuilder()
            commandMap.values.stream()
                    .filter { command -> command.category == category }
                    .forEach { command ->
                        builder.append("`!${command.name} - ${command.description}`\n")
                    }
            embed.addField(category.name.toLowerCase().capitalize(), builder.toString(), false)
        }
        embed.setColor(member!!.color)
        channel.sendMessage(embed.build()).queue()
    }
}