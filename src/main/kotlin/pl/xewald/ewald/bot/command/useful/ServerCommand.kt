package pl.xewald.ewald.bot.command.useful

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.OnlineStatus
import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.Command
import pl.xewald.ewald.bot.command.CommandCategory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class ServerCommand(val bot: EwaldBot) : Command(
        "serwer",
        CommandCategory.USEFUL,
        "Sprawdz informacje o serwerze",
        listOf("pomoc")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        if (member == null) {
            channel.sendMessage("Ta komenda dostepna jest tylko na serwerach!").queue()
            return
        }
        val g = member.guild
        val eb = EmbedBuilder()
        if (args.size == 0) {
            eb.setDescription("ID: ${g.id}")
            eb.addField("Poziom weryfikacji", when {
                g.verificationLevel.name == "NONE" -> "Brak"
                g.verificationLevel.name == "LOW" -> "Niski"
                g.verificationLevel.name == "MEDIUM" -> "Średni"
                g.verificationLevel.name == "HIGH" -> "Wysoki"
                else -> "Bardzo wysoki"
            }, false)
            eb.addField("Region", "${g.region}", true)
            eb.addField("Członkowie [${g.members.size}]", "", true)
            eb.addField("Kanały [${g.textChannels.size + g.voiceChannels.size}]", "${g.categories.size} Kategorie\n${g.textChannels.size} kanały tekstowe" +
                    "\n${g.voiceChannels.size} kanały głosowe", true)
            eb.addField("Właściciel serwera:", g.owner.asMention, true)
            eb.addField("Utworzono:", "${g.creationTime.toLocalDate()}", true)
            eb.addField("Role: ", "${g.roles.size}", true)
            eb.addField("Więcej informacji?", "!serwer help", false)
            eb.setAuthor(g.name, "https://bot.xewald.pl", g.iconUrl)
            eb.setThumbnail(g.iconUrl)
            eb.setColor(member.color)
            channel.sendMessage(eb.build()).queue()
        } else if (args.size == 1) {
            when {
                args[0].toLowerCase() == "help" || args[0].toLowerCase() == "pomoc" -> {
                    eb.setAuthor(g.name, "https://bot.xewald.pl", g.iconUrl)
                    eb.setThumbnail(g.iconUrl)
                    eb.setDescription("!serwer - wyświetla informacje o serwerze.\n!serwer role - wyświetla wszystkie role na serwerze.")
                    channel.sendMessage(eb.build()).queue()
                }
                args[0].toLowerCase() == "role" || args[0].toLowerCase() == "roles" -> {
                    eb.setAuthor(g.name, "https://bot.xewald.pl", g.iconUrl)
                    eb.setColor(member.color)
                    eb.setThumbnail(g.iconUrl)
                    eb.setDescription("Wszystkie role na serwerze: \n" + g.roles.joinToString(", "))
                    channel.sendMessage(eb.build()).queue()
                }
                else -> channel.sendMessage("Poprawne użycie: !serwer help")
            }
        }
    }
}

