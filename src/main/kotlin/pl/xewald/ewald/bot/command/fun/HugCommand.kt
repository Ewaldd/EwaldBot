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
        "Przytul kogoś/coś",
        listOf("hug")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        if (member == null) {
            channel.sendMessage("Ta komenda zadziała tylko na serwerach!").queue()
            return
        }
        if (args.isNotEmpty()) {
            if (message.mentionedUsers.isEmpty()) {
                val rand = Random().nextInt(12)
                val eb = EmbedBuilder()
                eb.setTitle("${member.effectiveName} przytulił ${args.joinToString(" ")} \uD83D\uDC95")
                eb.setColor(member.color)
                eb.setImage(
                        when (rand) {
                            1 -> "https://media.giphy.com/media/qscdhWs5o3yb6/giphy.gif"
                            2 -> "https://media.giphy.com/media/wnsgren9NtITS/giphy.gif"
                            3 -> "https://media1.tenor.com/images/8a5c94bfc51e50b35d78f2208a02165f/tenor.gif?itemid=10383031"
                            4 -> "https://media1.tenor.com/images/cb3031d8f1fb32d097ec899592ab7eec/tenor.gif?itemid=5406385"
                            5 -> "https://media1.tenor.com/images/68a86beda361994b2dd38ade6a252602/tenor.gif?itemid=3904776"
                            6 -> "https://media1.tenor.com/images/0ba08019dd365d87d72dda563b7123ce/tenor.gif?itemid=8516941"
                            7 -> "https://media1.tenor.com/images/08a69472e6ba9494afc8c045cb3077a5/tenor.gif?itemid=5288970"
                            8 -> "https://media1.tenor.com/images/b2ba311b508511318293edd4148d2192/tenor.gif?itemid=7352583"
                            9 -> "https://media1.tenor.com/images/2b7f8777a010f07bb49457b4ee987d09/tenor.gif?itemid=7615859"
                            10 -> "https ://media1.tenor.com/images/d13dba5cd4acda4ac5efd7d4d51f86dd/tenor.gif?itemid=10181124"
                            11 -> "https://media0.giphy.com/media/42YlR8u9gV5Cw/giphy.gif"
                            else -> "https://media2.giphy.com/media/VGACXbkf0AeGs/giphy.gif"
                        }
                )

                channel.sendMessage(eb.build()).queue()
            } else {
                channel.sendMessage("Wiadomość nie może posiadać oznaczenia!").queue()
            }
        } else {
            channel.sendMessage("Wiadomość jest pusta!").queue()
        }
    }
}



