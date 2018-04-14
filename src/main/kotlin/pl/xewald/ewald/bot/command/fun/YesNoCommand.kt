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

class YesNoCommand(val bot: EwaldBot) : Command(
        "tn",
        CommandCategory.FUN,
        "Odpowiedź na pytanie",
        listOf("yn", "taknie", "yesno")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        if (member == null) {
            channel.sendMessage("Ta komenda zadziała tylko na serwerach!").queue()
            return
        }
        if(args.isNotEmpty()) {
            val eb = EmbedBuilder()
            eb.setAuthor(member.effectiveName, null, member.user.avatarUrl)
            val obj: JSONObject = khttp.get("https://yesno.wtf/api").jsonObject
            if(obj.get("answer") == "yes"){
                eb.setDescription("Pytanie: `${args.joinToString(" ")}`\n Odpowiedź: Tak")
                eb.setColor(Color.GREEN)
            }else{
                eb.setDescription("Pytanie: `${args.joinToString(" ")}`\n Odpowiedź: Nie")
                eb.setColor(Color.RED)
            }
            eb.setImage("${obj.get("image")}")
            channel.sendMessage(eb.build()).queue()
        }else{
            channel.sendMessage("Wiadomość nie może być pusta!").queue()
        }
    }
}