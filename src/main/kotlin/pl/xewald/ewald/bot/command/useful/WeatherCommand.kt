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
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) =
            if (args.size == 1) {
                val obj: JSONObject = get("http://api.openweathermap.org/data/2.5/weather?q=${args[0]}&appid=2035703f08285805f62e16249b2ec581&units=metric").jsonObject
                if (obj.get("cod") == 200) {
                    val sunrise = Date(obj.getJSONObject("sys").getLong("sunrise") * 1000L)
                    val sdf = SimpleDateFormat("HH:mm")
                    val formattedSunrise = sdf.format(sunrise)
                    val sunset = Date(obj.getJSONObject("sys").getLong("sunset") * 1000L)
                    val formattedSunset = sdf.format(sunset)
                    val current = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                    val formatted = current.format(formatter)
                    val eb = EmbedBuilder()
                    eb.setTitle("Pogoda w ${args[0]}, ${obj.getJSONObject("sys").get("country")}")
                    eb.setColor(member!!.color)
                    eb.setFooter("EwaldBot $formatted", "https://xewald.pl/Ewald.gif")
                    eb.addField("Temperatura:", "${obj.getJSONObject("main").get("temp")} °C", true)
                    eb.addField("Ciśnienie:", "${obj.getJSONObject("main").get("pressure")} hPa", true)
                    eb.addField("Wschod słońca:", formattedSunrise, true)
                    eb.addField("Zachód słońca:", formattedSunset, true)
                    eb.addField("Najmniejsza temperatura:", "${obj.getJSONObject("main").get("temp_min")} °C", true)
                    eb.addField("Najwyższa temperatura:", "${obj.getJSONObject("main").get("temp_max")} °C", true)
                    eb.addField("Wilgotność:", "${obj.getJSONObject("main").get("humidity")} %", true)
                    eb.addField("Zachmurzenie:", "${obj.getJSONObject("clouds").get("all")} %", true)
                    eb.addField("Szerokość geograficzna:", if (obj.getJSONObject("coord").getInt("lat") > 0) {
                        "${obj.getJSONObject("coord").get("lat")} °N"
                    } else {
                        "${obj.getJSONObject("coord").get("lat")} °S"
                    }, true)
                    eb.addField("Długość geograficzna:", if (obj.getJSONObject("coord").getInt("lon") > 0) {
                        "${obj.getJSONObject("coord").get("lon")} °E"
                    } else {
                        "${obj.getJSONObject("coord").get("lon")} °W"
                    }, true)
                    eb.addField("Prędkość wiatru:", "${obj.getJSONObject("wind").get("speed")} m/s", true)
                    val degree = obj.getJSONObject("wind").getInt("deg")
                    if (degree > 337.5) {
                        eb.addField("Kierunek wiatru:", "Północny", true)
                    }
                    if (degree > 295.5 && degree < 337.5) {
                        eb.addField("Kierunek wiatru:", "Północno-zachodni", true)
                    }
                    if (degree > 247.5 && degree < 295.5) {
                        eb.addField("Kierunek wiatru:", "Zachodni", true)
                    }
                    if (degree > 202.5 && degree < 247.5) {
                        eb.addField("Kierunek wiatru:", "Południowo-zachodni", true)
                    }
                    if (degree > 157.5 && degree < 202.5) {
                        eb.addField("Kierunek wiatru:", "Południowy", true)
                    }
                    if (degree > 122.5 && degree < 157.5) {
                        eb.addField("Kierunek wiatru:", "Południowo-wschodni", true)
                    }
                    if (degree > 67.5 && degree < 122.5) {
                        eb.addField("Kierunek wiatru:", "Wschodni", true)
                    }
                    if (degree > 22.5 && degree < 67.5) {
                        eb.addField("Kierunek wiatru:", "Północno-wschodni", true)
                    }
                    if (degree < 22.5) {
                        eb.addField("Kierunek wiatru:", "Północny", true)
                    }
                    eb.setThumbnail("http://openweathermap.org/img/w/${obj.getJSONArray("weather").getJSONObject(0).get("icon")}.png")
                    channel.sendMessage(eb.build()).queue()
                } else {
                    channel.sendMessage("${member!!.asMention}, miasto **${args[0]}** nie istnieje!").queue()
                }
            } else {
                channel.sendMessage("${member!!.asMention}, poprawne użycie: !pogoda miasto").queue()
            }
}
