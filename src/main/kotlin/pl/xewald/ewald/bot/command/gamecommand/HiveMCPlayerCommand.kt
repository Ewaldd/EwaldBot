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

class HiveMCPlayerCommand(val bot: EwaldBot) : Command(
        "hivemc",
        "Sprawdź statystyki na serwerze hivemc.com",
        listOf("pomoc")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = current.format(formatter)
        if (args.size == 1) {
            var name = ""
            for (arg in args) name += arg + " "
                val mojang_api = khttp.get("https://api.mojang.com/users/profiles/minecraft/$name")
                val mojang_connect: JSONObject = mojang_api.jsonObject
                val uuid = mojang_connect.get("id")
            val api = khttp.get("http://api.hivemc.com/v1/player/$uuid")
                val obj: JSONObject = api.jsonObject
                val eb = EmbedBuilder()
                eb.setAuthor("Statystyki gracza $name")
                eb.setDescription("Nazwa rangi: ${obj.get("rankName")} \nTokeny: ${obj.get("tokens")} \nMedale: ${obj.get("medals")}\nKredyty: ${obj.get("credits")}")
                eb.setThumbnail("https://visage.surgeplay.com/face/$uuid")
                channel.sendMessage(eb.build()).queue()
        }

    }
}