package pl.xewald.ewald.bot.command.useful

import khttp.get
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import org.json.JSONObject
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.Command
import pl.xewald.ewald.bot.command.CommandCategory
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class WeatherCommand(val bot: EwaldBot) : Command(
        "pogoda",
        CommandCategory.USEFUL,
        "Sprawdź pogodę w danym mieście",
        listOf("weather")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        channel.sendMessage("Komenda zawieszona do odwołania.").queue()
    }
}
