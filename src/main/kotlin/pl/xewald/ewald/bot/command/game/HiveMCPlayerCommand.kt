package pl.xewald.ewald.bot.command.game

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import org.json.JSONObject
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.Command
import pl.xewald.ewald.bot.command.CommandCategory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import khttp.delete as httpDelete
class HiveMCPlayerCommand(val bot: EwaldBot) : Command(
        "hivemc",
        CommandCategory.GAME,
        "Sprawdź statystyki na serwerze hivemc.com",
        listOf("mchive")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
        val formatted = current.format(formatter)
        if (args.size == 1) {
            val mojangApi = khttp.get("https://api.mojang.com/users/profiles/minecraft/${args[0]}")
            val mojangConnect: JSONObject = mojangApi.jsonObject
            val uuid = mojangConnect.get("id")
            val api = khttp.get("http://api.hivemc.com/v1/player/$uuid")
            val obj: JSONObject = api.jsonObject
            val eb = EmbedBuilder().apply {
                setAuthor("Statystyki gracza ${args[0]}")
                setColor(member!!.color)
                addField("Nazwa rangi", "${obj.get("rankName")}", true)
                addField("Tokeny", "${obj.get("tokens")}", true)
                addField("Medale", "${obj.get("medals")}", true)
                addField("Punkty", "${obj.get("credits")}", true)
                setThumbnail("https://visage.surgeplay.com/face/$uuid")
                setFooter("EwaldBot, Serwer: hivemc.com $formatted", "https://xewald.pl/Ewald.gif")
            }
            channel.sendMessage(eb.build()).queue()
        } else {
            channel.sendMessage("Prawidłowe użycie: !hivemc <nick>").queue()
        }

    }
}