package pl.xewald.ewald.bot.command.gamecommand

import khttp.get
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.entities.MessageChannel
import org.json.JSONArray
import org.json.JSONObject
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.util.Command
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import khttp.delete as httpDelete

class ServerCommand(val bot: EwaldBot) : Command(
        "serwer",
        "Sprawdź informacje o serwerze",
        listOf("pomoc")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = current.format(formatter)
        if (args.size == 1) {
            var name = ""
            for (arg in args) name += arg + " "
            if (name.contains('.')) {
                val ip = khttp.get("https://mcapi.us/server/status?ip=$name")
                val obj: JSONObject = ip.jsonObject
                if (obj.get("status") == "success") {
                    if (obj.getJSONObject("server").get("name") != "") {
                        val eb = EmbedBuilder()
                        eb.setAuthor("Informacje o serwerze: $name")
                        eb.setDescription("Status: Online")
                        eb.addField("Motd:", "${obj.get("motd")}", true)
                        eb.addField("Graczy:", "${obj.getJSONObject("players").get("now")} / ${obj.getJSONObject("players").get("max")}", true)
                        eb.addField("Wersja:", "${obj.getJSONObject("server").get("name")}", true)
                        eb.setFooter("EwaldBot, $formatted", "https://xewald.pl/Ewald.gif")
                        channel.sendMessage(eb.build()).queue()
                    }else{
                        channel.sendMessage("Na podanym IP nie znajduje się żaden serwer minecraft.").queue()
                    }
                } else {
                    channel.sendMessage("Poprawne użycie: !serwer <adres serwera>").queue()
                }
            } else {
                channel.sendMessage("Poprawne użycie: !serwer <adres serwera>").queue()
            }
        } else {
            channel.sendMessage("Poprawne użycie: !serwer <adres serwera>").queue()
        }
    }
}
