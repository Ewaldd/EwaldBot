package pl.xewald.ewald.bot.command.admin

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.Permission
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.Command
import pl.xewald.ewald.bot.command.CommandCategory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class PollCommand(val bot: EwaldBot) : Command(
        "ankieta",
        CommandCategory.ADMIN,
        "Stwórz ankiete.",
        listOf("pomoc")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        if (member == null) {
            channel.sendMessage("Ta komenda zadziała tylko na serwerach!").queue()
        } else {
            if (member.hasPermission(Permission.MESSAGE_MANAGE) || member.user.name == "Ewald") {
                if (args.size >= 2) {
                    var string = ""
                    for (arg in args) {
                        string += arg + " "
                    }
                    val current = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                    val formatted = current.format(formatter)
                    val eb = EmbedBuilder()
                    eb.setTitle("EwaldBot, ankieta")
                    eb.setDescription("${args.drop(1).joinToString(" ")}")
                    eb.setColor(member.color)
                    eb.setFooter("Ewaldbot $formatted", member.user.avatarUrl)
                    channel.deleteMessageById(message.id).queue()
                    val reactions: List<String>
                    when (args[0].toLowerCase()) {
                        "tn" -> reactions = listOf("\uD83C\uDDF9", "\uD83C\uDDF3")
                        "abc" -> reactions = listOf("\uD83C\uDDE6","\uD83C\uDDE7","\uD83C\uDDE8")
                        "abcd" -> reactions = listOf("\uD83C\uDDE6", "\uD83C\uDDE7", "\uD83C\uDDE8", "\uD83C\uDDE9")
                        else -> {channel.sendMessage("${member.asMention}, poprawne użycie: !ankieta <odpowiedzi- TN/ABC/ABCD> <pytanie>").queue(); return;}
                    }
                    channel.sendMessage(eb.build()).queue( { v -> reactions.forEach { r -> v.addReaction(r).queue() } })
                } else {
                    channel.sendMessage("${member.asMention}, poprawne użycie: !ankieta <odpowiedzi- TN/ABC/ABCD> <pytanie>").queue()
                }
            } else {
                channel.sendMessage("Brak uprawnień.").queue()
            }
        }
    }
}