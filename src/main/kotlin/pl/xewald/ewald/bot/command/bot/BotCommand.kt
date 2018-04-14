package pl.xewald.ewald.bot.command.bot

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.Command
import pl.xewald.ewald.bot.command.CommandCategory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class BotCommand(val bot: EwaldBot) : Command(
        "bot",
        CommandCategory.BOT,
        "Informacje o bocie",
        listOf("ewaldbot")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
        val formatted = current.format(formatter)
        val eb = EmbedBuilder()
        eb.setTitle("Informacje o bocie", null)
        eb.setColor(member!!.color)
        eb.addField("Autorzy:", "**[Ewald](https://github.com/Ewaldd), [Ixidi](https://github.com/Ixidi) i [Reedzev_](https://github.com/Reedzev)** ", true)
        eb.addField("Wersja:", "**1.2**", true)
        eb.addField("Sponsor:", "**[bvcz](https://github.com/HaskellV)**", true)
        eb.addField("Github:", "[Przejdź](https://github.com/Ewaldd/EwaldBot)", true)
        eb.addField("Strona:", "[Przejdź](https://bot.xewald.pl)", true)
        eb.addField("Discord:", "[Przejdź](https://discord.io/ewaldbot)", true)
        eb.addField("Serwerów posiadających bota:", "${bot.jda.guilds.size}" , true )
        eb.addField("Ping bota:", "${bot.jda.ping}" , true )
        eb.setAuthor("EwaldBot", "https://xewald.pl/", "https://xewald.pl/Ewald.gif")
        eb.setFooter("Data i godzina: $formatted", "https://xewald.pl/Ewald.gif")
        channel.sendMessage(eb.build()).queue()
    }
}
