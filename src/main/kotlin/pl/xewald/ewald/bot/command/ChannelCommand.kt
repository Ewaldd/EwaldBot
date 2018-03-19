package pl.xewald.ewald.bot.command

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.*
import net.dv8tion.jda.core.events.channel.text.TextChannelCreateEvent
import net.dv8tion.jda.core.events.user.UserAvatarUpdateEvent
import net.dv8tion.jda.core.events.user.UserNameUpdateEvent
import net.dv8tion.jda.core.entities.User
import pl.xewald.ewald.bot.EventBot
import pl.xewald.ewald.bot.command.util.Command
import pl.xewald.ewald.bot.util.basicEmbedBuilder
import java.awt.Color
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class ChannelCommand(val bot: EventBot) : Command(
        "channel",
        "Sprawdz informacje o kanale",
        listOf("pomoc")
) {
    override fun execute(member: Member?, channel: MessageChannel, args: Array<String>) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = current.format(formatter)
        val eb = EmbedBuilder()
        eb.setTitle("Informacje o kanale ${channel.name}", null)
        eb.setColor(Color.RED)
        eb.setDescription("ID kanału: ${channel.id}\nID ostatniej wiadomości: ${channel.latestMessageId}\nData założenia kanału: ${channel.creationTime.toLocalDate()}")
        eb.setAuthor("EwaldBot", "https://xewald.pl/", "https://xewald.pl/Ewald.gif")
        eb.setFooter("Data i godzina: $formatted", null)
        channel.sendMessage(eb.build()).queue()
    }

}