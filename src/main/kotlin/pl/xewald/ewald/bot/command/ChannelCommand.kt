package pl.xewald.ewald.bot.command

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.*
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.util.Command
import java.awt.Color
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class ChannelCommand(val bot: EwaldBot) : Command(
        "channel",
        "Sprawdz informacje o kanale",
        listOf("pomoc")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
<<<<<<< HEAD
        if (member == null) {
            channel.sendMessage("Ta komenda dostepna jest tylko na serwerach!").queue()
            return
        }
=======
>>>>>>> 4ba9fc04f1059113aa9e31a308cba7ee257b0f19
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = current.format(formatter)
        val eb = EmbedBuilder()
        eb.setTitle("Informacje o kanale ${channel.name}", null)
        eb.setColor(member.color)
        eb.setDescription("ID kanału: ${channel.id}\nID ostatniej wiadomości: ${channel.latestMessageId}\nData założenia kanału: ${channel.creationTime.toLocalDate()}")
        eb.setAuthor("EwaldBot", "https://xewald.pl/", "https://xewald.pl/Ewald.gif")
        eb.setFooter("Data i godzina: $formatted", null)
        channel.sendMessage(eb.build()).queue()
    }
}