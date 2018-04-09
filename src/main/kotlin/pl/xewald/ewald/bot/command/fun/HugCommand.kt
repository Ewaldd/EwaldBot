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

class HugCommand(val bot: EwaldBot) : Command(
        "przytul",
        CommandCategory.FUN,
        "Odpowiedź kogoś/coś",
        listOf("pomoc")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        if (member == null) {
            channel.sendMessage("Ta komenda zadziała tylko na serwerach!").queue()
            return
        }
        if (args.isNotEmpty()) {
            if (message.mentionedUsers.isEmpty()){
                val rand = Random().nextInt(3 - 1 + 1) + 1
                val eb = EmbedBuilder()
                eb.setTitle("${member.effectiveName} przytulił ${args.joinToString(" ")} \uD83D\uDC95")
                eb.setColor(member.color)
                eb.setImage(
                        when (rand) {
                            1 -> "https://media.giphy.com/media/qscdhWs5o3yb6/giphy.gif"
                            2 -> "https://media.giphy.com/media/wnsgren9NtITS/giphy.gif"
                            else -> "https://media2.giphy.com/media/VGACXbkf0AeGs/giphy.gif"
                        }
                )
                channel.sendMessage(eb.build()).queue()
            }else{
                channel.sendMessage("Wiadomość nie może posiadać oznaczenia!").queue()
            }
        }else{
            channel.sendMessage("Wiadomość jest pusta!").queue()
        }
    }
}



