package pl.xewald.ewald.bot.command.useful

import khttp.get
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.Command
import pl.xewald.ewald.bot.command.CommandCategory
import java.awt.Color
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class CryptocurrencyCommand(val bot: EwaldBot) : Command(
        "kryptowaluta",
        CommandCategory.USEFUL,
        "Sprawdź informacje o kryptowalucie.",
        listOf("cryptocurrency", "krypto")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        if (args.size == 1) {
            val obj = get("https://api.coinmarketcap.com/v1/ticker/${args[0]}?convert=PLN").jsonArray
            val pricepln = obj.getJSONObject(0).getBigDecimal("price_pln").setScale(2, BigDecimal.ROUND_HALF_DOWN).toDouble()
            val priceusd = obj.getJSONObject(0).getBigDecimal("price_usd").setScale(2, BigDecimal.ROUND_HALF_DOWN).toDouble()
            val pricebtc = obj.getJSONObject(0).getBigDecimal("price_btc").setScale(8, BigDecimal.ROUND_HALF_DOWN).toDouble()
            val update = Date(obj.getJSONObject(0).getLong("last_updated") * 1000L)
            val sdf = SimpleDateFormat("HH:mm")
            val formattedUpdate = sdf.format(update)
            val eb = EmbedBuilder()
            eb.setTitle("Informacje o kryptowalucie")
            eb.setFooter("EwaldBot.", "https://xewald.pl/Ewald.gif")
            eb.setThumbnail("http://cryptoicons.co/128/color/${obj.getJSONObject(0).getString("symbol").toLowerCase()}.png")
            if(obj.getJSONObject(0).getString("percent_change_1h") > "0"){
                eb.setColor(Color.GREEN)
            }else{
                eb.setColor(Color.RED)
            }
            eb.addField("Nazwa:", obj.getJSONObject(0).getString("name"), true)
            eb.addField("Symbol :", obj.getJSONObject(0).getString("symbol"), true)
            eb.addField("Miejsce w rankingu:", "#${obj.getJSONObject(0).getInt("rank")}", true)
            eb.addField("Ostatnia aktualizacja:", formattedUpdate, true)
            eb.addField("Cena:", "$pricepln PLN", true)
            eb.addField("Cena:", "$priceusd USD", true)
            eb.addField("Cena:", "$pricebtc BTC", true)
            eb.addField("Zmiana ceny - 1 godzina:", "${obj.getJSONObject(0).getString("percent_change_1h")} %", true)
            eb.addField("Zmiana ceny - 1 dzień:", "${obj.getJSONObject(0).getString("percent_change_24h")} %", true)
            eb.addField("Zmiana ceny - 1 tydzień:", "${obj.getJSONObject(0).getString("percent_change_7d")} %", true)
            channel.sendMessage(eb.build()).queue()
        }

    }
}

