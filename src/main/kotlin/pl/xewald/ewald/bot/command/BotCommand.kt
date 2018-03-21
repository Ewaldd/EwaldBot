package pl.xewald.ewald.bot.command

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.MessageChannel
import pl.xewald.ewald.bot.Main
import pl.xewald.ewald.bot.command.util.Command
import java.awt.Color
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class BotCommand(val bot: Main) : Command(
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
        eb.addField("Autor bota:", "**Ewald#1796**", true)
        eb.addField("Github bota:", "[Przejdź](https://github.com/Ewaldd/EwaldBot)", true)
        eb.addField("Wersja bota:", "**1.0**", true)
        eb.addField("Sponsor bota:", "**bvcz#5371**", true)
        eb.setAuthor("EwaldBot", "https://xewald.pl/", "https://xewald.pl/Ewald.gif")
        eb.setFooter("Data i godzina: $formatted", "https://xewald.pl/Ewald.gif")
        channel.sendMessage(eb.build()).queue()
    }
}