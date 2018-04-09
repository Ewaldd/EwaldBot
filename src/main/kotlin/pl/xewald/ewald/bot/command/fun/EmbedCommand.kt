package pl.xewald.ewald.bot.command.`fun`

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import net.dv8tion.jda.core.utils.WidgetUtil
import org.json.JSONObject
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.Command
import pl.xewald.ewald.bot.command.CommandCategory
import java.awt.Color
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class EmbedCommand(val bot: EwaldBot) : Command(
        "embed",
        CommandCategory.FUN,
        "Wyślij embed",
        listOf("pomoc")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        if (member == null) {
            channel.sendMessage("Ta komenda zadziała tylko na serwerach!").queue()
            return
        }
        if (args.isNotEmpty()) {
            val eb = EmbedBuilder()
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
            val formatted = current.format(formatter)
            eb.setColor(member.color)
            eb.setAuthor("Embed użytkownika ${member.effectiveName}", "https://bot.xewald.pl", member.user.avatarUrl)
            eb.setDescription(args.joinToString(" "))
            eb.setFooter("EwaldBot $formatted", "https://xewald.pl/Ewald.gif")
            channel.deleteMessageById(message.id).queue()
            channel.sendMessage(eb.build()).queue()
        }else{
            channel.sendMessage("Embed nie może być pusty!").queue()
        }
    }
}