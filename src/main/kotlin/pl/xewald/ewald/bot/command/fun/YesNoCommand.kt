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
        listOf("pomoc")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        if (member == null) {
            channel.sendMessage("Ta komenda zadziała tylko na serwerach!").queue()
            return
        }
        val eb = EmbedBuilder()
        eb.setAuthor(member.effectiveName, null, member.user.avatarUrl)
        val obj: JSONObject = khttp.get("https://yesno.wtf/api").jsonObject
        eb.setDescription("Pytanie: `${args.joinToString(" ")}`\n" +
                "Odpowiedź: ${if (obj.get("answer") == "yes") {
                    "Tak"
                } else {
                    "Nie"
                }
                }")
        eb.setImage("${obj.get("image")}")
        eb.setColor(member.color)
        channel.sendMessage(eb.build()).queue()

    }
}