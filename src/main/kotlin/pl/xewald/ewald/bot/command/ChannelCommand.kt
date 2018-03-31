package pl.xewald.ewald.bot.command

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.util.Command
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class ChannelCommand(val bot: EwaldBot) : Command(
        "channel",
        "Sprawdz informacje o kanale",
        listOf("pomoc")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        if (member == null) {
            channel.sendMessage("Ta komenda dostepna jest tylko na serwerach!").queue()
            return
        }

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
        val formatted = current.format(formatter)
        val eb = EmbedBuilder()
        eb.setTitle("Informacje o kanale #${channel.name}", null)
        eb.setColor(member.color)
        eb.setDescription(
                "ID kanału: ${channel.id}\n" +
                "ID ostatniej wiadomości: ${channel.latestMessageId}\n" +
                "Przypięte wiadomości: ${channel.pinnedMessages.complete().size}\n" +
                "Data założenia kanału: ${channel.creationTime.toLocalDate()}")
        eb.setAuthor("EwaldBot", "https://xewald.pl/", "https://xewald.pl/Ewald.gif")
        eb.setFooter("Data i godzina: $formatted", null)
        channel.sendMessage(eb.build()).queue()
    }
}