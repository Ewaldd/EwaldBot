package pl.xewald.ewald.bot.command

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import org.json.JSONObject
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.util.Command
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class IllegalCommand(val bot: EwaldBot) : Command(
        "zdelegalizuj",
        "Zdelegalizuj daną rzecz",
        listOf("illegal", "isillegal")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        val text = args.joinToString(" ").toUpperCase()
        if (args.isEmpty()) {
            message.channel.sendMessage("${message.author.asMention}, poprawne użycie: `!zdelegalizuj <tekst (^[a-z0-9]+$)>").queue()
            return
        }
        else if (text.length > 10) {
            message.channel.sendMessage("${message.author.asMention}, ten tekst jest za długi!").queue()
            return
        }
        else if (!text.toLowerCase().matches(Regex("^[a-z0-9]+\$"))) {
            message.channel.sendMessage("${message.author.asMention}, ten tekst jest nieprawidłowy!").queue()
            return
        }

        khttp.post("https://is-now-illegal.firebaseio.com/queue/tasks.json", json=mapOf("task" to "gif", "word" to text))
        val resp: JSONObject?
        while (true) {
            val r = khttp.get("https://is-now-illegal.firebaseio.com/gifs/$text.json")
            if (r.text.toLowerCase() != "null") { resp = r.jsonObject; break; }
        }


        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
        val formatted = current.format(formatter)
        val eb = EmbedBuilder()
        eb.setTitle("Delegalizacja $text", null)
        eb.setColor(member!!.color)
        eb.setImage(resp!!.getString("url"))
        eb.setAuthor("EwaldBot", "https://xewald.pl/", "https://xewald.pl/Ewald.gif")
        eb.setFooter("Data i godzina: $formatted", null)
        channel.sendMessage(eb.build()).queue()
    }
}