package pl.xewald.ewald.bot.command.game

import khttp.get
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.*
import org.json.JSONObject
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.Command
import pl.xewald.ewald.bot.command.CommandCategory
import java.awt.Color
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import khttp.delete as httpDelete
import java.util.Random

class MCServerCommand(val bot: EwaldBot) : Command(
        "mcserwer",
        CommandCategory.GAME,
        "Sprawdź informacje o serwerze",
        listOf("serwermc")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
        val formatted = current.format(formatter)
        val rand = Random()
        val r = rand.nextFloat()
        val g = rand.nextFloat()
        val b = rand.nextFloat()
        val randomColor = Color(r, g, b)
        if (args.size == 1) {
            if (args[0].contains('.')) {
                val obj: JSONObject = get("https://api.skript.pl/server/${args[0]}/").jsonObject
                if (obj.get("online") == true) {
                    val eb = EmbedBuilder()
                    val description = obj.getString("description").replace(Regex("§[0-9a-fk-or]"), "")
                    eb.setAuthor("Informacje o serwerze: ${args[0]}")
                    eb.setDescription("Status: Online")
                    eb.addField("Motd:", "```$description```", true)
                    eb.setColor(randomColor)
                    eb.addField("Graczy:", "${obj.getJSONObject("players").get("online")} / ${obj.getJSONObject("players").get("max")}", true)
                    eb.addField("Wersja:", "${obj.getJSONObject("version").get("name")}", true)
                    eb.setFooter("EwaldBot, $formatted ", "https://xewald.pl/Ewald.gif")
                    channel.sendMessage(eb.build()).queue()
                } else {
                    channel.sendMessage("Na podanym IP nie ma działającego serwera").queue()
                }
            } else {
                channel.sendMessage("Poprawne użycie: !mcserwer <adres serwera>").queue()
            }
        } else {
            channel.sendMessage("Poprawne użycie: !mcserwer <adres serwera>").queue()
        }
    }
}
