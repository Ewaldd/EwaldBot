package pl.fcraft.event.bot.command

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.MessageChannel
import pl.fcraft.event.bot.EventBot
import pl.fcraft.event.bot.command.util.Command
import java.awt.Color
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class BotCommand(val bot: EventBot) : Command(
        "bot",
        "Informacje o bocie",
        listOf("pomoc")
) {
    override fun execute(member: Member?, channel: MessageChannel, args: Array<String>) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = current.format(formatter)
        val eb = EmbedBuilder()
        eb.setTitle("Informacje o bocie", null)
        eb.setColor(Color.BLUE)
        eb.addField("Autor bota:", "Ewald", true)
        eb.addField("Github bota:", "https://github.com/Ewaldd/EwaldBot", true)
        eb.setAuthor("EwaldBot", "https://xewald.pl/", "https://xewald.pl/Ewald.gif")
        eb.setFooter("Data i godzina: $formatted", "https://xewald.pl/Ewald.gif")
        channel.sendMessage(eb.build()).queue()
    }
}