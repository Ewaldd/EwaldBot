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
import java.math.*

class HitCommand(val bot: EwaldBot) : Command(
        "uderz",
        CommandCategory.FUN,
        "Uderz kogoś/coś",
        listOf("pomoc")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        if (member == null) {
            channel.sendMessage("Ta komenda zadziała tylko na serwerach!").queue()
            return
        }
        if(args.isNotEmpty()) {
            if (message.mentionedUsers.isEmpty()) {
                val eb = EmbedBuilder()
                eb.setColor(member.color)
                eb.setTitle("${member.effectiveName} uderza ${args.joinToString(" ")} \uD83D\uDC4A")
                val rand = Random().nextInt(3 - 1 + 1) + 1
                eb.setImage(
                        when (rand) {
                            1 -> "https://media.giphy.com/media/BKRECiW08vdjG/giphy.gif"
                            2 -> "https://media.giphy.com/media/AlsIdbTgxX0LC/giphy.gif"
                            else -> "https://media.giphy.com/media/rcRwO8GMSfNV6/giphy.gif"
                        }
                )
                channel.sendMessage(eb.build()).queue()
            } else {
                channel.sendMessage("Komenda nie może posiadać oznaczenia!").queue()
            }
        }else{
            channel.sendMessage("Wiadomość nie możę być pusta!").queue()
        }
    }
}