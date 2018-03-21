package pl.xewald.ewald.bot.command

import net.dv8tion.jda.core.entities.*
import net.dv8tion.jda.core.events.channel.text.TextChannelCreateEvent
import net.dv8tion.jda.core.events.user.UserAvatarUpdateEvent
import net.dv8tion.jda.core.events.user.UserNameUpdateEvent
import pl.xewald.ewald.bot.Main
import pl.xewald.ewald.bot.command.util.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import java.awt.Color;
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class UserCommand(val bot: Main) : Command(
        "user",
        "Podstawowe informacje o Twoim koncie",
        listOf("pomoc")
) {
    override fun execute(member: Member?, channel: MessageChannel, args: Array<String>) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = current.format(formatter)
        val eb = EmbedBuilder()
        eb.setTitle("Informacje o Tobie", null)
        eb.setColor(Color.darkGray)
        eb.setDescription(" **Twoje rangi:** ${member!!.roles} \n**Twój Tag:** #${member.user.discriminator}\n**Data założenia Discorda:** ${member.user.creationTime.toLocalDate()}\n**Twój status:** ${member.onlineStatus}\n**Aktualna gra:** ${member.game}\n**Data dołączenia do serwera:** ${member.joinDate.toLocalDate()}")
        eb.addField("", "Twój awatar:", false)
        eb.setAuthor("EwaldBot", "https://xewald.pl/", "https://xewald.pl/Ewald.gif")
        eb.setFooter("Data i godzina: $formatted", "https://xewald.pl/Ewald.gif")
        eb.setImage("${member.user.avatarUrl}")
        channel.sendMessage(eb.build()).queue()
    }
}
