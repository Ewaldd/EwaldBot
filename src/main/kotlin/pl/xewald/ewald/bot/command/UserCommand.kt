package pl.xewald.ewald.bot.command

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.OnlineStatus
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.util.Command
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class UserCommand(val bot: EwaldBot) : Command(
        "user",
        "Podstawowe informacje o członku gildii",
        listOf("pomoc")
) {
    val statusLangMap = mapOf(
            OnlineStatus.DO_NOT_DISTURB to "Nie przeszkadzać",
            OnlineStatus.ONLINE to "Online",
            OnlineStatus.OFFLINE to "Offline/Niewidoczny",
            OnlineStatus.INVISIBLE to "Niewidoczny",
            OnlineStatus.IDLE to "Zaraz wracam",
            OnlineStatus.UNKNOWN to "Nieokreślony"
    )

    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        if (member == null) {
            channel.sendMessage("Ta komenda dostepna jest tylko na serwerach!").queue()
            return
        }
        val user = if (!message.mentionedMembers.isEmpty()) message.mentionedMembers.first() else member
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = current.format(formatter)
        val eb = EmbedBuilder()
        eb.setTitle("Informacje o ${user.effectiveName}", null)
        eb.setColor(user.color)
        val gameData = StringBuilder()
        if (user.game != null) {
            gameData.append(user.game.name)
            if (user.game.isRich) {
                val details = user.game.asRichPresence().details
                if (details != null) {
                    gameData.append(" (").append(details)
                    if (user.game.asRichPresence().state != null) {
                        gameData.append(", ").append(user.game.asRichPresence().state)
                    }
                    gameData.append(')')
                }
            }
        }


        val roles = if (user.roles.isEmpty()) "brak"
            else user.roles.joinToString(", ") { role -> "${role.name} (${role.id})" }
        eb.setDescription(
                "**Role:** $roles\n" +
                "**Tag:** #${member.user.discriminator}\n" +
                "**Data założenia konta:** ${user.user.creationTime.toLocalDate()}\n" +
                "**Status:** ${statusLangMap[user.onlineStatus]}\n" +
                if (user.game != null) "**Aktualna gra:** $gameData\n" else "" +
                "**Data dołączenia do serwera:** ${user.joinDate.toLocalDate()}")
        eb.setAuthor("EwaldBot", "https://xewald.pl/", "https://xewald.pl/Ewald.gif")
        eb.setFooter("Data i godzina: $formatted", "https://xewald.pl/Ewald.gif")
        eb.setThumbnail(user.user.effectiveAvatarUrl)
        channel.sendMessage(eb.build()).queue()
    }
}
